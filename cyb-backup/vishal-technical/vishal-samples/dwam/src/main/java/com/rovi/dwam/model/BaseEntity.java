package com.rovi.dwam.model;

import java.io.Serializable;

/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 6, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
public abstract class BaseEntity implements Serializable
{
	private String languageCode;
	private long timestamp;
	private boolean success;
	private String countryCode;

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

	/**
	 * @return the timestamp
	 */
	public long getTimestamp()
	{
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(long timestamp)
	{
		this.timestamp = timestamp;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess()
	{
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success)
	{
		this.success = success;
	}
}
