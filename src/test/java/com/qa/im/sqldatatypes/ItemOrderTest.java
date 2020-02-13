package com.qa.im.sqldatatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class ItemOrderTest {

private ItemOrder itemOrder;
	
	@Before
	public void setUp() {
		itemOrder = new ItemOrder(1, 2, 3, 4);
	}
	
	@Test
	public void gettersTest() {
		assertEquals(1, itemOrder.getId());
		assertEquals(2, itemOrder.getItemID());
		assertEquals(3, itemOrder.getOrderID());
		assertEquals(4, itemOrder.getQuantity());
	}
	
	@Test
	public void settersTest() {
		assertNotNull(itemOrder.getId());
		assertNotNull(itemOrder.getItemID());
		assertNotNull(itemOrder.getOrderID());
		assertNotNull(itemOrder.getQuantity());
		
		itemOrder.setId(0);
		assertEquals(0, itemOrder.getId());
		itemOrder.setItemID(0);
		assertEquals(0, itemOrder.getItemID());
		itemOrder.setOrderID(0);
		assertEquals(0, itemOrder.getOrderID());
		itemOrder.setQuantity(0);
		assertEquals(0, itemOrder.getQuantity());
	}
	
	@Test
	public void databaseItemOrderConstructor() {
		itemOrder = new ItemOrder(5, 6, 7, 8);
	}
	
	@Test
	public void newItemOrderWithQuantityConstructorTest() {
		itemOrder = new ItemOrder(6, 7, 8);
	}
	
	@Test
	public void newItemOrderConstructorTest() {
		itemOrder = new ItemOrder(6, 7);
	}
	
	@Test
	public void newEmptyItemOrderConstructor() {
		itemOrder = new ItemOrder();
	}
}
