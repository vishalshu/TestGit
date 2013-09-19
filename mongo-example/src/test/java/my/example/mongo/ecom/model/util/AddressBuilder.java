/**
 * 
 */
package my.example.mongo.ecom.model.util;

import my.example.mongo.ecom.model.impl.MongoAddress;

/**
 * @author vishalshu
 * 
 */
public class AddressBuilder {
	private MongoAddress address = new MongoAddress();

	/**
	 * 
	 */
	private AddressBuilder() {
		address.setCity("DefaultCity");
		address.setState("DefaultState");
		address.setStreet("DefaultStreet");
		address.setZip("12345");
	}

	public static AddressBuilder aNew() {
		return new AddressBuilder();
	}

	public MongoAddress build() {
		return address;
	}

	public AddressBuilder withNoCity() {
		address.setCity(null);
		return this;
	}

	public AddressBuilder withCity(String city) {
		address.setCity(city);
		return this;
	}

	public AddressBuilder withState(String state) {
		address.setState(state);
		return this;
	}

	public AddressBuilder withStreet(String street) {
		address.setStreet(street);
		return this;
	}

	public AddressBuilder withZip(String zip) {
		address.setZip(zip);
		return this;
	}
}
