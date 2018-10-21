package com.crud.customer.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.crud.customer.model.Customer;
import com.crud.customer.repo.CustomerRepository;

/**
 * A simple mock data loader to insert few customer records to Database 
 * at application startup. 
 *  
 * @author <a href="mailto:oshailesh@gmail.com">Me</a>
 * @version 1.0
 * */
@Component
public class MockDataLoader implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MockDataLoader.class);
	private final CustomerRepository repository;
 
    @Autowired
    public MockDataLoader(CustomerRepository repository) {
        this.repository = repository;
    }
 
    /**
     * Overriding run method in order to save few {@link Customer} object as {@link Document} in database.
     * 
     * @param strings Vararg of String
     * */
    @Override
    public void run(String... strings) throws Exception {
    	
    	save("test1@gmail.com","Jones","Tom","Victoria");
    	save("test2@gmail.com","Stiller","Ben","NSW");
    	save("test3@gmail.com","Hur","Ben","NZ");
    	save("test4@gmail.com","Maa","Jack","Victoria");
    }
    
    /**
     * This method will be performing the task of saving the {@link Customer} documents to database
     * @param emailAddress
     * @param lastName
     * @param firstName
     * @param address
     * */
    private void save(String emailAddress,String lastName, String firstName, String address) {
    	try {
    		this.repository.save(new Customer(emailAddress,lastName, firstName, address));
    	}catch(Exception e) {
    		logger.error("Failed to save mock data", e);
    	}
    }
    
}