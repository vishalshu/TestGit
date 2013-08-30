/**
 * 
 */
package my.example.mongo.ecom.repository.test;

import my.example.mongo.ecom.model.impl.MongoCategory;
import my.example.mongo.ecom.model.util.CategoryBuilder;
import my.example.mongo.ecom.repository.ICategoryRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author vishalshu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-config.xml")
public class CategoryRepositoryTest {

	@Autowired
	ICategoryRepository categoryRepository;

	private MongoCategory insertRootCategory() {
		MongoCategory category = CategoryBuilder.aNew().withName("Root"+System.currentTimeMillis()).build();
		MongoCategory savedCategory = categoryRepository.save(category);
		return savedCategory;
	}

	@Test
	public void insertRootCategoryTest() {
		MongoCategory savedCategory = insertRootCategory();
		Assert.assertNotNull(savedCategory);
		Assert.assertNotNull(savedCategory.getId());
	}

	@Test
	public void insertCategoryWithParentTest() {
		MongoCategory category = CategoryBuilder.aNew()
				.withParent(insertRootCategory()).build();
		MongoCategory savedCategory = categoryRepository.save(category);
		Assert.assertNotNull(savedCategory);
		Assert.assertNotNull(savedCategory.getParentCategory());
	}

	/*
	 * @Test public void insertCategoryWithChildren() { MongoCategory user =
	 * CategoryBuilder.aNew().withChildCategories().build();
	 * repository.save(user); }
	 */

	@Test
	public void readCategoryWithChildren() {

		Page<MongoCategory> categories = categoryRepository.findAll(new PageRequest(0,
				10));
		Assert.assertTrue(categories.isFirstPage());
	}

}
