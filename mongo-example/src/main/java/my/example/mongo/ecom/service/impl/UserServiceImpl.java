/**
 * 
 */
package my.example.mongo.ecom.service.impl;

import my.example.mongo.ecom.model.IAddress;
import my.example.mongo.ecom.model.IUser;
import my.example.mongo.ecom.model.impl.MongoAddress;
import my.example.mongo.ecom.model.impl.MongoUser;
import my.example.mongo.ecom.repository.IUserRepository;
import my.example.mongo.ecom.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;

/**
 * @author vishalshu
 * 
 */
public class UserServiceImpl implements IUserService {

	IUserRepository userRepository;

	public UserServiceImpl() {
	}

	/**
	 * 
	 */
	@Autowired
	public UserServiceImpl(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * @see my.example.mongo.ecom.service.IUserService#registerUser(my.example.mongo.ecom.model.IUser)
	 */
	public IUser registerUser(IUser user) {
		return userRepository.save((MongoUser) user);
	}

	/**
	 * 
	 * @see my.example.mongo.ecom.service.IUserService#searchUsers(java.lang.String)
	 */
	public LinkedHashSet<IUser> searchUsers(String searchKey) {
		String regex = "(?i)" + searchKey + "(?-i)";
		LinkedHashSet<IUser> users = new LinkedHashSet<IUser>();
		users.addAll(userRepository.findByUsernameRegex(regex));
		users.addAll(userRepository.findByFirstNameRegex(regex));
		users.addAll(userRepository.findByLastNameRegex(regex));
		return users;
	}

	/**
	 * @see my.example.mongo.ecom.service.IUserService#changeAddress(java.lang.String,
	 *      my.example.mongo.ecom.model.IAddress)
	 */
	public boolean changeAddress(String username, IAddress address) {
		int result = userRepository.updateAddress(username,
				(MongoAddress) address);
		return result == 1 ? true : false;
	}

	/**
	 * @see my.example.mongo.ecom.service.IUserService#authenticateUser(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean authenticateUser(String username, String password) {
		boolean authenticated = false;
		IUser user = userRepository.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			authenticated = true;
		}
		return authenticated;
	}

}
