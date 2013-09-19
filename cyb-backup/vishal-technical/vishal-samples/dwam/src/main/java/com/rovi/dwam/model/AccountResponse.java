package com.rovi.dwam.model;

import java.util.Date;

/**
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: utsavj Created: Jun 11, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
public class AccountResponse extends BaseEntity
{
	private Long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private Integer securityQuestionId;
	private String securityQuestionAnswer;
	private Date registeredTimestamp;
	private String emailValidatedIndicator;
	private String communityInd;
	private Integer revokeStatus;
	private Date communityValidationTimestamp;
	private Date emailValidationTimestamp;
	private String accountLanguageCode;
	private String accountCountryCode;
	private String exchangeData;
	private String verifierString;

	public AccountResponse()
	{
	}

	public Long getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(Long customerId)
	{
		this.customerId = customerId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Integer getSecurityQuestionId()
	{
		return securityQuestionId;
	}

	public void setSecurityQuestionId(Integer securityQuestionId)
	{
		this.securityQuestionId = securityQuestionId;
	}

	public String getSecurityQuestionAnswer()
	{
		return securityQuestionAnswer;
	}

	public void setSecurityQuestionAnswer(String securityQuestionAnswer)
	{
		this.securityQuestionAnswer = securityQuestionAnswer;
	}

	public Date getRegisteredTimestamp()
	{
		return registeredTimestamp;
	}

	public void setRegisteredTimestamp(Date registeredTimestamp)
	{
		this.registeredTimestamp = registeredTimestamp;
	}

	public String getEmailValidatedIndicator()
	{
		return emailValidatedIndicator;
	}

	public void setEmailValidatedIndicator(String emailValidatedIndicator)
	{
		this.emailValidatedIndicator = emailValidatedIndicator;
	}

	public String getCommunityInd()
	{
		return communityInd;
	}

	public void setCommunityInd(String communityInd)
	{
		this.communityInd = communityInd;
	}

	public Integer getRevokeStatus()
	{
		return revokeStatus;
	}

	public void setRevokeStatus(Integer revokeStatus)
	{
		this.revokeStatus = revokeStatus;
	}

	public Date getCommunityValidationTimestamp()
	{
		return communityValidationTimestamp;
	}

	public void setCommunityValidationTimestamp(Date communityValidationTimestamp)
	{
		this.communityValidationTimestamp = communityValidationTimestamp;
	}

	public Date getEmailValidationTimestamp()
	{
		return emailValidationTimestamp;
	}

	public void setEmailValidationTimestamp(Date emailValidationTimestamp)
	{
		this.emailValidationTimestamp = emailValidationTimestamp;
	}

	public String getAccountLanguageCode()
	{
		return accountLanguageCode;
	}

	public void setAccountLanguageCode(String accountLanguageCode)
	{
		this.accountLanguageCode = accountLanguageCode;
	}

	public String getAccountCountryCode()
	{
		return accountCountryCode;
	}

	public void setAccountCountryCode(String accountCountryCode)
	{
		this.accountCountryCode = accountCountryCode;
	}

	/**
	 * @return the exchangeData
	 */
	public String getExchangeData()
	{
		return exchangeData;
	}

	/**
	 * @param exchangeData
	 *            the exchangeData to set
	 */
	public void setExchangeData(String exchangeData)
	{
		this.exchangeData = exchangeData;
	}

	/**
	 * @return the verifierString
	 */
	public String getVerifierString()
	{
		return verifierString;
	}

	/**
	 * @param verifierString
	 *            the verifierString to set
	 */
	public void setVerifierString(String verifierString)
	{
		this.verifierString = verifierString;
	}
}
