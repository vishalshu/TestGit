package com.rovi.dwam.service;

/**
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * @author Rovi Media Solutions Engineering: utsavj
 * Created: Jun 21, 2013
 * Copyright RoviCorp 2013 
 *
 * Developer Comment(s):
 */

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.support.RequestContext;

import com.rovi.dwam.commons.IDwamRESTClient;
import com.rovi.dwam.exception.AuthorizationException;
import com.rovi.dwam.exception.DwamException;
import com.rovi.dwam.exception.DwamRuntimeException;
import com.rovi.dwam.log.DWAMLogger;
import com.rovi.dwam.model.AccountRequest;
import com.rovi.dwam.model.AccountResponse;
import com.rovi.dwam.model.AccountVerifiedRequest;
import com.rovi.dwam.model.Customer;
import com.rovi.dwam.model.ResetPassword;
import com.rovi.dwam.model.ResetPasswordNotification;
import com.rovi.dwam.model.UpdateAccountRequest;
import com.rovi.dwam.util.DwamUserContext;
import com.rovi.dwam.ws.DWAMWebServices;
import com.rovi.dwam.ws.ServiceType;

public class UserServiceImpl implements IUserService
{
	private static DWAMLogger logger = DWAMLogger.getLogger(UserServiceImpl.class);

	@Autowired
	protected IDwamRESTClient dwamRESTClient;

	public Customer authenticate(String email, String password) throws AuthorizationException, DwamException
	{
		logger.info("Authenticating user: {}", email);
		Customer customer = null;

		try
		{
			Map<String, String> headerParams = buildHttpAuthHeader(email, password);
			customer = dwamRESTClient.exchange(DWAMWebServices.AUTHENTICATE_CUSTOMER, HttpMethod.GET, Customer.class, headerParams, ServiceType.USER, DwamUserContext.getLanguage(), DwamUserContext.getCountry());
		}
		catch (DwamRuntimeException dre)
		{
			throw new AuthorizationException(dre.getMessage());
		}
		catch (HttpServerErrorException e)
		{
			throw new DwamException(e);
		}

		return customer;
	}

	public AccountResponse signup(AccountRequest accountRequest) throws DwamException
	{
		logger.info("Signing up user: {}", accountRequest.getFirstName());
		AccountResponse accountResponse = null;

		Map<String, String> headerParams = new HashMap<String, String>();

		try
		{
			accountResponse = dwamRESTClient.postForObject(AccountResponse.class, headerParams, accountRequest, DWAMWebServices.CUSTOMER_SIGNUP, ServiceType.USER);
			logger.info("Signup Response :" + accountResponse.getCustomerId());
		}
		catch (HttpServerErrorException e)
		{
			throw new DwamException(e);
		}

		return accountResponse;
	}

	public void updateCustomer(UpdateAccountRequest updateAccountRequest) throws DwamException
	{
		try
		{
			Map<String, String> headerParams = new HashMap<String, String>();

			dwamRESTClient.put(DWAMWebServices.CUSTOMER_UPDATE, headerParams, ServiceType.USER, updateAccountRequest);
		}
		catch (HttpServerErrorException e)
		{
			throw new DwamException(e);
		}
	}

	public AccountResponse getUserAccountInfo(String sessionId) throws DwamException
	{
		AccountResponse customerAccountInfo = null;
		try
		{
			customerAccountInfo = dwamRESTClient.exchange(DWAMWebServices.CUSTOMER_ACCOUNT_INFO, HttpMethod.GET, AccountResponse.class, null, ServiceType.USER, sessionId, DwamUserContext.getLanguage(), DwamUserContext.getCountry());
		}
		catch (HttpServerErrorException e)
		{
			throw new DwamException(e);
		}

		return customerAccountInfo;
	}

	public void sendResetPasswordNotification(ResetPasswordNotification resetPasswordRequest) throws DwamException
	{
		try
		{
			Map<String, String> headerParams = new HashMap<String, String>();
			dwamRESTClient.postForLocation(headerParams, resetPasswordRequest, DWAMWebServices.SEND_RESET_PASSWORD_NOTIFICATION, ServiceType.USER);
		}
		catch (HttpServerErrorException e)
		{
			throw new DwamException(e);
		}
	}

	public void sendResetPassword(ResetPassword request) throws DwamException
	{
		try
		{
			Map<String, String> headerParams = new HashMap<String, String>();
			dwamRESTClient.postForLocation(headerParams, request, DWAMWebServices.SEND_RESET_PASSWORD, ServiceType.USER);
		}
		catch (HttpServerErrorException e)
		{
			throw new DwamException(e);
		}
	}

	public void accountVerified(AccountVerifiedRequest request) throws DwamException
	{
		try
		{
			Map<String, String> headerParams = new HashMap<String, String>();
			dwamRESTClient.postForLocation(headerParams, request, DWAMWebServices.ACCOUNT_VERIFIED, ServiceType.USER);
		}
		catch (HttpServerErrorException e)
		{
			throw new DwamException(e);
		}
	}

	protected Map<String, String> buildHttpAuthHeader(String username, String password)
	{
		String credential = username + ":" + password;
		credential = Base64.encodeBase64String(credential.getBytes());

		Map<String, String> headerParams = new HashMap<String, String>();
		headerParams.put("authorization", "BASIC " + credential);

		return headerParams;
	}

	protected Locale getLocale(HttpServletRequest request)
	{
		RequestContext requestContext = new RequestContext(request);
		return requestContext.getLocale();
	}
}
