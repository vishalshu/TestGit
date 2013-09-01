package my.example.mongo.ecom.model.util;

import my.example.mongo.ecom.model.impl.MongoOrderLineitem;
import my.example.mongo.ecom.model.impl.MongoPrice;

public class OrderLineitemBuilder {
	private MongoOrderLineitem lineitem;

	/**
	 * 
	 */
	private OrderLineitemBuilder() {
		lineitem = new MongoOrderLineitem("itemName", 2.0, new MongoPrice(
				10000l, 10000l));
	}

	public static OrderLineitemBuilder aNew() {
		return new OrderLineitemBuilder();
	}
	
	public OrderLineitemBuilder withItem(String name){
		lineitem.setName(name);
		return this;
	}
	
	public OrderLineitemBuilder withQty(Double qty){
		lineitem.setQuantity(qty);
		return this;
	}
	
	public OrderLineitemBuilder withPrice(Long price){
		lineitem.setPrice(new MongoPrice(price, price));
		return this;
	}

	public MongoOrderLineitem build() {
		return lineitem;
	}
}
