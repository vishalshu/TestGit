/**
 * 
 */
package my.example.mongo.ecom.model;

/**
 * @author vishalshu
 *
 */
public interface IOrderLineitem {

	String getId();
	String getSku();
	String getName();
	Double getQuantity();
	IPrice getPrice();
}
