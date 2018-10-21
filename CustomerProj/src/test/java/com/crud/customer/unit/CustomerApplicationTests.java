package com.crud.customer.unit;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crud.customer.model.Customer;
import com.crud.customer.repo.CustomerRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerApplicationTests {
 
	@Autowired
	private CustomerRepository cusRepo;
 
	static final int QTY = 2;
 
	@Before
	public void init() {
		cusRepo.deleteAll();
	}
 
	@Test
	public void checkDuplicateSave() {
		for (int i = 0; i < QTY; i++) {
			Customer cust = new Customer("test2@gmail.com","Stiller","Ben","NSW");
			cusRepo.save(cust);
		}
		List<Customer> list = cusRepo.findAll();
		assertEquals(1, list.size());
	}
	
	@Test
	public void validateSave() {
		String emailId="test1@gmail.com";
		Customer cust = new Customer(emailId,"Jones","Tom","Victoria");
		cusRepo.save(cust);

		Customer retrieved = cusRepo.findById(emailId).get();
		assertEquals(cust.getEmailAddress(), retrieved.getEmailAddress());
	}
	
	@Test
	public void validateDelete() {
		String emailId="test3@gmail.com";
		Customer cust = new Customer(emailId,"Hur","Ben","NZ");
		cusRepo.save(cust);
		
		cusRepo.delete(cust);
		boolean isPresent = cusRepo.existsById(emailId);
		assertFalse(isPresent);
	}
}


