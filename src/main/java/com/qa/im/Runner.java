package com.qa.im;

import org.apache.log4j.Logger;

import com.qa.im.controller.Controller;

public class Runner {
	
	public static final Logger LOGGER = Logger.getLogger(Runner.class);

	public static void main(String[] args) {
		Controller ims = new Controller();
		ims.run();
		LOGGER.info("GoodBye");
	}
}
