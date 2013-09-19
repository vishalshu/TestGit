package com.rovi.dwam.model;

public class ResetPassword  extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String secKey;
	private String newPassword;
	
	
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
	/**
	 * @return the newPassword
	 */
	public String getNewPassword()
	{
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}


}
