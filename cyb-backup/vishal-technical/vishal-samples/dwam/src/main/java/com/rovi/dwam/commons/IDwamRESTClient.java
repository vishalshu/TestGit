package com.rovi.dwam.commons;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpMethod;

import com.rovi.dwam.exception.DwamException;
import com.rovi.dwam.ws.DWAMWebServices;
import com.rovi.dwam.ws.ServiceType;

/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 12, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
public interface IDwamRESTClient
{
	<R> R postForObject(Class<? extends R> responseClass, Map<String, String> headerParams, Object entity, DWAMWebServices services, ServiceType serviceType) throws DwamException;
	
	public URI postForLocation(Map<String, String> headerParams, Object entity, DWAMWebServices services, ServiceType serviceType) throws DwamException;

	String exchange(DWAMWebServices service, HttpMethod httpMethod, Map<String, String> headerParams, ServiceType serviceType, String... queryParams) throws DwamException;

	<R> R exchange(DWAMWebServices service, HttpMethod httpMethod, Class<? extends R> responseClass, Map<String, String> headerParams, ServiceType serviceType, String... queryParams) throws DwamException;
	
	void put(DWAMWebServices service, Map<String, String> headerParams, ServiceType serviceType, Object entity) throws  DwamException;
}
