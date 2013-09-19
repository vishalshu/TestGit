package com.rovi.dwam.exception;

/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 13, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
public class AuthorizationException extends DwamException
{
	private static final long serialVersionUID = 1L;

	/**
	 * @param e
	 */
	public AuthorizationException(Exception e)
	{
		super(e);
	}
	
	/**
	 * @param message
	 */
	public AuthorizationException(String message)
	{
		super(message);
	}
}
