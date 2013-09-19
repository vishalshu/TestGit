package com.rovi.dwam.model;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: ewallin
 * Created: Jul 1, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class ResetPasswordNotification extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	private String email;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
