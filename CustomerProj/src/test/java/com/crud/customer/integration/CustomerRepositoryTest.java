package com.crud.customer.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.crud.customer.model.Customer;
import com.crud.customer.repo.CustomerRepository;
import com.mongodb.Mongo;


@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

	@MockBean
	private CustomerRepository custRepo;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private static final String LOCALHOST = "127.0.0.1";
    private static final int MONGO_TEST_PORT = 27017;

	@Before
	public void setup() throws Exception {
		mongoTemplate.dropCollection(Customer.class);
	}

	@After
	public void tearDown() throws Exception {
		mongoTemplate.dropCollection(Customer.class);
	}

    @Test
    public void testSaveAndFindCustomer() throws Exception {
        Customer cust = new Customer("test1@gmail.com","Jones","Tom","Victoria");
        custRepo.save(cust);

        assertEquals(cust, custRepo.findById("test1@gmail.com").get());
        assertNull(custRepo.findById("test1@gmail.com").get());
    }
    
    @Test
    public void testSaveDuplicateAndFindCustomer() throws Exception {
        Customer cust1 = new Customer("test1@gmail.com","Jones","Tom","Victoria");
        custRepo.save(cust1);
        Customer cust2 = new Customer("test1@gmail.com","Jones","Tom","Victoria");
        custRepo.save(cust2);
        assertEquals(cust1, custRepo.findById("test1@gmail.com").get());
        assertEquals(cust2, custRepo.findById("test1@gmail.com").get());
        assertNull(custRepo.findById("test1@gmail.com").get());
    }





    @Test
    public void testSave() {
        //fail("Not yet implemented");
    }

    @Test
    public void testFindByKey() {
        //fail("Not yet implemented");
    }
	
}
