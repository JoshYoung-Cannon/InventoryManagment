package com.qa.im;

import org.apache.log4j.Logger;

import com.qa.im.services.CustomerServices;
import com.qa.im.services.ItemServices;
import com.qa.im.services.OrderServices;
import com.qa.im.utils.Config;

public class Runner {
	
	public static final Logger LOGGER = Logger.getLogger(Runner.class);

	public static void main(String[] args) {
//		LOGGER.info("Enter the inventory_db username:");
//		Config.username = Utils.strInput(30, "Username (hint root)");
//		LOGGER.info("Enter the inventory_db password:");
//		Config.password = Utils.strInput(30, "Password");

		CustomerServices cus = new CustomerServices();
		OrderServices test = new OrderServices();
		test.viewAll();
		LOGGER.info("");
		test.findRecord(3);
	}
}
