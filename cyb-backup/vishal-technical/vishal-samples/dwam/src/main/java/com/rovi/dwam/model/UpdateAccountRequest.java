package com.rovi.dwam.model;

/**
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * @author Rovi Media Solutions Engineering: utsavj
 * Created: Jun 20, 2013
 * Copyright RoviCorp 2013 
 *
 * Developer Comment(s):
 */
public class UpdateAccountRequest extends AccountRequest
{
	private String sessionId;
	private String newPassword;
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
