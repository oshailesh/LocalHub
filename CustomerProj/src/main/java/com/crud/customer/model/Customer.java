package com.crud.customer.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {
	
	@Id
	@Email(message = "Email address should be valid")
	private String emailAddress;
	
	@NotBlank(message = "First name should not be blank")
	private String firstName;
	
	@NotBlank(message = "Last name should not be blank")
	private String lastName;
	
	private String address;
	
	public Customer() {
		super();
	}

	public Customer(String emailAddress,String firstName, String lastName, String address) {
		super();
		this.emailAddress = emailAddress;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [emailAddress=" + emailAddress + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + "]";
	}
	
}
