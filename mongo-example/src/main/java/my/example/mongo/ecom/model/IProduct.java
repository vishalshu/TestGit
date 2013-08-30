package my.example.mongo.ecom.model;

import java.util.Collection;
import java.util.Map;

/**
 * @author vishalshu
 * 
 */
public interface IProduct {

	/**
	 * @param
	 * @return the product id
	 */
	String getId();

	/**
	 * @param
	 * @return the product name
	 */
	String getName();

	/**
	 * @param
	 * @return the description
	 */
	String getDescription();

	/**
	 * @param
	 * @return the slug
	 */
	String getSlug();

	/**
	 * @param
	 * @return the sku
	 */
	String getSku();

	/**
	 * @param
	 * @return the details
	 */
	Map<String, String> getDetails();

	/**
	 * @param
	 * @return the categories
	 */
	Collection<ICategory> getCategories();

	/**
	 * @param
	 * @return the main category
	 */
	ICategory getMainCategory();

	/**
	 * @param
	 * @return the tags
	 */
	Collection<String> getTags();

	Integer getTotalReviews();

	Float getAverageRating();

	IPrice getPrice();

	Collection<IHistoricalPrice> getPriceHistory();

}
