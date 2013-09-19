/**
 * 
 */
package my.example.mongo.ecom.model;

/**
 * @author vishalshu
 *
 */
public interface IUser extends IBaseEntity{

	String getId();
	
	String getUsername();
	
	String getPassword();
	
	IAddress getAddress();
}
