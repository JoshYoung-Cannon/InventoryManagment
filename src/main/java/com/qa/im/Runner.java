package com.qa.im;

import org.apache.log4j.Logger;

import com.qa.im.services.CustomerServices;
import com.qa.im.utils.Config;
import com.qa.im.utils.Utils;

public class Runner {
	
	public static final Logger LOGGER = Logger.getLogger(Runner.class);

	public static void main(String[] args) {
//		LOGGER.info("Enter the inventory_db username:");
//		Config.username = Utils.strInput(30, "Username (hint root)");
//		LOGGER.info("Enter the inventory_db password:");
//		Config.password = Utils.strInput(30, "Password");
		Config.username = "root";
		Config.password = "1amGr00t";
		CustomerServices test = new CustomerServices();
		//test.add();
		test.viewAll();
		LOGGER.info("+-------------------------------------------------------+");
		test.findRecord(3);
	}
}
