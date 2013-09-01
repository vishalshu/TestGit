/**
 * 
 */
package my.example.mongo.ecom.service.test;

import my.example.mongo.ecom.model.IUser;
import my.example.mongo.ecom.model.util.UserBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author vishalshu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-config.xml")
public class UserServiceTest {

	@Test
	public void addNewUser(){
		IUser user = UserBuilder.aNew().build();
		
		
	}
	
}
