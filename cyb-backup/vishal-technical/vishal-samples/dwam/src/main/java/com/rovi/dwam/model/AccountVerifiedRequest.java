package com.rovi.dwam.model;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: ptang
 * Created: Jul 27, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class AccountVerifiedRequest extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String secKey;
	
	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	/**
	 * @return the secKey
	 */
	public String getSecKey()
	{
		return secKey;
	}
	/**
	 * @param secKey the secKey to set
	 */
	public void setSecKey(String secKey)
	{
		this.secKey = secKey;
	}
	


}
