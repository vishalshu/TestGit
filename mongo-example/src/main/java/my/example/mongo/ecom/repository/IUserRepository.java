/**
 * 
 */
package my.example.mongo.ecom.repository;

import my.example.mongo.ecom.model.impl.MongoUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author vishalshu
 * 
 */
public interface IUserRepository extends CustomUserRepository,
		PagingAndSortingRepository<MongoUser, String> {
	List<MongoUser> findByAgeBetween(Integer from, Integer to, Pageable pageable);

	MongoUser findByUsername(String username);
	
	List<MongoUser> findByUsernameRegex(String regex);
	List<MongoUser> findByFirstNameRegex(String regex);
	List<MongoUser> findByLastNameRegex(String regex);
	
}
