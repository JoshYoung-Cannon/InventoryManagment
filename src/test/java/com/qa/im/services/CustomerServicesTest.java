package com.qa.im.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class CustomerServicesTest {
	
private CustomerServices customer;
	
	@Before
	public void setUp() {
		customer = new CustomerServices();
	}
	
	@Test
	public void gettersTest() {
		assertEquals(20, customer.getFirstNameMaxLength());
		assertEquals(30, customer.getLastNameMaxLength());
	}
	
	@Test
	public void settersTest() {
		assertNotNull(customer.getFirstNameMaxLength());
		assertNotNull(customer.getLastNameMaxLength());
		
		customer.setFirstNameMaxLength(0);
		assertEquals(0, customer.getFirstNameMaxLength());
		customer.setLastNameMaxLength(0);
		assertEquals(0, customer.getLastNameMaxLength());
	}
}
