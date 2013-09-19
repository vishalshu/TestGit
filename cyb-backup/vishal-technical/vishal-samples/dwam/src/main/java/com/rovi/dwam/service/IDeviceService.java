package com.rovi.dwam.service;

import com.rovi.dwam.model.DeregisterDeviceResponse;
import com.rovi.dwam.model.DeregisterCeDeviceRequest;
import com.rovi.dwam.model.RegisterCeDeviceRequest;
import com.rovi.dwam.model.RegisterCeDeviceResponse;
import com.rovi.dwam.model.RegisteredDeviceBaseResponse;

public interface IDeviceService

{
	public RegisteredDeviceBaseResponse getRegisteredDeviceList(String sessionId) throws Exception;
	
	public RegisterCeDeviceResponse registerDevice(RegisterCeDeviceRequest registerCeDeviceRequest) throws Exception;
	
	public DeregisterDeviceResponse deRegisterDevice(DeregisterCeDeviceRequest deregisterCeDeviceRequest) throws Exception;

}
