package my.example.mongo.ecom.service.test;

import my.example.mongo.ecom.model.IUser;
import my.example.mongo.ecom.model.impl.MongoAddress;
import my.example.mongo.ecom.model.impl.MongoUser;
import my.example.mongo.ecom.model.util.AddressBuilder;
import my.example.mongo.ecom.model.util.UserBuilder;
import my.example.mongo.ecom.repository.IUserRepository;
import my.example.mongo.ecom.service.IUserService;
import my.example.mongo.ecom.service.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.mockito.Mockito.when;

/**
 * @author vishalshu
 * 
 */

public class UserServiceTest {

	@Mock
	IUserRepository userRepository;

	@InjectMocks
	IUserService userService = new UserServiceImpl();

	@Mock
	MongoUser user;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testRegisterUser() {
		when(userRepository.save(user)).thenReturn(user);
		IUser actual = userService.registerUser(user);
		Assert.assertNotNull(actual);
		Assert.assertEquals(user, actual);
	}

	@SuppressWarnings("unused")
	@DataProvider
	private static final Object[][] getAuthData() {
		return new Object[][] { { "username", "password", true },
				{ "username", "wrong", false },
				{ "invalid", "password", false } };
	}

	@Test(groups = "authentication", dataProvider = "getAuthData")
	public void testAuthenticateUser(String username, String password,
			boolean expected) {

		when(userRepository.findByUsername("username")).thenReturn(
				UserBuilder.aNew().withUsername("username")
						.withPassword("password").build());

		Assert.assertEquals(userService.authenticateUser(username, password),
				expected);
	}

	@Test
	public void testSearchUser() {

		when(userRepository.findByUsernameRegex("(?i)sha(?-i)")).thenReturn(
				Arrays.asList(new MongoUser[] {
						UserBuilder.aNew().withUsername("vishalname1").build(),
						UserBuilder.aNew().withUsername("Nishant").build(),
						UserBuilder.aNew().withUsername("Nisha").build(),
						UserBuilder.aNew().withUsername("TriSHA").build() }));

		when(userRepository.findByFirstNameRegex("(?i)sha(?-i)")).thenReturn(
				Arrays.asList(new MongoUser[] {
						UserBuilder.aNew().withUsername("vishalname1")
								.withFirstName("VishalS").build(),
						UserBuilder.aNew().withUsername("Nishant")
								.withFirstName("NishantP").build(),
						UserBuilder.aNew().withUsername("Nisha")
								.withFirstName("NishaM").build(),
						UserBuilder.aNew().withUsername("TriSHA")
								.withFirstName("ATriSHA").build() }));

		when(userRepository.findByLastNameRegex("(?i)sha(?-i)"))
				.thenReturn(
						Arrays.asList(new MongoUser[] { UserBuilder.aNew()
								.withUsername("bhavesh").withLastName("Shah")
								.build() }));

		LinkedHashSet<IUser> users = userService.searchUsers("sha");
		Assert.assertNotNull(users);
		Assert.assertTrue(users.size() == 5);
	}

	@Test
	public void testChangeAddress() {
		MongoAddress address = AddressBuilder.aNew().build();
		when(userRepository.updateAddress("vishal", address)).thenReturn(1);
		boolean updated = userService.changeAddress("vishal", address);
		Assert.assertTrue(updated);
	}

}
