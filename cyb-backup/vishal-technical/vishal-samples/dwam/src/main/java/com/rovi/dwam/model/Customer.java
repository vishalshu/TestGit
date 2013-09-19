package com.rovi.dwam.model;

/**
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: utsavj Created: Jun 11, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
public class Customer extends BaseEntity
{
	private String firstName;
	private String lastName;
	private String email;
	private String secKey;
	private String newPassword;
	private String sessionId;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getSecKey()
	{
		return secKey;
	}

	public void setSecKey(String secKey)
	{
		this.secKey = secKey;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstname)
	{
		this.firstName = firstname;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastname)
	{
		this.lastName = lastname;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId()
	{
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}
}
