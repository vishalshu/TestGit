package com.rovi.dwam.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * @author Rovi Media Solutions Engineering: utsavj
 * Created: Jun 21, 2013
 * Copyright RoviCorp 2013 
 *
 * Developer Comment(s):
 */

public class RegisteredDeviceBaseResponse
{

	private String sessionId = null;
	private long timestamp = 0L;
	private boolean success = true;
	private String transferIpAddress;
	private Long remainingDeviceRegistrations = 0L;
	List<RegisteredDevice> registeredDeviceList = new ArrayList<RegisteredDevice>();
	private String remainingNumberOfDeviceRegistration;
	
	public String getSessionId()
	{
		return sessionId;
	}
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}
	public long getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp(long timestamp)
	{
		this.timestamp = timestamp;
	}
	public boolean isSuccess()
	{
		return success;
	}
	public void setSuccess(boolean success)
	{
		this.success = success;
	}
	public String getTransferIpAddress()
	{
		return transferIpAddress;
	}
	public void setTransferIpAddress(String transferIpAddress)
	{
		this.transferIpAddress = transferIpAddress;
	}
	public List<RegisteredDevice> getRegisteredDeviceList()
	{
		return registeredDeviceList;
	}
	public void setRegisteredDeviceList(List<RegisteredDevice> registeredDeviceList)
	{
		this.registeredDeviceList = registeredDeviceList;
	}
	public Long getRemainingDeviceRegistrations()
	{
		return remainingDeviceRegistrations;
	}
	public void setRemainingDeviceRegistrations(Long remainingDeviceRegistrations)
	{
		this.remainingDeviceRegistrations = remainingDeviceRegistrations;
	}
	public String getRemainingNumberOfDeviceRegistration()
	{
		return remainingNumberOfDeviceRegistration;
	}
	public void setRemainingNumberOfDeviceRegistration(String remainingNumberOfDeviceRegistration)
	{
		this.remainingNumberOfDeviceRegistration = remainingNumberOfDeviceRegistration;
	}
	
}
