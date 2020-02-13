package com.qa.im.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ConfigTest {
	
	@Before
	public void setUp() {
		Config.setUsername("admin");
		Config.setPassword("root");
	}
	
	@Test
	public void gettersTest() {
		assertEquals("admin", Config.getUsername());
		assertEquals("root", Config.getPassword());
	}
	
	@Test
	public void settersTest() {
		assertNotNull(Config.getUsername());
		assertNotNull(Config.getPassword());
		
		Config.setUsername("root");
		assertEquals("root", Config.getUsername());
		Config.setPassword("admin");
		assertEquals("admin", Config.getPassword());
	}
}
