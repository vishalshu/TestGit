package com.rovi.dwam.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpServerErrorException;

import com.rovi.dwam.commons.IDwamRESTClient;
import com.rovi.dwam.exception.DwamException;
import com.rovi.dwam.log.DWAMLogger;
import com.rovi.dwam.model.DeregisterCeDeviceRequest;
import com.rovi.dwam.model.DeregisterDeviceResponse;
import com.rovi.dwam.model.RegisterCeDeviceRequest;
import com.rovi.dwam.model.RegisterCeDeviceResponse;
import com.rovi.dwam.model.RegisteredDeviceBaseResponse;
import com.rovi.dwam.util.DwamUserContext;
import com.rovi.dwam.ws.DWAMWebServices;
import com.rovi.dwam.ws.ServiceType;

public class DeviceServiceImpl implements IDeviceService
{
	@Autowired
	protected IDwamRESTClient dwamRESTClient;

	private static DWAMLogger logger = DWAMLogger.getLogger(DeviceServiceImpl.class);

	// @Override
	public RegisteredDeviceBaseResponse getRegisteredDeviceList(String sessionId) throws Exception
	{
		try
		{
			Map<String, String> headerParams = null;
			RegisteredDeviceBaseResponse registeredDeviceBaseResponse = null;
			registeredDeviceBaseResponse = dwamRESTClient.exchange(DWAMWebServices.REGISTERED_DEVICE_LIST, HttpMethod.GET, RegisteredDeviceBaseResponse.class, headerParams, ServiceType.DEVICE, sessionId, DwamUserContext.getLanguage(), DwamUserContext.getCountry());
			logger.info("Registered Devices = " + registeredDeviceBaseResponse.getRegisteredDeviceList().size());
			return registeredDeviceBaseResponse;
		}
		catch (HttpServerErrorException e)
		{
			throw new DwamException(e);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	// @Override
	public RegisterCeDeviceResponse registerDevice(RegisterCeDeviceRequest registerCeDeviceRequest) throws DwamException
	{
		RegisterCeDeviceResponse registerCeDeviceResponse = null;
		try
		{
			registerCeDeviceResponse = dwamRESTClient.postForObject(RegisterCeDeviceResponse.class, null, registerCeDeviceRequest, DWAMWebServices.REGISTER_DEVICE, ServiceType.DEVICE);
		}
		catch (HttpServerErrorException e)
		{
			throw new DwamException(e);
		}
		return registerCeDeviceResponse;
	}

	// @Override
	public DeregisterDeviceResponse deRegisterDevice(DeregisterCeDeviceRequest deregisterCeDeviceRequest) throws DwamException
	{
		DeregisterDeviceResponse deregisterDeviceResponse = null;
		try
		{
			deregisterDeviceResponse = dwamRESTClient.postForObject(DeregisterDeviceResponse.class, null, deregisterCeDeviceRequest, DWAMWebServices.DEREGISTER_DEVICE, ServiceType.DEVICE);
		}
		catch (HttpServerErrorException e)
		{
			throw new DwamException(e);
		}
		return deregisterDeviceResponse;
	}
}
