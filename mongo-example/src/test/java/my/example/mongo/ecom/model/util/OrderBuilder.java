package my.example.mongo.ecom.model.util;

import my.example.mongo.ecom.model.IOrderLineitem;
import my.example.mongo.ecom.model.impl.MongoOrder;
import my.example.mongo.ecom.model.impl.MongoUser;

public class OrderBuilder {
	private MongoOrder order;

	/**
	 * 
	 */
	private OrderBuilder(MongoUser user) {
		order = new MongoOrder(user);
	}

	public static OrderBuilder aNew(MongoUser user) {
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
