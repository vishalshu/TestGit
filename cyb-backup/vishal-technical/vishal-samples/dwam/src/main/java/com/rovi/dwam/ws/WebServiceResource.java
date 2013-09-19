package com.rovi.dwam.ws;

import org.springframework.core.io.Resource;

/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 7, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
public class WebServiceResource
{
	private Resource webServiceConfig;
	private Resource dwamConfig;
	private Resource dwamCertificate;

	public Resource getDwamConfig()
	{
		return dwamConfig;
	}

	public void setDwamConfig(Resource dwamConfig)
	{
		this.dwamConfig = dwamConfig;
	}

	public Resource getWebServiceConfig()
	{
		return webServiceConfig;
	}

	public void setWebServiceConfig(Resource webServiceConfig)
	{
		this.webServiceConfig = webServiceConfig;
	}

	public Resource getDwamCertificate()
	{
		return dwamCertificate;
	}

	public void setDwamCertificate(Resource dwamCertificate)
	{
		this.dwamCertificate = dwamCertificate;
	}
}
