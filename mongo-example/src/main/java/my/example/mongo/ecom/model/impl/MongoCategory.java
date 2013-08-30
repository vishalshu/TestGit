/**
 * 
 */
package my.example.mongo.ecom.model.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import my.example.mongo.ecom.model.ICategory;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vishalshu
 * 
 */
@Document(collection = "categories")
public class MongoCategory implements ICategory {
	@Id
	private String id;
	private String name;
	private String description;
	@DBRef
	private MongoCategory parentCategory;
	@Transient
	private transient Set<ICategory> childCategories = new LinkedHashSet<ICategory>();

	/**
	 * 
	 */
	public MongoCategory() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * @param id
	 * @param name
	 */
	public MongoCategory(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



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

	public MongoCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(MongoCategory parentCategory) {
		this.parentCategory = parentCategory;
		if (parentCategory != null) {
			parentCategory.addChildCategory(this);
		}
	}

	public Set<ICategory> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<ICategory> childCategories) {
		this.childCategories = childCategories;
	}

	public boolean addChildCategory(ICategory childCategory) {
		return childCategories.add(childCategory);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MongoCategory other = (MongoCategory) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
