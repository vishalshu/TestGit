package com.rovi.dwam.model;

public class RegisteredDeviceDTO
{
	private String deviceName;
	private String deviceType;
	private String deviceRegisteredDate;
	
	public String getDeviceName()
	{
		return deviceName;
	}
	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}
	public String getDeviceType()
	{
		return deviceType;
	}
	public void setDeviceType(String deviceType)
	{
		this.deviceType = deviceType;
	}
	public String getDeviceRegisteredDate()
	{
		return deviceRegisteredDate;
	}
	public void setDeviceRegisteredDate(String deviceRegisteredDate)
	{
		this.deviceRegisteredDate = deviceRegisteredDate;
	}
	
	
}
