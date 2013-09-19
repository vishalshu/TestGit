package my.example.mongo.ecom.repository;

import my.example.mongo.ecom.model.IUser;
import my.example.mongo.ecom.model.impl.MongoOrder;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IOrderRepository extends
		PagingAndSortingRepository<MongoOrder, String> {

	List<MongoOrder> findByShippingAddressCity(String city);

	List<MongoOrder> findByUser(IUser user);
}
