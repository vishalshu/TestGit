package com.rovi.dwam.exception;

import java.util.List;

public class BaseErrorResponse
{
	private List<ErrorResponse> errorResponse;

	public BaseErrorResponse()
	{
		// TODO Auto-generated constructor stub
	}

	public List<ErrorResponse> getErrorResponse()
	{
		return errorResponse;
	}

	public void setErrorResponse(List<ErrorResponse> errorResponse)
	{
		this.errorResponse = errorResponse;
	}

	
}