package com.rovi.dwam.model;

/**
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * @author Rovi Media Solutions Engineering: utsavj
 * Created: Jun 21, 2013
 * Copyright RoviCorp 2013 
 *
 * Developer Comment(s):
 */
public class RegisteredDevice
{
	private String deviceName;
	private String deviceType;
	private String deviceTypeDesc;
	private Long deviceId;
	private long deviceRegisteredDate;

	/**
	 * @return the deviceId
	 */
	public Long getDeviceId()
	{
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(Long deviceId)
	{
		this.deviceId = deviceId;
	}


	/**
	 * @return the deviceName
	 */
	public String getDeviceName()
	{
		return deviceName;
	}

	/**
	 * @param deviceName
	 *            the deviceName to set
	 */
	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}

	/**
	 * @return the deviceType
	 */
	public String getDeviceType()
	{
		return deviceType;
	}

	/**
	 * @param deviceType
	 *            the deviceType to set
	 */
	public void setDeviceType(String deviceType)
	{
		this.deviceType = deviceType;
	}

	public String getDeviceTypeDesc()
	{
		return deviceTypeDesc;
	}

	public void setDeviceTypeDesc(String deviceTypeDesc)
	{
		this.deviceTypeDesc = deviceTypeDesc;
	}

	/**
	 * @return the deviceRegisteredDate
	 */
	public long getDeviceRegisteredDate()
	{
		return deviceRegisteredDate;
	}

	/**
	 * @param deviceRegisteredDate
	 *            the deviceRegisteredDate to set
	 */
	public void setDeviceRegisteredDate(long deviceRegisteredDate)
	{
		this.deviceRegisteredDate = deviceRegisteredDate;
	}
}
