package com.rovi.dwam.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rovi.dwam.exception.DwamException;
import com.rovi.dwam.model.AccountRequest;
import com.rovi.dwam.model.Customer;
import com.rovi.dwam.model.DeregisterCeDeviceRequest;
import com.rovi.dwam.model.DeregisterDeviceResponse;
import com.rovi.dwam.model.RegisterCeDeviceRequest;
import com.rovi.dwam.model.RegisterCeDeviceResponse;
import com.rovi.dwam.model.RegisteredDeviceBaseResponse;
import com.rovi.dwam.service.IDeviceService;
import com.rovi.dwam.service.IUserService;
import com.rovi.dwam.util.DwamUserContext;


@ContextConfiguration(locations = { "/applicationContext-test.xml","/META-INF/spring/applicationContext-service.xml"})
public class DeviceRegistrationTest extends AbstractTestNGSpringContextTests
{
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	AccountRequest accountRequest;
	
	@Autowired
	RegisterCeDeviceRequest registerCeDeviceRequest;
	
	@Autowired
	DeregisterCeDeviceRequest deRegisterCeDeviceRequest;
	
	@Test(enabled=false)
	public void testRegisteredDeviceList() throws Exception
	{
		
		Customer customer = userService.authenticate(accountRequest.getEmail(), accountRequest.getPassword());
		//Localization property
		DwamUserContext.setLanguage("en");
		DwamUserContext.setCountry("US");
		
		RegisteredDeviceBaseResponse registeredDeviceBaseResponse = deviceService.getRegisteredDeviceList(customer.getSessionId());
		Assert.assertNotNull(registeredDeviceBaseResponse.getRegisteredDeviceList());
		Assert.assertTrue(registeredDeviceBaseResponse.isSuccess());

	}

	@Test(enabled=false)
	public void testRegisterNewDevice() throws Exception
	{
		
		Customer customer = userService.authenticate(accountRequest.getEmail(), accountRequest.getPassword());
		String activationURL= "";
		DwamUserContext.setLanguage("en");
		DwamUserContext.setCountry("US");
		registerCeDeviceRequest.setLanguageCode("en");
		registerCeDeviceRequest.setCountryCode("US");
		registerCeDeviceRequest.setSession(customer.getSessionId());
		
		RegisterCeDeviceResponse registerCeDeviceResponse = deviceService.registerDevice(registerCeDeviceRequest);
		Assert.assertNotEquals(activationURL, registerCeDeviceResponse.getActivationURL());
	
	}
	
	@Test(enabled=false)
	public void testDeRegisterDevice() throws Exception
	{
		
		Customer customer = userService.authenticate(accountRequest.getEmail(), accountRequest.getPassword());
		DwamUserContext.setLanguage("en");
		DwamUserContext.setCountry("US");
		
		deRegisterCeDeviceRequest.setLanguageCode("en");
		deRegisterCeDeviceRequest.setCountryCode("US");
		deRegisterCeDeviceRequest.setSessionId(customer.getSessionId());
				
		DeregisterDeviceResponse deregisterCeDeviceResponse = deviceService.deRegisterDevice(deRegisterCeDeviceRequest);
		Assert.assertTrue(deregisterCeDeviceResponse.isSuccess());
	}
	
	@Test(enabled=false)
	public void testDeleteDevice() throws Exception
	{
		
		//Customer customer = userService.authenticate(accountRequest.getEmail(), accountRequest.getPassword());
		
		/*deRegisterCeDeviceRequest.setLanguageCode(locale.getLanguage());
		deRegisterCeDeviceRequest.setCountryCode(locale.getCountry());
		deRegisterCeDeviceRequest.setSessionId(sessionKey);
		
		deregisterCeDeviceResponse = deviceService.deRegisterDevice(deRegisterCeDeviceRequest);
		deregisterCeDeviceResponse.setSession(sessionKey);*/
		//Assert.assertNotEquals(activationURL, registerCeDeviceResponse.getActivationURL());
	
	}

}
