/**
 * 
 */
package my.example.mongo.ecom.repository;

import java.util.List;

import my.example.mongo.ecom.model.impl.MongoUser;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author vishalshu
 * 
 */
public interface IUserRepository extends
		PagingAndSortingRepository<MongoUser, String> {
	List<MongoUser> findByAgeBetween(Integer from, Integer to, Pageable pageable);

	@Query("{'username':{$regex:'\\^?0\\'}}")
	List<MongoUser> findByUsernamePrefix(String prefix);
}
