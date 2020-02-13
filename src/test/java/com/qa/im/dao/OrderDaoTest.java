package com.qa.im.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.qa.im.enums.OrderSearchTypes;

public class OrderDaoTest {

	private OrderDao dao;
	
	@Before
	public void setUp() {
		dao = new OrderDao();
		dao.setSearchID(OrderSearchTypes.ORDER.getSearchType());
	}
	
	@Test
	public void gettersTest() {
		assertEquals(OrderSearchTypes.ORDER.getSearchType(), dao.getSearchID());
		assertEquals(10000, dao.getDiscountThreshold(), 0);
		assertEquals(0.1, dao.getDiscountRate(), 0);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(dao.getSearchID());
		assertNotNull(dao.getDiscountRate());
		assertNotNull(dao.getDiscountThreshold());
		
		dao.setSearchID(null);
		assertNull(dao.getSearchID());
		dao.setDiscountRate(10);
		assertEquals(10, dao.getDiscountRate(), 0);
		dao.setDiscountThreshold(5);
		assertEquals(5, dao.getDiscountThreshold(), 0);
	}
}
