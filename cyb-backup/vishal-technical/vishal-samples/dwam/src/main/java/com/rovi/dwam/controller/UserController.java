package com.rovi.dwam.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rovi.dwam.log.DWAMLogger;
import com.rovi.dwam.model.AccountRequest;
import com.rovi.dwam.model.AccountResponse;
import com.rovi.dwam.model.AccountVerifiedRequest;
import com.rovi.dwam.model.Customer;
import com.rovi.dwam.model.ResetPassword;
import com.rovi.dwam.model.ResetPasswordNotification;
import com.rovi.dwam.model.UpdateAccountRequest;
import com.rovi.dwam.service.IUserService;
import com.rovi.dwam.util.DwamUserContext;
import com.rovi.dwam.util.SessionKeys;

/**
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: utsavj Created: Jun 11, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
@Controller
public class UserController extends GenericController
{
	private static DWAMLogger logger = DWAMLogger.getLogger(UserController.class);

	
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/loadUser.do")
	public @ResponseBody AccountResponse loadUser(HttpServletRequest request, Model model) throws Exception
	{
		logger.info("customerAccountinfo: " + request.getSession().getAttribute("customerAccountinfo"));
		String sessionKey = (String) request.getSession().getAttribute(SessionKeys.LOGGED_IN_USER_SESSION.toString());
		
		AccountResponse customerAccountInfo = userService.getUserAccountInfo(sessionKey);
		return customerAccountInfo;
	}

	@RequestMapping(value = "/registerUser.do", method = RequestMethod.POST)
	public @ResponseBody String customerSignup(@RequestBody AccountRequest accountRequest, HttpServletRequest request) throws Exception
	{
		logger.info("Signing up user: {}", accountRequest.getEmail());

		accountRequest.setLanguageCode(DwamUserContext.getLanguage());
		accountRequest.setCountryCode(DwamUserContext.getCountry());

		AccountResponse accountResponse = userService.signup(accountRequest);
		return accountResponse.getEmail();
	}
	@RequestMapping(value = "/accountVerified.do", method = RequestMethod.POST)
	public   @ResponseBody String accountVerified(  HttpServletRequest request,  @RequestBody AccountVerifiedRequest accountVerifiedRequest ) throws Exception
	{
		logger.info("accountVerified email: {} , secKey {}", accountVerifiedRequest.getEmail(), accountVerifiedRequest.getSecKey());
		
		accountVerifiedRequest.setLanguageCode(DwamUserContext.getLanguage());
		accountVerifiedRequest.setCountryCode(DwamUserContext.getCountry());
		
		userService.accountVerified(accountVerifiedRequest);
		
		return "account_verified";
	}

	@RequestMapping(value = "/updateUser.do", method = RequestMethod.POST)
	public @ResponseBody String updateUser(HttpServletRequest request, @RequestBody UpdateAccountRequest updateAccountRequest) throws Exception
	{
		String sessionKey = (String) request.getSession().getAttribute(SessionKeys.LOGGED_IN_USER_SESSION.toString());
		updateAccountRequest.setLanguageCode(DwamUserContext.getLanguage());
		updateAccountRequest.setCountryCode(DwamUserContext.getCountry());
		updateAccountRequest.setSessionId(sessionKey);
		logger.info("Updating user: {}", updateAccountRequest.getEmail());
		
		userService.updateCustomer(updateAccountRequest);
		return "update_user";
	}
	
	@RequestMapping(value = "/forgotPasswordRequest.do", method = RequestMethod.POST)
	public @ResponseBody String sendResetPasswordNotification(@RequestBody ResetPasswordNotification resetPasswordRequest, HttpServletRequest request) throws Exception
	{
		logger.info("Sending reset password request to: {}", resetPasswordRequest.getEmail());
		
		resetPasswordRequest.setLanguageCode(DwamUserContext.getLanguage());
		resetPasswordRequest.setCountryCode(DwamUserContext.getCountry());
		
		userService.sendResetPasswordNotification(resetPasswordRequest);
		
		return resetPasswordRequest.getEmail();
	}
	
	@RequestMapping(value = "/reset_password.do", method = RequestMethod.POST)
	public @ResponseBody String sendResetPassword(@RequestBody ResetPassword resetPasswordRequest, HttpServletRequest request) throws Exception
	{
		logger.info("Sending reset password request -  email: {}, secKey: {}", resetPasswordRequest.getEmail(), resetPasswordRequest.getSecKey());
		
		resetPasswordRequest.setLanguageCode(DwamUserContext.getLanguage());
		resetPasswordRequest.setCountryCode(DwamUserContext.getCountry());
		
		userService.sendResetPassword(resetPasswordRequest);
		
		return "confirm_password_active";
	}

	@RequestMapping(value = "/getCustomers.do", method = RequestMethod.GET)
	public @ResponseBody List<Customer> getCustomers() throws Exception
	{
		List<Customer> customerList = new ArrayList<Customer>();

		Customer customer1 = new Customer();
		customer1.setEmail("jimasdfsadf@gmail.com");
		customerList.add(customer1);

		Customer customer2 = new Customer();
		customer2.setEmail("jimasd@gmail.com");
		customerList.add(customer2);
		return customerList;
	}
	
}
