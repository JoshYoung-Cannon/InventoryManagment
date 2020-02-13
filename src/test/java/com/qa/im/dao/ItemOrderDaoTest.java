package com.qa.im.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.qa.im.enums.ItemOrderSearchTypes;

public class ItemOrderDaoTest {
private OrderDao dao;
	
	@Before
	public void setUp() {
		dao = new OrderDao();
		dao.setSearchID(ItemOrderSearchTypes.ITEM_ORDER.getSearchType());
	}
	
	@Test
	public void gettersTest() {
		assertEquals(ItemOrderSearchTypes.ITEM_ORDER.getSearchType(), dao.getSearchID());
	}
	
	@Test
	public void settersTest() {
		assertNotNull(dao.getSearchID());
		
		dao.setSearchID(null);
		assertNull(dao.getSearchID());
	}
}
