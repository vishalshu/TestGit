/**
 * 
 */
package com.rovi.dwam.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.rovi.dwam.service.IUserService;

/**
 * @author vishalshu
 *
 */
@ContextConfiguration(locations = { "/applicationContext-test.xml","/META-INF/spring/applicationContext-service.xml"})
public class UserRegistrationTest extends AbstractTestNGSpringContextTests{

	@Autowired
	private IUserService userService;
	

}
