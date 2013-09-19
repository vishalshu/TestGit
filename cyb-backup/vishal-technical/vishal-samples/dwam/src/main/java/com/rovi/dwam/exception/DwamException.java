package com.rovi.dwam.exception;

/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 7, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
@SuppressWarnings("serial")
public class DwamException extends Exception
{
	public DwamException(String message)
	{
		super(message);
	}

	public DwamException(Exception e)
	{
		super(e);
	}
}
