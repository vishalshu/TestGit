package my.example.mongo.ecom.repository;

import org.springframework.stereotype.Repository;

@Repository
public class IUserRepositoryImpl implements CustomUserRepository{

	@Override
	public void upsert() {
		System.out.println("upsert donw");
	}

}
