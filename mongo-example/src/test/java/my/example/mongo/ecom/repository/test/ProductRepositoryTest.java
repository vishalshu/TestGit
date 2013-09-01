/**
 * 
 */
package my.example.mongo.ecom.repository.test;

import my.example.mongo.ecom.model.impl.MongoCategory;
import my.example.mongo.ecom.model.impl.MongoProduct;
import my.example.mongo.ecom.model.util.ProductBuilder;
import my.example.mongo.ecom.repository.ICategoryRepository;
import my.example.mongo.ecom.repository.IProductRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author vishalshu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-config.xml")
public class ProductRepositoryTest {

	@Autowired
	IProductRepository productRepository;

	@Autowired
	ICategoryRepository categoryRepository;

	@Test
	public void insertProductTest() {
		MongoCategory category = categoryRepository
				.findOne("5221e8ec52f2df998109611b");
		Assert.assertNotNull(category);
		category.setName("changedCategoryName2");
		MongoProduct product = productRepository.save(ProductBuilder.aNew()
				.withMainCategory(category).build());
		Assert.assertNotNull(product);
		Assert.assertNotNull(product.getId());
	}

	/*
	 * @Test public void insertCategoryWithParentTest() { MongoProduct product =
	 * ProductBuilder.aNew("product") .build(); MongoProduct savedProduct=
	 * productRepository.save(product);
	 * 
	 * }
	 */
	/*
	 * @Test public void insertCategoryWithChildren() { MongoCategory user =
	 * CategoryBuilder.aNew().withChildCategories().build();
	 * repository.save(user); }
	 */

	@Test
	public void readProduct() {

		Page<MongoProduct> products = productRepository
				.findAll(new PageRequest(0, 10));
		Assert.assertTrue(products.isFirstPage());
	}

}
