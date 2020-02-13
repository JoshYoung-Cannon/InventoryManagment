package com.qa.im.sqldatatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order order;
	
	@Before
	public void setUp() {
		order = new Order(1, 2, 3);
	}
	
	@Test
	public void gettersTest() {
		assertEquals(1, order.getId());
		assertEquals(2, order.getCustomer_id());
		assertEquals(3, order.getTotal(), 0);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(order.getId());
		assertNotNull(order.getCustomer_id());
		assertNotNull(order.getTotal());
		
		order.setId(0);
		assertEquals(0, order.getId());
		order.setCustomer_id(1);
		assertEquals(1, order.getCustomer_id());
		order.setTotal(2);
		assertEquals(2, order.getTotal(), 0);
	}
	
	@Test
	public void databaseOrderConstructor() {
		order = new Order(5, 6, 78);
	}
	
	@Test
	public void newOrderConstructorTest() {
		order = new Order(6);
	}
	
	@Test
	public void newEmptyOrderConstructor() {
		order = new Order();
	}
}
