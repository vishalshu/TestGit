/**
 * 
 */
package my.example.mongo.ecom.repository.test;

import my.example.mongo.ecom.model.impl.MongoCategory;
import my.example.mongo.ecom.model.util.CategoryBuilder;
import my.example.mongo.ecom.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/**
 * @author vishalshu
 * 
 */

public class CategoryRepositoryTest  extends AbstractMongoSpringRepoTest{

	@Autowired
	ICategoryRepository repository;

	private MongoCategory insertRootCategory() {
		MongoCategory category = CategoryBuilder.aNew()
				.withName("Root" + System.currentTimeMillis()).build();
		MongoCategory savedCategory = repository.save(category);
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
		MongoCategory savedCategory = repository.save(category);
		Assert.assertNotNull(savedCategory);
		Assert.assertNotNull(savedCategory.getParentCategory());
		Assert.assertEquals(savedCategory.getParentCategory(), category.getParentCategory());

	}

	@Test
	public void readCategoryWithChildren() {

		Page<MongoCategory> categories = repository.findAll(new PageRequest(0,
				10));
		Assert.assertTrue(categories.isFirstPage());
	}

}
