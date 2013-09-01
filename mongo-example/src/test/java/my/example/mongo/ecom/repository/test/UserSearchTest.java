package my.example.mongo.ecom.repository.test;

import java.util.ArrayList;
import java.util.List;

import my.example.mongo.ecom.model.IUser;
import my.example.mongo.ecom.model.impl.MongoUser;
import my.example.mongo.ecom.model.util.UserBuilder;
import my.example.mongo.ecom.repository.IUserRepository;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-config.xml")
public class UserSearchTest {

	@Autowired
	private static IUserRepository repository;

	private static List<String> ids = new ArrayList<String>();

	@BeforeClass
	public static void setup() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/spring-config.xml");
		repository = ctx.getBean(IUserRepository.class);
		bulkInsertUsers();
	}

	private static void bulkInsertUsers() {
		String[] usernames = new String[]{"vishal","abc","xyz","klsf","klowe","akls","pwpl","laksd"};
		for (int i = 0; i < 50; i++) {
			MongoUser user = UserBuilder.aNew().withUsername(usernames[i%8]+System.currentTimeMillis()).withAge(i + 18).build();
			IUser savedUser = repository.save(user);
			ids.add(savedUser.getId());
		}
	}

	@Test
	public void testFindByAgeBetween() {
		List<MongoUser> users = repository.findByAgeBetween(20, 40,
				new PageRequest(0, 5, new Sort("username")));
		Assert.assertNotNull(users);
		Assert.assertTrue(!users.isEmpty());
	}
	
	@Test
	public void testFindUsersByUsernamePrefix() {
		List<MongoUser> users = repository.findByUsernamePrefix("vishal");
		Assert.assertNotNull(users);
		Assert.assertTrue(!users.isEmpty());
	}

	@AfterClass
	public static void teardown() {
		for (String id : ids) {
			repository.delete(id);
		}
	}

}
