package com.rovi.dwam.service;

import com.rovi.dwam.exception.AuthorizationException;
import com.rovi.dwam.exception.DwamException;
import com.rovi.dwam.model.AccountRequest;
import com.rovi.dwam.model.AccountResponse;
import com.rovi.dwam.model.AccountVerifiedRequest;
import com.rovi.dwam.model.Customer;
import com.rovi.dwam.model.ResetPassword;
import com.rovi.dwam.model.ResetPasswordNotification;
import com.rovi.dwam.model.UpdateAccountRequest;

/**
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * @author Rovi Media Solutions Engineering: utsavj
 * Created: Jun 21, 2013
 * Copyright RoviCorp 2013 
 *
 * Developer Comment(s):
 */
public interface IUserService
{
	public Customer authenticate(String email,String password) throws AuthorizationException, DwamException;

	public AccountResponse signup(AccountRequest accountRequest) throws DwamException;
	
	public void updateCustomer(UpdateAccountRequest updateAccountRequest) throws DwamException;
	
	public AccountResponse getUserAccountInfo(String sessionId) throws DwamException;
	
	public void sendResetPasswordNotification(ResetPasswordNotification resetPasswordRequest) throws DwamException;
	
	public void sendResetPassword(ResetPassword request)throws DwamException;
	
	public void accountVerified(AccountVerifiedRequest request)throws DwamException;
}
