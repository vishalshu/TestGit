package com.rovi.dwam.ws;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.rovi.dwam.exception.BaseErrorResponse;
import com.rovi.dwam.exception.DwamRuntimeException;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: ewallin Created: Jun 27,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class CustomResponseErrorHandler implements ResponseErrorHandler
{
	private static Logger logger = LoggerFactory.getLogger(CustomResponseErrorHandler.class);
	private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

	public boolean hasError(ClientHttpResponse response) throws IOException
	{
		return errorHandler.hasError(response);
	}

	public void handleError(ClientHttpResponse response) throws IOException
	{
		InputStream is = response.getBody();
		String errorResponse = IOUtils.toString(is, "UTF-8");
		logger.info("Error Response: " + errorResponse);
		if (errorResponse.contains("errorCode"))
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
			BaseErrorResponse rsp = mapper.readValue(errorResponse, BaseErrorResponse.class);
			throw new DwamRuntimeException(new Integer(rsp.getErrorResponse().get(0).getErrorCode()), rsp.getErrorResponse().get(0).getMessage());
		}
		else
		{
			errorHandler.handleError(response);
		}
	}
}
