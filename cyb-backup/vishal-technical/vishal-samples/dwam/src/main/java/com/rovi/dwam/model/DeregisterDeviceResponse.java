package com.rovi.dwam.model;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: ptang
 * Created: Jul 23, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class DeregisterDeviceResponse extends BaseEntity
{
	private String session;

	/**
	 * @return the session
	 */
	public String getSession()
	{
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(String session)
	{
		this.session = session;
	}

}
