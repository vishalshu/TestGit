/**
 * 
 */
package my.example.mongo.ecom.model.impl;

import my.example.mongo.ecom.model.IOrder;
import my.example.mongo.ecom.model.IOrderLineitem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vishalshu
 * 
 */
@Document(collection = "orders")
public class MongoOrder implements IOrder {
	@Id
	private String id;
	@DBRef
	private MongoUser user;
	private List<IOrderLineitem> lineitems = new ArrayList<IOrderLineitem>();
	private MongoAddress shippingAddress;
	private Long subtotal = 0l;

	public MongoOrder() {
	}

	public MongoOrder(MongoUser user) {
		shippingAddress = user.getAddress();
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MongoUser getUser() {
		return user;
	}

	public void setUser(MongoUser user) {
		this.user = user;
	}

	public List<IOrderLineitem> getLineitems() {
		return lineitems;
	}

	public void setLineitems(List<IOrderLineitem> lineitems) {
		for (IOrderLineitem lineitem : lineitems) {
			addLineitem(lineitem);
		}
	}

	public MongoAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(MongoAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Long getSubtotal() {
		return subtotal;
	}

	public void addLineitem(IOrderLineitem lineitem) {
		lineitems.add(lineitem);
		subtotal += lineitem.getPrice().getSalePrice();
	}

}
