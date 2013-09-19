package com.rovi.dwam.exception;

/**
 * Enumeration to hold error code with its corresponding message key. <br> 
 * Message key will be used to load locale specific error messages.
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jul 4, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
public enum ErrorMessage
{
	GENERIC_ERROR(0,"error.genericError"), 
	USER_PROVIDED_NOT_REGISTERED(32037,"error.forgotPasswordNotifError"),
	UPDATE_ACCOUNT_INVALID_PASSWORD(32022,"error.updateAccount.authenticationFailure"),
	UNABLE_TO_DISPATCH_EMAIL(322021,"error.genericError"),
	ONE(1,"label.one"),
	TWO(2,"label.two"),
	THREE(3,"label.three"),
	FOUR(4,"label.four"),
	FIVE(5,"label.five"),
	SIX(6,"label.six"),
	DEVICE_ACTIVATION_CODE_INVALID(32047,"error.registration.registrationCode")
	;
	
	private String messageKey;
	private int errorCode;
	
	/**
	 * @param messageKey
	 */
	private ErrorMessage(int errorCode, String messageKey)
	{
		this.errorCode = errorCode;
		this.messageKey = messageKey;
	}

	public String getMessageKey()
	{
		return messageKey;
	}

	public int getErrorCode()
	{
		return errorCode;
	}
	
	public static ErrorMessage getErrorMessage(int errorCode){
		for(ErrorMessage errorMsg : ErrorMessage.values()){
			if(errorMsg.getErrorCode()==errorCode){
				return errorMsg;
			}
		}
		return null;
	}

}
