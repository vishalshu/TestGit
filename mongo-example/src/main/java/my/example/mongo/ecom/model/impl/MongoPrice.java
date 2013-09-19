/**
 * 
 */
package my.example.mongo.ecom.model.impl;

import my.example.mongo.ecom.model.IPrice;

/**
 * @author vishalshu
 * 
 */
public class MongoPrice implements IPrice {

	private Long retailPrice;
	private Long salePrice;

	public MongoPrice(Long retail, Long sale) {
		retailPrice = retail;
		salePrice = sale;
	}

	/**
	 * 
	 */
	public MongoPrice() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see my.example.mongo.ecom.model.IPrice#getRetailPrice()
	 */
	public Long getRetailPrice() {
		return retailPrice;
	}

	/**
	 * @see my.example.mongo.ecom.model.IPrice#getSalePrice()
	 */
	public Long getSalePrice() {
		return salePrice;
	}

	public void setRetailPrice(Long retailPrice) {
		this.retailPrice = retailPrice;
	}

	public void setSalePrice(Long salePrice) {
		this.salePrice = salePrice;
	}

}
