package com.rovi.dwam.ws;

import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Properties;

import org.springframework.http.MediaType;

import com.rovi.dwam.exception.DwamException;

/**
 * Utility to prepare an URL from externalized web service properties.
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 7, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
public class WSClientUtil
{
	public static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

	public static final MediaType MEDIA_TYPE_DEFAULT = new MediaType("application", "json", DEFAULT_CHARSET);

	/**
	 * Extracts properties and prepares web service URL
	 * 
	 * @param properties
	 * @param service
	 * @param params
	 * @return
	 * @throws SupportConsoleServiceException
	 */
	public static String prepareWebServiceClientURL(Properties properties, DWAMWebServices service, ServiceType serviceType, String... params) throws DwamException
	{
		StringBuffer wsUrl = null;

		if (properties != null)
		{
			wsUrl = new StringBuffer();
			if (ServiceType.DEVICE.equals(serviceType))
			{
				wsUrl.append(properties.get("ws.protocol.device")).append("://");
				wsUrl.append(properties.get("ws.address.device")).append(":");
				wsUrl.append(properties.get("ws.port.device")).append("/");
			}
			else if (ServiceType.USER.equals(serviceType))
			{
				wsUrl.append(properties.get("ws.protocol.user")).append("://");
				wsUrl.append(properties.get("ws.address.user")).append(":");
				wsUrl.append(properties.get("ws.port.user")).append("/");
			}
			else
			{
				wsUrl.append(properties.get("ws.protocol")).append("://");
				wsUrl.append(properties.get("ws.address")).append(":");
				wsUrl.append(properties.get("ws.port")).append("/");
			}
			wsUrl.append(formatServiceName((String) properties.get(service.name()), params));
		}
		else
		{
			throw new DwamException("Webservice properties should not be null!");
		}
		return wsUrl.toString();
	}

	/**
	 * Format service name by setting dynamic parameters
	 * 
	 * @param serviceName
	 * @param params
	 * @return formatted service name
	 */
	private static String formatServiceName(String serviceName, String... params)
	{
		if (params != null)
		{
			MessageFormat formatter = new MessageFormat(serviceName);
			return formatter.format(params);
		}
		return serviceName;
	}

	public static void main(String[] args)
	{
		System.out.println(System.getProperty("javax.net.ssl.trustStore"));
	}

}