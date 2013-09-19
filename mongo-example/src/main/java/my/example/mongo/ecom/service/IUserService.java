package my.example.mongo.ecom.service;

import my.example.mongo.ecom.model.IAddress;
import my.example.mongo.ecom.model.IUser;

import java.util.LinkedHashSet;

public interface IUserService {
	IUser registerUser(IUser user);

	boolean authenticateUser(String username, String password);
	
	/**
	 * Search for users by username, firstName or lastName. Allows partial
	 * matches.
	 * 
	 * @param searchKey
	 * @return
	 */
	LinkedHashSet<IUser> searchUsers(String searchKey);

	boolean changeAddress(String username, IAddress address);

}
