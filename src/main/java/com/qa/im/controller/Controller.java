package com.qa.im.controller;

import com.qa.im.Runner;
import com.qa.im.enums.CrudOptions;
import com.qa.im.enums.TableOptions;
import com.qa.im.services.CustomerServices;
import com.qa.im.services.ItemOrderServices;
import com.qa.im.services.ItemServices;
import com.qa.im.services.OrderServices;
import com.qa.im.utils.Config;
import com.qa.im.utils.Utils;


/**
 * This contains all the logic for menu navigation, and completing functionality whilst avoiding infinite loops.
 * @author Admin
 *
 */
public class Controller {

	/**
	 * run() is the system startup
	 */
	public void run() {
		boolean running = true;
		CustomerServices customer = new CustomerServices();
		ItemServices item = new ItemServices();
		OrderServices order = new OrderServices();
		ItemOrderServices itemOrder = new ItemOrderServices();
		Runner.LOGGER.info("Enter the inventory_db username:");
		Config.setUsername(Utils.logInput("Username (hint root)"));
		Runner.LOGGER.info("Enter the inventory_db password:");
		Config.setPassword(Utils.logInput("Password"));
		while (running) {
			String tableChoice = null;
			while (tableChoice == null) {
				tableChoice = getTableChoice();
				if (tableChoice.equals("QUIT")) {
					running = false;
					continue;
				}
				String crudChoice = null;
				while (crudChoice == null) {
					crudChoice = getCRUDChoice();
				}
				switch (crudChoice) {
				case ("CREATE"):
					switch (tableChoice) {
					case ("CUSTOMERS"):
						customer.add();
						break;
					case ("ITEMS"):
						item.add();
						break;
					case ("ORDERS"):
						order.add();
						break;
					case ("ITEMS IN ORDERS"):
						itemOrder.addItemOrder();
						break;
					default:
						break;
					}
					break;
				case ("READ ALL"):
					switch (tableChoice) {
					case ("CUSTOMERS"):
						customer.viewAll();
						break;
					case ("ITEMS"):
						item.viewAll();
						break;
					case ("ORDERS"):
						order.viewAll();
						break;
					case ("ITEMS IN ORDERS"):
						itemOrder.viewAll();
						break;
					default:
						break;
					}
					break;
				case ("UPDATE"):
					switch (tableChoice) {
					case ("CUSTOMERS"):
						customer.update();
						break;
					case ("ITEMS"):
						item.updateItem();
						break;
					case ("ORDERS"):
						order.calculateCost();
						break;
					case ("ITEMS IN ORDERS"):
						itemOrder.updateQuantity();
						break;
					default:
						break;
					}
					break;
				case ("DELETE"):
					switch (tableChoice) {
					case ("CUSTOMERS"):
						customer.delete();
						break;
					case ("ITEMS"):
						item.deleteItem();
						order.calculateCost();
						break;
					case ("ORDERS"):
						order.deleteOrder();
						break;
					case ("ITEMS IN ORDERS"):
						itemOrder.deleteItemOrder();
						order.calculateCost();
						break;
					default:
						break;
					}
					break;
				case ("BACK"):
					tableChoice = null;
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * gets the users menu choice and ensures that it is valid CRUD option
	 * @return
	 */
	public String getCRUDChoice() {
		String crudChoice = null;
		while (crudChoice == null) {
			displayCRUD();
			crudChoice = Utils.strInput(10, "Command");
			switch (crudChoice) {
			case ("CREATE"):
				break;
			case ("READ ALL"):
				break;
			case ("UPDATE"):
				break;
			case ("DELETE"):
				break;
			case ("BACK"):
				break;
			default:
				Runner.LOGGER.info("Please enter a valid Command");
				crudChoice = null;
			}
		}
		return crudChoice;
	}

	/**
	 * gets the users menu choice and ensures that it is valid table in the database
	 * @return
	 */
	public String getTableChoice() {
		String tableChoice = null;
		while (tableChoice == null) {
			displayTables();
			tableChoice = Utils.strInput(15, "Command");
			switch (tableChoice) {
			case ("CUSTOMERS"):
				break;
			case ("ITEMS"):
				break;
			case ("ORDERS"):
				break;
			case ("ITEMS IN ORDERS"):
				break;
			case ("QUIT"):
				break;
			default:
				Runner.LOGGER.info("Please enter a valid Command");
				tableChoice = null;
			}
		}
		return tableChoice;
	}

	/**
	 * outputs the CRUD menu
	 */
	public void displayCRUD() {
		Runner.LOGGER.info("What would you like to do?");
		Runner.LOGGER.info(CrudOptions.CREATE.getCrudOption());
		Runner.LOGGER.info(CrudOptions.READALL.getCrudOption());
		Runner.LOGGER.info(CrudOptions.UPDATE.getCrudOption());
		Runner.LOGGER.info(CrudOptions.DELETE.getCrudOption());
		Runner.LOGGER.info(CrudOptions.BACK.getCrudOption());
	}

	/**
	 * outputs the table menu
	 */
	public void displayTables() {
		Runner.LOGGER.info("Which table would you like to use?");
		Runner.LOGGER.info(TableOptions.CUSTOMERS.getTableOption());
		Runner.LOGGER.info(TableOptions.ITEMS.getTableOption());
		Runner.LOGGER.info(TableOptions.ORDERS.getTableOption());
		Runner.LOGGER.info(TableOptions.ORDERITEMS.getTableOption());
		Runner.LOGGER.info(TableOptions.QUIT.getTableOption());

	}
}
