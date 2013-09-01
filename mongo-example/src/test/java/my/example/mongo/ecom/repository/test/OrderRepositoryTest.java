package my.example.mongo.ecom.repository.test;

import my.example.mongo.ecom.model.IUser;
import my.example.mongo.ecom.model.impl.MongoOrder;
import my.example.mongo.ecom.model.impl.MongoUser;
import my.example.mongo.ecom.model.util.OrderBuilder;
import my.example.mongo.ecom.model.util.OrderLineitemBuilder;
import my.example.mongo.ecom.model.util.UserBuilder;
import my.example.mongo.ecom.repository.IOrderRepository;
import my.example.mongo.ecom.repository.IUserRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-config.xml")
public class OrderRepositoryTest {
	@Autowired
	private IOrderRepository repository;

	@Autowired
	private IUserRepository userRepository;
	private  IUser user;

	@Before
	public  void setup() {
		user = userRepository.save(UserBuilder.aNew().build());
		Assert.assertNotNull(user);
	}

	@Test
	public void insertOrdersTest() {

		MongoOrder order = repository.save(OrderBuilder.aNew(user).build());

		Assert.assertNotNull(order);
		Assert.assertNotNull(order.getId());
	}

	@Test
	public void insertOrderSubtotalTest() {

		MongoOrder order = repository.save(OrderBuilder
				.aNew(user)
				.withLineitem(
						OrderLineitemBuilder.aNew().withPrice(100l).build(),
						OrderLineitemBuilder.aNew().withPrice(200l).build(),
						OrderLineitemBuilder.aNew().withPrice(300l).build())
				.build());

		Assert.assertNotNull(order);
		Assert.assertNotNull(order.getId());
		Assert.assertEquals((Long)600l, order.getSubtotal());
	}

	@Test
	public void readOrders() {

		Page<MongoOrder> orders = repository.findAll(new PageRequest(0, 10));
		Assert.assertTrue(orders.isFirstPage());
	}

}
