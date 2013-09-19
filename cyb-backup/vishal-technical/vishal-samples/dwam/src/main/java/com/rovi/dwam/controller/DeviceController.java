package com.rovi.dwam.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rovi.dwam.log.DWAMLogger;
import com.rovi.dwam.model.DeregisterCeDeviceRequest;
import com.rovi.dwam.model.DeregisterDeviceResponse;
import com.rovi.dwam.model.DeviceRow;
import com.rovi.dwam.model.RegisterCeDeviceRequest;
import com.rovi.dwam.model.RegisterCeDeviceResponse;
import com.rovi.dwam.model.RegisteredDeviceBaseResponse;
import com.rovi.dwam.service.IDeviceService;
import com.rovi.dwam.util.DwamUserContext;
import com.rovi.dwam.util.SessionKeys;

/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: utsavj Created: Jun 7, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
@Controller
public class DeviceController extends GenericController
{
	private static DWAMLogger logger = DWAMLogger.getLogger(DeviceController.class);

	@Autowired
	IDeviceService deviceService;
	
	@RequestMapping(value = "/getRegisteredDeviceList.do", method = RequestMethod.GET)
	public @ResponseBody RegisteredDeviceBaseResponse getRegisteredDeviceList(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.info("Fetching Registered Device List");
		RegisteredDeviceBaseResponse registeredDeviceBaseResponse = null;
	
		String sessionKey = (String) request.getSession().getAttribute(SessionKeys.LOGGED_IN_USER_SESSION.toString());

		registeredDeviceBaseResponse = deviceService.getRegisteredDeviceList(sessionKey);
		
		registeredDeviceBaseResponse.setRemainingNumberOfDeviceRegistration( registeredDeviceBaseResponse.getRemainingDeviceRegistrations().toString() );	
		return registeredDeviceBaseResponse;
	}
	
	
	/**
	 * For new device registration
	 * 
	 * @param registerCeDeviceRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registerDevice.do", method = RequestMethod.POST)
	public @ResponseBody String registerDevice(@RequestBody RegisterCeDeviceRequest registerCeDeviceRequest, HttpServletRequest request) throws Exception
	{
		logger.info("Register New Device");

		RegisterCeDeviceResponse registerCeDeviceResponse = null;
		
		String sessionKey = (String) request.getSession().getAttribute(SessionKeys.LOGGED_IN_USER_SESSION.toString());
		registerCeDeviceRequest.setLanguageCode(DwamUserContext.getLanguage());
		registerCeDeviceRequest.setCountryCode(DwamUserContext.getCountry());
		registerCeDeviceRequest.setSession(sessionKey);
		
		registerCeDeviceResponse = deviceService.registerDevice(registerCeDeviceRequest);
		registerCeDeviceResponse.setSessionId(sessionKey);
		request.getSession().setAttribute(SessionKeys.ACTIVATION_URL.toString(), registerCeDeviceResponse.getActivationURL());
		return registerCeDeviceResponse.getActivationURL();
	}
	
	@RequestMapping(value = "/deleteDevice.do", method = RequestMethod.POST)
	public @ResponseBody DeregisterDeviceResponse deleteDevice(@RequestBody DeregisterCeDeviceRequest deRegisterCeDeviceRequest, HttpServletRequest request) throws Exception
	{
		logger.info("delete Device");

		DeregisterDeviceResponse deregisterCeDeviceResponse = null;
		
		String sessionKey = (String) request.getSession().getAttribute(SessionKeys.LOGGED_IN_USER_SESSION.toString());
		deRegisterCeDeviceRequest.setLanguageCode(DwamUserContext.getLanguage());
		deRegisterCeDeviceRequest.setCountryCode(DwamUserContext.getCountry());
		deRegisterCeDeviceRequest.setSessionId(sessionKey);
		
		deregisterCeDeviceResponse = deviceService.deRegisterDevice(deRegisterCeDeviceRequest);
		deregisterCeDeviceResponse.setSession(sessionKey);
		return deregisterCeDeviceResponse;
	}
	
	@RequestMapping(value = "/deregisterDevice.do", method = RequestMethod.POST)
	public @ResponseBody DeregisterDeviceResponse deRegisterDevice(@RequestBody DeregisterCeDeviceRequest deRegisterCeDeviceRequest, HttpServletRequest request) throws Exception
	{
		logger.info("deregister Device");

		DeregisterDeviceResponse deregisterCeDeviceResponse = null;
		
		String sessionKey = (String) request.getSession().getAttribute(SessionKeys.LOGGED_IN_USER_SESSION.toString());
		deRegisterCeDeviceRequest.setLanguageCode(DwamUserContext.getLanguage());
		deRegisterCeDeviceRequest.setCountryCode(DwamUserContext.getCountry());
		deRegisterCeDeviceRequest.setSessionId(sessionKey);
				
		deregisterCeDeviceResponse = deviceService.deRegisterDevice(deRegisterCeDeviceRequest);
		deregisterCeDeviceResponse.setSession(sessionKey);
		
		return deregisterCeDeviceResponse;
	}
	
	@RequestMapping(value = "/storeDeviceInfoInSession.do", method = RequestMethod.POST)
	public @ResponseBody String storeDeviceInfoInSession(@RequestBody DeviceRow deviceInfo, HttpServletRequest request)
	{
		request.getSession().setAttribute(SessionKeys.DEVICE_INFO.toString(), deviceInfo);
		return "success";
	}

	@RequestMapping(value = "/fetchDeviceInfoFromSession.do", method = RequestMethod.POST)
	public @ResponseBody DeviceRow fetchDeviceInfoFromSession(HttpServletRequest request)
	{
		return (DeviceRow) request.getSession().getAttribute(SessionKeys.DEVICE_INFO.toString());
	}
	
	@RequestMapping(value = "/fetchActivationUrlFromSession.do", method = RequestMethod.POST)
	public @ResponseBody String fetchActivationUrlFromSession(HttpServletRequest request)
	{
		return (String) request.getSession().getAttribute(SessionKeys.ACTIVATION_URL.toString());
	}
}
