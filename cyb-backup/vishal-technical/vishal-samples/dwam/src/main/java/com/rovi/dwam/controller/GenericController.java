package com.rovi.dwam.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.RequestContext;

import com.rovi.dwam.commons.IDwamRESTClient;
import com.rovi.dwam.exception.DwamRuntimeException;
import com.rovi.dwam.exception.ErrorMessage;
import com.rovi.dwam.log.DWAMLogger;

/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 6, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
@Controller
public abstract class GenericController
{
	private DWAMLogger logger = DWAMLogger.getLogger(GenericController.class);
	@Autowired
	protected IDwamRESTClient dwamRESTClient;
	
	@Autowired
	private MessageSource messageSource;

	protected Map<String, String> buildHttpAuthHeader(String username, String password)
	{
		String credential = username + ":" + password;
		credential = Base64.encodeBase64String(credential.getBytes());

		Map<String, String> headerParams = new HashMap<String, String>();
		headerParams.put("authorization", "BASIC " + credential);

		return headerParams;
	}

	@ExceptionHandler(value = DwamRuntimeException.class)
	public void dwamExceptionHandler(DwamRuntimeException ex, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.error(ex.getLocalizedMessage());
		Locale locale = getLocale(request);
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		String message = ex.getMessage();
		if (message == null)
		{
			// If server sends blank message then we will show generic error.
			ErrorMessage errorMessage = ErrorMessage.getErrorMessage(ex.getErrorCode());
			response.getWriter().write(getLocalizedMessage(errorMessage.getMessageKey(), locale));
		}
		else
		{
			response.getWriter().write(message);
		}
		response.flushBuffer();
	}

	protected Locale getLocale(HttpServletRequest request)
	{
		RequestContext requestContext = new RequestContext(request);
		return requestContext.getLocale();
	}
	
	protected String getLocalizedMessage(String messageKey, Locale locale, Object...params){
		return messageSource.getMessage(messageKey, params, locale);
	}
}
