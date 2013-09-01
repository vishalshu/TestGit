package my.example.mongo.ecom.model.util;

import my.example.mongo.ecom.model.IOrderLineitem;
import my.example.mongo.ecom.model.IUser;
import my.example.mongo.ecom.model.impl.MongoOrder;

public class OrderBuilder {
	private MongoOrder order;

	/**
	 * 
	 */
	private OrderBuilder(IUser user) {
		order = new MongoOrder(user);
	}

	public static OrderBuilder aNew(IUser user) {
		return new OrderBuilder(user);
	}

	public MongoOrder build() {
		return order;
	}

	public OrderBuilder withLineitem(IOrderLineitem... lineitems) {
		for (IOrderLineitem li : lineitems) {
			order.addLineitem(li);
		}
		return this;
	}

}
