/**
 * 
 */
package my.example.mongo.ecom.model.impl;

import my.example.mongo.ecom.model.ICategory;
import my.example.mongo.ecom.model.IHistoricalPrice;
import my.example.mongo.ecom.model.IPrice;
import my.example.mongo.ecom.model.IProduct;
import my.example.mongo.ext.CascadeSave;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * @author vishalshu
 * 
 */
@Document(collection = "products")
public class MongoProduct implements IProduct {

	@Id
	private String id;
	private String name;
	private String description;
	@Indexed(unique = true)
	private String slug;
	private String sku;
	private Map<String, String> details = new HashMap<String, String>();
	@DBRef
	//@CascadeSave
	private Set<ICategory> categories = new LinkedHashSet<ICategory>();
	@DBRef
	@CascadeSave
	private ICategory mainCategory;
	private Set<String> tags = new HashSet<String>();
	private Integer totalReviews;
	private Float averageRating;
	private IPrice price;
	private Set<IHistoricalPrice> priceHistory = new LinkedHashSet<IHistoricalPrice>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	public Set<ICategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<ICategory> categories) {
		this.categories = categories;
	}

	public ICategory getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(ICategory mainCategory) {
		this.mainCategory = mainCategory;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Integer getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(Integer totalReviews) {
		this.totalReviews = totalReviews;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public IPrice getPrice() {
		return price;
	}

	public void setPrice(IPrice price) {
		this.price = price;
	}

	public Set<IHistoricalPrice> getPriceHistory() {
		return priceHistory;
	}

	public void setPriceHistory(Set<IHistoricalPrice> priceHistory) {
		this.priceHistory = priceHistory;
	}

	public boolean addTag(String tag) {
		return tags.add(tag);
	}

	public void addDetail(String key, String value) {
		details.put(key, value);
	}

}
