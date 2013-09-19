/**
 * 
 */
package com.rovi.dwam.test.service;

import org.testng.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rovi.dwam.exception.AuthorizationException;
import com.rovi.dwam.exception.DwamException;
import com.rovi.dwam.model.AccountRequest;
import com.rovi.dwam.model.AccountResponse;
import com.rovi.dwam.model.Customer;
import com.rovi.dwam.model.UpdateAccountRequest;
import com.rovi.dwam.service.IUserService;

/**
 * 
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 5, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-service.xml","/applicationContext-test.xml"})
public class UserServiceTest extends AbstractTestNGSpringContextTests// extends GenericTest
{

	@Autowired
	private IUserService userService;
	
	@Autowired
	private UpdateAccountRequest updateAccountRequest;
	
	@Autowired
	private AccountRequest accountRequest;
	
	@Autowired
	private AccountRequest invalidAccountRequest;
	
	
	@Test(enabled=false)
	public void testValidAuthentication() throws AuthorizationException, DwamException
	{
		String email = "jimuliautsav@gmail.com";
		Customer customer = userService.authenticate(email, "cybage");
		Assert.assertNotNull(customer.getSessionId());
		Assert.assertTrue(customer.isSuccess());
	}
	
	@Test(expectedExceptions=AuthorizationException.class, enabled=false)
	public void testInvalidAuthentication() throws AuthorizationException, DwamException
	{
		userService.authenticate("jimuliautsav@gmail.com", "invalidpassword");
	}
	
	@BeforeTest(groups="update")
	public void updateSetup(){
		System.out.println("Update setup called");
	}
	
	@Test
	public void testSample(){
		System.out.println("Dummy method for test");
	}
	
	/**
	 * Assumes that an entry already exists in the database with email: vishalshu@cybage.com & password: vishal123
	 * @throws DwamException
	 */
	@Test( groups="update", enabled=false )
	public void testUpdateUser() throws DwamException{
		System.out.println("Email of UpdateAccountRequest is : "+accountRequest.getEmail());
		Customer customer = userService.authenticate(accountRequest.getEmail(), accountRequest.getPassword());
		updateAccountRequest.setSessionId(customer.getSessionId());
		
		userService.updateCustomer(updateAccountRequest);
		
		AccountResponse cust = userService.getUserAccountInfo(customer.getSessionId());
		
		Assert.assertEquals(updateAccountRequest.getFirstName(), cust.getFirstName());
	}
	
	@Test(enabled=false)
	public void testSignup() throws DwamException{
		accountRequest.setEmail(System.currentTimeMillis()+accountRequest.getEmail());
		System.out.println(accountRequest.getEmail());
		AccountResponse response = userService.signup(accountRequest);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test(expectedExceptions=DwamException.class,enabled=false)
	public void testInvalidEmailSignup() throws DwamException{
		AccountResponse response = userService.signup(invalidAccountRequest);
		Assert.assertFalse(response.isSuccess());
	}
	
	@Test(expectedExceptions=DwamException.class, enabled=false)
	public void testExistingEmailSignup() throws DwamException{
		accountRequest.setEmail("vishal1shukla2@gmail.com");
		AccountResponse response = userService.signup(accountRequest);
		Assert.assertFalse(response.isSuccess());
	}
}