package com.crud.customer.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.crud.customer.model.Customer;
import com.crud.customer.repo.CustomerRepository;


@RestController
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerRepository custRepo;
	
	@RequestMapping(method =RequestMethod.GET ,value="/customers")
	public Iterable<Customer> customer(){
		logger.info("Got get list request");
		return custRepo.findAll();
	}

	@RequestMapping(method =RequestMethod.POST ,value="/customers")
	public Customer save(@RequestBody @Valid Customer cust){
		logger.info("Got save request");
		custRepo.save(cust);
		logger.info("Saved Customer {}",cust);
		return cust;
	}
	
	@RequestMapping(method =RequestMethod.GET ,value="/customers/{id}")
	public Customer show(@PathVariable @Email String id){
		logger.info("Got find request for {}",id);
		Optional<Customer> cust = custRepo.findById(id);
		return cust.get();		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/customers/{id}")
	public String delete(@PathVariable @Email String id) {
		logger.info("Got delete request for {}",id);
		Optional<Customer> optCust = custRepo.findById(id);

		Customer cust = optCust.get();
		if(cust!=null)
		custRepo.delete(cust);
		
		logger.info("Deleted Customer by email ID{}",id);
//TODO: Need to change this logic
		return "";
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    return ex.getBindingResult()
	        .getAllErrors().stream()
	        .map(ObjectError::getDefaultMessage)
	        .collect(Collectors.toList());
	}
}
