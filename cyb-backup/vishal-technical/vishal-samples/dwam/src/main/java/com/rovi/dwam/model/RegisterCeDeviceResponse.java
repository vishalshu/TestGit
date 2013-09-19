package com.rovi.dwam.model;

public class RegisterCeDeviceResponse 
{
	String activationURL;
	private String sessionId = null;

	public String getActivationURL()
	{
		return activationURL;
	}

	public void setActivationURL(String activationURL)
	{
		this.activationURL = activationURL;
	}
	
	public String getSessionId()
	{
		return sessionId;
	}
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

}
