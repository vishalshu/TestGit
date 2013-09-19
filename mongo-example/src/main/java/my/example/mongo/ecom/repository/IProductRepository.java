/**
 * 
 */
package my.example.mongo.ecom.repository;

import my.example.mongo.ecom.model.impl.MongoProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author vishalshu
 * 
 */
public interface IProductRepository extends
		PagingAndSortingRepository<MongoProduct, String> {

}
