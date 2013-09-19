package com.rovi.dwam.model;

/**
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * @author Rovi Media Solutions Engineering: utsavj
 * Created: Jun 11, 2013
 * Copyright RoviCorp 2013 
 *
 * Developer Comment(s):
 */
public class AccountRequest extends BaseEntity
{

	private String firstName;
	private String lastName;
	private Integer securityQuestionId;
	private String securityQuestionAnswer;
	private String email;
	private String password;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}

	public Integer getSecurityQuestionId()
	{
		return securityQuestionId;
	}

	public void setSecurityQuestionId( Integer securityQuestionId )
	{
		this.securityQuestionId = securityQuestionId;
	}

	public String getSecurityQuestionAnswer()
	{
		return securityQuestionAnswer;
	}

	public void setSecurityQuestionAnswer( String securityQuestionAnswer )
	{
		this.securityQuestionAnswer = securityQuestionAnswer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
