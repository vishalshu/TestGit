package my.example.mongo.ecom.repository.test;

import my.example.mongo.ecom.model.impl.MongoCategory;
import my.example.mongo.ecom.model.impl.MongoOrder;
import my.example.mongo.ecom.model.impl.MongoProduct;
import my.example.mongo.ecom.model.impl.MongoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;

/**
 * Created with IntelliJ IDEA.
 * User: vishalshu
 * Date: 9/4/13
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = "classpath:/test-spring-config.xml")
public abstract class AbstractMongoSpringRepoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    MongoTemplate mongoTemplate;

    @AfterSuite
    public void teardown() {
        if (mongoTemplate.getDb().getName().contains("test")) {
            for (Class<Object> c : getEntityClasses()) {
                String collectionName = c.getAnnotation(Document.class).collection();
                mongoTemplate.dropCollection(collectionName);
            }
        }
    }

    private <T extends Object> Class<T>[] getEntityClasses() {
        return new Class[]{MongoProduct.class, MongoCategory.class, MongoUser.class, MongoOrder.class};
    }
}
