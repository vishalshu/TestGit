/**
 * 
 */
package my.example.mongo.ecom.model.util;

import my.example.mongo.ecom.model.ICategory;
import my.example.mongo.ecom.model.impl.MongoPrice;
import my.example.mongo.ecom.model.impl.MongoProduct;

import java.util.UUID;

/**
 * @author vishalshu
 * 
 */
public class ProductBuilder {
	private MongoProduct product = new MongoProduct();

	/**
	 * 
	 */
	private ProductBuilder() {
		product.setPrice(new MongoPrice(1000l, 900l));
		product.setSku("productName" + 1);
		product.setName("productName");
		product.setSlug(UUID.randomUUID().toString());
		product.setDescription("Some description ");
		product.setAverageRating(0f);
		product.addTag("dummy");
		product.addDetail("color", "black");
		product.setTotalReviews(0);
		product.setMainCategory(CategoryBuilder.aNew().build());
	}

	public static ProductBuilder aNew() {
		return new ProductBuilder();
	}

	public static ProductBuilder aNew(String slug) {
		return new ProductBuilder().withSlug(slug);
	}

	public ProductBuilder withMainCategory(ICategory category) {
		product.setMainCategory(category);
		return this;
	}

	public MongoProduct build() {
		return product;
	}

	public ProductBuilder withSlug(String slug) {
		product.setSlug(slug);
		return this;
	}

}
