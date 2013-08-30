/**
 * 
 */
package my.example.mongo.ecom.model.util;

import java.util.UUID;

import my.example.mongo.ecom.model.impl.MongoCategory;

/**
 * @author vishalshu
 * 
 */
public class CategoryBuilder {
	private MongoCategory category = new MongoCategory();

	/**
	 * 
	 */
	private CategoryBuilder() {
		category.setName(UUID.randomUUID().toString());
		category.setDescription("Some description ");
	}

	public static CategoryBuilder aNew() {
		return new CategoryBuilder();
	}

	public static CategoryBuilder aNew(String name) {
		return new CategoryBuilder().withName(name);
	}

	public MongoCategory build() {
		return category;
	}

	public CategoryBuilder withName(String name) {
		category.setName(name);
		return this;
	}

	public CategoryBuilder withParent() {
		category.setParentCategory(this.aNew().build());
		return this;
	}
	
	public CategoryBuilder withParent(MongoCategory parentCategory) {
		category.setParentCategory(parentCategory);
		return this;
	}

}
