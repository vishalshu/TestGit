/**
 * 
 */
package my.example.mongo.ecom.model.util;

import java.util.UUID;

import my.example.mongo.ecom.model.impl.MongoAddress;
import my.example.mongo.ecom.model.impl.MongoUser;

/**
 * @author vishalshu
 * 
 */
public class UserBuilder {

	private AddressBuilder addressBuilder = AddressBuilder.aNew();
	private MongoUser user = new MongoUser();

	private UserBuilder() {
		user.setUsername(UUID.randomUUID().toString());
		user.setPassword("password");
		user.setAddress(addressBuilder.build());
	}

	public static UserBuilder aNew() {
		return new UserBuilder();
	}

	public UserBuilder withAddress(MongoAddress address) {
		user.setAddress(address);
		return this;
	}

	public UserBuilder withUsername(String username) {
		user.setUsername(username);
		return this;
	}

	public UserBuilder withPassword(String password) {
		user.setPassword(password);
		return this;
	}

	public UserBuilder withNoAddress() {
		user.setAddress(null);
		return this;
	}

	public MongoUser build() {
		return user;
	}
}
