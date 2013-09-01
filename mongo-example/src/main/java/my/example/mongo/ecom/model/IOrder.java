/**
 * 
 */
package my.example.mongo.ecom.model;

import java.util.List;

/**
 * @author vishalshu
 * 
 */
public interface IOrder {

	String getId();

	IUser getUser();

	List<IOrderLineitem> getLineitems();

	void addLineitem(IOrderLineitem lineitem);

	IAddress getShippingAddress();

	Long getSubtotal();
}
