package com.qa.im.sqldatatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private Customer customer;
	
	@Before
	public void setUp() {
		customer = new Customer(0, "John", "Smith");
	}
	
	@Test
	public void gettersTest() {
		assertEquals(0, customer.getId());
		assertEquals("John", customer.getForname());
		assertEquals("Smith", customer.getSurname());
	}
	
	@Test
	public void settersTest() {
		assertNotNull(customer.getId());
		assertNotNull(customer.getForname());
		assertNotNull(customer.getSurname());
		
		customer.setId(0);
		assertEquals(0, customer.getId());
		customer.setForname(null);
		assertNull(customer.getForname());
		customer.setSurname(null);
		assertNull(customer.getSurname());
	}
	
	@Test
	public void databaseCustomerConstructorTest() {
		customer = new Customer(0, "Jane", "Doe");
	}
	
	@Test
	public void newCustomerConstructorTest() {
		customer = new Customer("Jane", "Doe");
	}
	
	@Test
	public void emptyCustomerConstructor() {
		customer = new Customer();
	}
}
