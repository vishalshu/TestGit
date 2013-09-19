package com.rovi.dwam.exception;


/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jul 4, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
@SuppressWarnings("serial")
public class DwamRuntimeException extends RuntimeException
{
	private ErrorMessage errorMessage;
	private int errorCode;
	private Throwable originalException;
	
	/**
	 * 
	 */
	public DwamRuntimeException(int errorCode)
	{
		this.errorMessage = ErrorMessage.getErrorMessage(errorCode);
	}
	
	/**
	 * 
	 */
	public DwamRuntimeException(int errorCode, String message)
	{
		super(message);
		this.errorCode = errorCode;
		this.errorMessage = ErrorMessage.getErrorMessage(errorCode);
	}
	
	public DwamRuntimeException(Throwable ex){
		super(ex);
	}
	/**
	 * 
	 */
	public DwamRuntimeException(int errorCode, Throwable originalException)
	{
		super(originalException);
		this.errorMessage = ErrorMessage.getErrorMessage(errorCode);
		this.originalException = originalException;
	}
	
	public ErrorMessage getErrorMessage()
	{
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage)
	{
		this.errorMessage = errorMessage;
	}
	public Throwable getOriginalException()
	{
		return originalException;
	}
	public void setOriginalException(Throwable originalException)
	{
		this.originalException = originalException;
	}
	public int getErrorCode()
	{
		return errorCode;
	}
	public void setErroCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
	
}
