/**
 * 
 */
package my.example.mongo.ecom.repository;

import my.example.mongo.ecom.model.impl.MongoUser;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author vishalshu
 * 
 */
public interface IUserRepository extends
		PagingAndSortingRepository<MongoUser, String> {

}
