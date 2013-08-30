/**
 * 
 */
package my.example.mongo.ecom.model.impl;

import java.util.Date;

import my.example.mongo.ecom.model.IHistoricalPrice;

/**
 * @author vishalshu
 * 
 */
public class MongoHistoricalPrice extends MongoPrice implements
		IHistoricalPrice {

	private Date startDate;
	private Date endDate;

	/**
	 * @see my.example.mongo.ecom.model.IHistoricalPrice#getStartDate()
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @see my.example.mongo.ecom.model.IHistoricalPrice#getEndDate()
	 */
	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
