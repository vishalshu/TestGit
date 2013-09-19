package com.rovi.dwam.model;


public class RegisterCeDeviceRequest
{
	private String session;
	private String regCode;
	private String newDeviceName;
	private String languageCode;
	private String countryCode;
	
	public String getSession()
	{
		return session;
	}
	public void setSession(String session)
	{
		this.session = session;
	}
	public String getRegCode()
	{
		return regCode;
	}
	public void setRegCode(String regCode)
	{
		this.regCode = regCode;
	}
	public String getNewDeviceName()
	{
		return newDeviceName;
	}
	public void setNewDeviceName(String newDeviceName)
	{
		this.newDeviceName = newDeviceName;
	}
	
	/**
	 * @return the countryCode
	 */
	public String getCountryCode()
	{
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
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

	
}
