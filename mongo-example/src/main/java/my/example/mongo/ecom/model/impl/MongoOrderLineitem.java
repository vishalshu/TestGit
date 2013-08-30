/**
 * 
 */
package my.example.mongo.ecom.model.impl;

import my.example.mongo.ecom.model.IOrderLineitem;
import my.example.mongo.ecom.model.IPrice;

import org.springframework.data.annotation.Id;

/**
 * @author vishalshu
 * 
 */
public class MongoOrderLineitem implements IOrderLineitem {

	@Id
	private String id;
	private String sku;
	private String name;
	private Double quantity;
	private IPrice price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public IPrice getPrice() {
		return price;
	}

	public void setPrice(IPrice price) {
		this.price = price;
	}

}
