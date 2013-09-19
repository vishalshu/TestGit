package my.example.mongo.ecom.repository.test;

import my.example.mongo.ecom.model.impl.MongoOrder;
import my.example.mongo.ecom.model.impl.MongoUser;
import my.example.mongo.ecom.model.util.AddressBuilder;
import my.example.mongo.ecom.model.util.OrderBuilder;
import my.example.mongo.ecom.model.util.UserBuilder;
import my.example.mongo.ecom.repository.IOrderRepository;
import my.example.mongo.ecom.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class OrderSearchTest extends AbstractMongoSpringRepoTest {
    @Autowired
    private IOrderRepository repository;

    @Autowired
    private IUserRepository userRepository;

    private MongoOrder order;
    private static final String USERNAME = "vishalshu";
    private static final String CITY = "Ahmedabad";

    @BeforeClass
    public void setup() {
        MongoUser user = userRepository.save(
                UserBuilder.aNew()
                        .withUsername(USERNAME)
                        .withAddress(AddressBuilder.aNew()
                                             .withCity(CITY)
                                             .build())
                        .build());
        Assert.assertNotNull(user);
        order = repository.save(OrderBuilder.aNew(user).build());
    }

    @Test
    public void testFindOrdersByUsername() {
        List<MongoOrder> orders = repository.findByUser(userRepository
                                                                .findByUsername(USERNAME));

        Assert.assertNotNull(orders);
        Assert.assertTrue(orders.size() > 0);
    }

    @Test
    public void testFindByShippingAddressCity() {

        List<MongoOrder> orders = repository
                .findByShippingAddressCity(CITY);

        Assert.assertNotNull(orders);
        Assert.assertTrue(orders.size() > 0);
    }

}
