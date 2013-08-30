/**
 * 
 */
package my.example.mongo.ecom.model;

/**
 * @author vishalshu
 *
 */
public interface IUser {

	String getId();
	
	String getUsername();
	
	String getPassword();
	
	IAddress getAddress();
}
