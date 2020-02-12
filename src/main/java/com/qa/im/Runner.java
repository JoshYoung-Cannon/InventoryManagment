package com.qa.im;

import org.apache.log4j.Logger;

import com.qa.im.controller.Controller;
import com.qa.im.services.CustomerServices;
import com.qa.im.services.ItemOrderServices;
import com.qa.im.services.ItemServices;
import com.qa.im.services.OrderServices;
import com.qa.im.utils.Config;

public class Runner {

	public static final Logger LOGGER = Logger.getLogger(Runner.class);

	public static void main(String[] args) {
		Controller ims = new Controller();
		ims.run();
		LOGGER.info("GoodBye");
	}
}
