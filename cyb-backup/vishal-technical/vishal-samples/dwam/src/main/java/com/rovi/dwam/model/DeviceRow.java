package com.rovi.dwam.model;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: ewallin
 * Created: Jul 26, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class DeviceRow
{
	private Long deviceId;
	private String deregCode;
	private String deviceName;
	private String sessionId;
	private String languageCode;
	private String countryCode;

	public Long getDeviceId()
	{
		return deviceId;
	}

	public void setDeviceId(Long deviceId)
	{
		this.deviceId = deviceId;
	}

	public String getDeregCode()
	{
		return deregCode;
	}

	public void setDeregCode(String deregCode)
	{
		this.deregCode = deregCode;
	}

	public String getDeviceName()
	{
		return deviceName;
	}

	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	public String getLanguageCode()
	{
		return languageCode;
	}

	public void setLanguageCode(String languageCode)
	{
		this.languageCode = languageCode;
	}

	public String getCountryCode()
	{
		return countryCode;
	}

	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}
}
