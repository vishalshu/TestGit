package com.rovi.dwam.model;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: ptang Created: Jul 23,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class DeregisterCeDeviceRequest
{
	private Long deviceId;
	private String deregCode;
	private String sessionId;
	private String languageCode;
	private String countryCode;

	/**
	 * @return the deviceId
	 */
	public Long getDeviceId()
	{
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(Long deviceId)
	{
		this.deviceId = deviceId;
	}

	/**
	 * @return the deregCode
	 */
	public String getDeregCode()
	{
		return deregCode;
	}

	/**
	 * @param deregCode
	 *            the deregCode to set
	 */
	public void setDeregCode(String deregCode)
	{
		this.deregCode = deregCode;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	/**
	 * @return the languageCode
	 */
	public String getLanguageCode()
	{
		return languageCode;
	}

	/**
	 * @param languageCode
	 *            the languageCode to set
	 */
	public void setLanguageCode(String languageCode)
	{
		this.languageCode = languageCode;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode()
	{
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

}
