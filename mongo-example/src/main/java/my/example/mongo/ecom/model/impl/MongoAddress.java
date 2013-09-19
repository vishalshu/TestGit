/**
 * 
 */
package my.example.mongo.ecom.model.impl;

import my.example.mongo.ecom.model.IAddress;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @author vishalshu
 * 
 */
public class MongoAddress implements IAddress {

	private String street;
	@Indexed
	private String city;
	private String state;
	private String zip;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
