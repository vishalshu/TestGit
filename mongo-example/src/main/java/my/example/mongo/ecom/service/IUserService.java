package my.example.mongo.ecom.service;

import java.util.List;

import my.example.mongo.ecom.model.IAddress;
import my.example.mongo.ecom.model.IUser;

public interface IUserService {
	IUser registerUser(IUser user);

	/**
	 * Search for users by username, firstName or lastName. Allows partial
	 * matches.
	 * 
	 * @param searchKey
	 * @return
	 */
	List<IUser> searchUsers(String searchKey);

	boolean changeAddress(String userId, IAddress address);

}
