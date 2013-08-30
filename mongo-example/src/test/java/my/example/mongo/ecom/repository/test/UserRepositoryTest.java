/**
 * 
 */
package my.example.mongo.ecom.repository.test;

import my.example.mongo.ecom.model.IUser;
import my.example.mongo.ecom.model.impl.MongoUser;
import my.example.mongo.ecom.model.util.UserBuilder;
import my.example.mongo.ecom.repository.IUserRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author vishalshu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-config.xml")
public class UserRepositoryTest {

	@Autowired
	IUserRepository repository;

	@Test
	public void insertUser() {
		MongoUser user = UserBuilder.aNew().build();
		IUser savedUser = repository.save(user);
		Assert.assertNotNull(savedUser);
		Assert.assertNotNull(savedUser.getId());
	}

	@Test(expected = DuplicateKeyException.class)
	public void insertDuplicateUsername() {
		MongoUser user = UserBuilder.aNew().withUsername("vishalshu").build();
		repository.save(user);
	}

	@Test
	public void readsFirstPageCorrectly() {

		Page<MongoUser> users = repository.findAll(new PageRequest(0, 10));
		Assert.assertTrue(users.isFirstPage());
	}
}
