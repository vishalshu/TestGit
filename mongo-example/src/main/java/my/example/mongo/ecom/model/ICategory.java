/**
 * 
 */
package my.example.mongo.ecom.model;

import java.util.Collection;

/**
 * @author vishalshu
 * 
 */
public interface ICategory extends IBaseEntity{

    /**
	 * @param
	 * @return the category name
	 */
	String getName();

	/**
	 * @param
	 * @return the description
	 */
	String getDescription();

	/**
	 * @param
	 * @return the parent category
	 */
	ICategory getParentCategory();

	/**
	 * @param
	 * @return the collection of child categories
	 */
	Collection<ICategory> getChildCategories(); 

}
