package com.qa.im.sqldatatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	private Item item;
	
	@Before
	public void setUp() {
		item = new Item(1, "DooDad", 10);
	}
	
	@Test
	public void gettersTest() {
		assertEquals(1, item.getId());
		assertEquals("DooDad", item.getName());
		assertEquals(10, item.getValue(), 0);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(item.getId());
		assertNotNull(item.getName());
		assertNotNull(item.getValue());
		
		item.setId(0);
		assertEquals(0, item.getId());
		item.setName(null);
		assertNull(item.getName());
		item.setValue(0);
		assertEquals(0, item.getValue(), 0);
	}
	
	@Test
	public void databaseItemConstructor() {
		item = new Item(0, "Gizmo", 42);
	}
	
	@Test
	public void newItemConstructorTest() {
		item = new Item("Gizmo", 42);
	}
	
	@Test
	public void newEmptyItemConstructor() {
		item = new Item();
	}
}
