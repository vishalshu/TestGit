/**
 * 
 */
package my.example.mongo.ecom.model;

import java.util.Date;

/**
 * @author vishalshu
 * 
 */
public interface IHistoricalPrice extends IPrice {

	Date getStartDate();

	Date getEndDate();
}
