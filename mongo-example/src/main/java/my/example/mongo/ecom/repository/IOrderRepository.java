package my.example.mongo.ecom.repository;

import my.example.mongo.ecom.model.impl.MongoOrder;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IOrderRepository extends
PagingAndSortingRepository<MongoOrder, String> {

}
