package com.qa.im.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ItemServicesTest {

	private ItemServices item;
	
	@Before
	public void setUp() {
		item = new ItemServices();
	}
	
	@Test
	public void gettersTest() {
		assertEquals(100, item.getItemNameMaxLength());
		assertEquals(99999.99, item.getMaxValue(), 0);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(item.getItemNameMaxLength());
		assertNotNull(item.getMaxValue());
		
		item.setItemNameMaxLength(0);
		assertEquals(0, item.getItemNameMaxLength());
		item.setMaxValue(0);
		assertEquals(0, item.getMaxValue(), 0);
	}
}
