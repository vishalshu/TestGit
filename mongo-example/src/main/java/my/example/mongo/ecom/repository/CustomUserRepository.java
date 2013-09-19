package my.example.mongo.ecom.repository;

import my.example.mongo.ecom.model.impl.MongoAddress;

public interface CustomUserRepository {
	int updateAddress(String username, MongoAddress address);
}
