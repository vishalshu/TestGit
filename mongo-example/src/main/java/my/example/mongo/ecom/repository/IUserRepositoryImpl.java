package my.example.mongo.ecom.repository;

import com.mongodb.WriteResult;
import my.example.mongo.ecom.model.impl.MongoAddress;
import my.example.mongo.ecom.model.impl.MongoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class IUserRepositoryImpl implements CustomUserRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * @see my.example.mongo.ecom.repository.CustomUserRepository#updateAddress(java.lang.String,
	 *      my.example.mongo.ecom.model.impl.MongoAddress)
	 */
	@Override
	public int updateAddress(String username, MongoAddress address) {
		WriteResult wr = mongoTemplate.updateFirst(
				Query.query(Criteria.where("username").is(username)),
				new Update().set("address", address), MongoUser.class);
		return wr.getN();
	}

}
