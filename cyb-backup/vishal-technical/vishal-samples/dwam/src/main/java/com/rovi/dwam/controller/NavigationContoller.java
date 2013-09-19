package com.rovi.dwam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 7, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
@Controller
public class NavigationContoller
{
	/**
	 * 
	 * @return home page template
	 */
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String getHome()
	{
		return "dwam_home";
	}

	/**
	 * @param pageName
	 *            name of canvas
	 * @return jsp of canvas
	 */
	@RequestMapping(value = "/getPage.do", method = RequestMethod.GET)
	public String getPage(@RequestParam("pageName") String pageName)
	{
		return pageName;
	}

	@RequestMapping(value = "/showLogin.htm", method = RequestMethod.GET)
	public String showLogin()
	{
		return "login";
	}

	@RequestMapping(value = "/userSignUp.htm", method = RequestMethod.GET)
	public String userSignUp()
	{
		return "registration";
	}

	@RequestMapping(value = "/registerDevice.htm", method = RequestMethod.GET)
	public String registerDevice()
	{
		return "register_device";
	}

	@RequestMapping(value = "/download.htm", method = RequestMethod.GET)
	public String download()
	{
		return "download";
	}

	@RequestMapping(value = "/resetPasswordNotification.htm", method = RequestMethod.GET)
	public String showResetPasswordNotification()
	{
		return "reset_password_notification";
	}

	@RequestMapping(value = "/resetPasswordNotifConf.htm", method = RequestMethod.GET)
	public String showResetPasswordNotifConfirmation(HttpServletRequest request)
	{
		return "reset_password_notification_conf";
	}

	@RequestMapping(value = "/confirm_password_active.htm", method = RequestMethod.GET)
	public String resetPasswordPage(HttpServletRequest request)
	{
		return "confirm_password_active";
	}

	@RequestMapping(value = "/reset_password_page.htm", method = RequestMethod.GET)
	public String showForgotPasswordPage(HttpServletRequest request)
	{
		return "reset_password_page";
	}

	@RequestMapping(value = "/deregisterDevice.htm", method = RequestMethod.GET)
	public String deRegisterDevice()
	{
		return "deregister_device";
	}

	@RequestMapping(value = "/delete_device.htm", method = RequestMethod.GET)
	public String deleteDevice(HttpServletRequest request)
	{
		return "delete_device";
	}
	
	@RequestMapping(value = "/delete_device_success.htm", method = RequestMethod.GET)
	public String deleteDeviceSuccess(HttpServletRequest request)
	{
		return "delete_device_success";
	}

	
	@RequestMapping(value = "/deregisterSuccess.htm", method = RequestMethod.GET)
	public String deRegisterDeviceSuccess()
	{
		return "deregister_success";
	}
	
	@RequestMapping(value = "/accountVerified.htm", method = RequestMethod.GET)
	public String accountVerified()
	{
		return "account_verified";
	}
}
