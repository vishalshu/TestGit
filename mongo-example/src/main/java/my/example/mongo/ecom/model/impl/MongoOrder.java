/**
 * 
 */
package my.example.mongo.ecom.model.impl;

import java.util.ArrayList;
import java.util.List;

import my.example.mongo.ecom.model.IAddress;
import my.example.mongo.ecom.model.IOrder;
import my.example.mongo.ecom.model.IOrderLineitem;
import my.example.mongo.ecom.model.IUser;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vishalshu
 * 
 */
@Document(collection="orders")
public class MongoOrder implements IOrder {
	@Id
	private String id;
	@DBRef
	private IUser user;
	private List<IOrderLineitem> lineitems = new ArrayList<IOrderLineitem>();
	private IAddress shippingAddress;
	private Long subtotal;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IUser getUser() {
		return user;
	}

	public void setUser(IUser user) {
		this.user = user;
	}

	public List<IOrderLineitem> getLineitems() {
		return lineitems;
	}

	public void setLineitems(List<IOrderLineitem> lineitems) {
		this.lineitems = lineitems;
	}

	public IAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(IAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Long getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Long subtotal) {
		this.subtotal = subtotal;
	}

}
