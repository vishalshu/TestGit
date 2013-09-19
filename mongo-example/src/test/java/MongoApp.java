import com.mongodb.Mongo;
import com.mongodb.WriteConcern.Majority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

public class MongoApp {
	private static final Log log = LogFactory.getLog(MongoApp.class);

	public static void main(String[] args) throws Exception {
		SimpleMongoDbFactory factory = new SimpleMongoDbFactory(new Mongo(),
				"database");
		factory.setWriteConcern(Majority.SAFE);

		PagingAndSortingRepository<Person, String> repo = null;
		QPerson person = new QPerson("persons");
		//person.age.gt(50).and(person.name.)
		MongoTemplate mongoOps = new MongoTemplate(factory);
		String username = "Jigna";
		int age = 28;
		Person p = new Person(username, age);
		// Insert is used to initially store the object into the database.
		mongoOps.insert(p);
		log.info("Insert: " + p);
		// Find
		p = mongoOps.findById(p.getId(), Person.class);
		log.info("Found: " + p);
		// Update
		mongoOps.updateFirst(query(where("name").is(username)),
				update("age", age), Person.class);
		p = mongoOps.findOne(query(where("name").is(username)), Person.class);
		log.info("Updated: " + p);
		/*
		 * // Delete mongoOps.remove(p); // Check that deletion worked
		 */
		List<Person> people = mongoOps.findAll(Person.class);
		log.info("Number of people = : " + people.size());
		// mongoOps.dropCollection(Person.class);
	}
}