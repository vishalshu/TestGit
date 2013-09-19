package com.rovi.dwam.exception;

public class ErrorResponse
{
	private String message;
	private String messageCode;
	private String[] messageParameters = null;
	private int errorCode = 0;
	private String category;
	private String categoryCode;
	private String title;
	private String link;
	private String linkTarget;
	private String success;

	public ErrorResponse()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponse(String message, String messageCode, String[] messageParameters, int errorCode, String category, String categoryCode, String title, String link, String linkTarget)
	{
		super();
		this.message = message;
		this.messageCode = messageCode;
		this.messageParameters = messageParameters;
		this.errorCode = errorCode;
		this.category = category;
		this.categoryCode = categoryCode;
		this.title = title;
		this.link = link;
		this.linkTarget = linkTarget;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getMessageCode()
	{
		return messageCode;
	}

	public void setMessageCode(String messageCode)
	{
		this.messageCode = messageCode;
	}

	public String[] getMessageParameters()
	{
		return messageParameters;
	}

	public void setMessageParameters(String[] messageParameters)
	{
		this.messageParameters = messageParameters;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getCategoryCode()
	{
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode)
	{
		this.categoryCode = categoryCode;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		if (title != null && title.length() == 0)
		{
			this.title = "";
		}
		else
		{
			this.title = title;
		}
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getLinkTarget()
	{
		return linkTarget;
	}

	public void setLinkTarget(String linkTarget)
	{
		this.linkTarget = linkTarget;
	}

	public String getSuccess()
	{
		return success;
	}

	public void setSuccess(String success)
	{
		this.success = success;
	}

	
}