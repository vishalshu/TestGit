package my.example.mongo.ecom.repository.test;

import my.example.mongo.ecom.model.IUser;
import my.example.mongo.ecom.model.impl.MongoUser;
import my.example.mongo.ecom.model.util.UserBuilder;
import my.example.mongo.ecom.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class UserSearchTest extends AbstractMongoSpringRepoTest {

    @Autowired
    private IUserRepository repository;

    String[] usernames = new String[]{"vishal", "abc", "xyz", "klsf",
            "klowe", "akls", "pwpl", "laksd"};
    IUser savedUser;
    @BeforeClass
    public void setup() {

        for (int i = 0; i < 50; i++) {
            MongoUser user = UserBuilder
                    .aNew()
                    .withUsername(usernames[i % 8] + System.currentTimeMillis())
                    .withAge(i + 18).build();
            savedUser = repository.save(user);
        }
    }

    @Test
    public void testFindByAgeBetween() {
        List<MongoUser> users = repository.findByAgeBetween(20, 40,
                new PageRequest(0, 5, new Sort("username")));
        Assert.assertNotNull(users);
        Assert.assertTrue(!users.isEmpty());
    }

    @Test
    public void testFindUsersByUsernamePrefix() {
        List<MongoUser> users = repository.findByUsernameRegex("(?i)iSH(?-i)");
        Assert.assertNotNull(users);
        Assert.assertTrue(!users.isEmpty());
    }

    @Test
    public void testFindByUsername() {
        MongoUser user = repository.findByUsername(savedUser.getUsername());
        Assert.assertNotNull(user);
        Assert.assertEquals(user, savedUser);
    }

}
