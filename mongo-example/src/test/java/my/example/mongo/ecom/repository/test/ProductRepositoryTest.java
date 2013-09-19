/**
 *
 */
package my.example.mongo.ecom.repository.test;

import my.example.mongo.ecom.model.impl.MongoCategory;
import my.example.mongo.ecom.model.impl.MongoProduct;
import my.example.mongo.ecom.model.util.CategoryBuilder;
import my.example.mongo.ecom.model.util.ProductBuilder;
import my.example.mongo.ecom.repository.ICategoryRepository;
import my.example.mongo.ecom.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * @author vishalshu
 */

public class ProductRepositoryTest extends AbstractMongoSpringRepoTest{

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ICategoryRepository categoryRepository;

    MongoCategory category;

    @BeforeMethod
    public void setupForInsertProduct() {
        category = categoryRepository
                .save(CategoryBuilder.aNew().build());
        Assert.assertNotNull(category);
    }

    @Test
    public void insertProductTest() {
        MongoProduct product = productRepository.save(ProductBuilder.aNew()
                .withMainCategory(category).build());
        Assert.assertNotNull(product);
        Assert.assertNotNull(product.getId());
    }

    @Test
    public void readProduct() {
        Page<MongoProduct> products = productRepository
                .findAll(new PageRequest(0, 10));
        Assert.assertTrue(products.isFirstPage());
    }

}
