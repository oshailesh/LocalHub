package com.crud.customer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.crud.customer.model.Customer;

@RepositoryRestResource
public interface CustomerRepository extends MongoRepository<Customer, String>{

}
