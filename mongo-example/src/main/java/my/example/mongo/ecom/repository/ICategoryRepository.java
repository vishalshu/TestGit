/**
 * 
 */
package my.example.mongo.ecom.repository;

import my.example.mongo.ecom.model.impl.MongoCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;

/**
 * @author vishalshu
 *
 */
public interface ICategoryRepository extends PagingAndSortingRepository<MongoCategory, String>{

	/*@Component
	public class AfterSaveListener extends AbstractMongoEventListener<MongoCategory>{
		
		@Autowired
		ICategoryRepository repository;
		
		*//** 
		 * @see org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener#onAfterSave(java.lang.Object, com.mongodb.DBObject)
		 *//*
		@Override
		public void onAfterSave(MongoCategory source, DBObject dbo) {
			if(source.getParentCategory()!=null)
			repository.save(source.getParentCategory());
		}
	}*/
}
