package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.dao.OrderDao;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.sqldatatypes.Order;
import com.qa.im.utils.Utils;

public class OrderServices {
	/**
	 * Contains logic for: Calculate cost of Order Add Order View all Orders Delete
	 * Order
	 */
	OrderDao dao = new OrderDao();
	CustomerServices customerServices = new CustomerServices();

	public void add() {

//		 get customer id
		int customerID;
		Runner.LOGGER.info("Please enter the Customer id you want to make an order for:");
		customerID = Utils.idInput();
		while (customerServices.findRecord(customerID) == false) {
			Runner.LOGGER.info("Please enter the Customer id you want to make an order for:");
			customerID = Utils.idInput();
		}
//		 get at least 1 item id
//		 add customer id to order
		Order order = new Order(customerID);
//		 add order to database
		dao.create(order);
		Runner.LOGGER.info("Order added");
//		 add item to itemOrders and link to current order
	}

	public void viewAll() {
//		 create order listarray
		ArrayList<Order> orders = new ArrayList<Order>();
//		 populate listarray
		orders = dao.readAll();
//		 print result
		if (orders.size() > 0) {
			for (Order i : orders) {
				Runner.LOGGER.info("Order: " + i.getId() + " Customer: " + i.getCustomer_id() + " Total: £" + i.getTotal());
			}
		}
		else {
			Runner.LOGGER.info("There are no Orders");
		}

	}

	public ArrayList<Order> findRecord(int recordID) {
		/**
		 * Find all orders linked to a specified Customer
		 */
//		create order listarray
		ArrayList<Order> orders = new ArrayList<Order>();
//		get orders
		orders = dao.readRecords(recordID);
		if (orders.size() > 0) {
			for (Order i : orders) {
				Runner.LOGGER.info("Order: " + i.getId() + " Customer: " + i.getCustomer_id() + " Total: £" + i.getTotal());
			}
		}
		else {
			Runner.LOGGER.info("Could not find customer record with id: " + recordID);
		}
		return orders;
	}

	public void calculateCost() {
		/**
		 * Calculate the total cost of the Order and apply any appropriate discounts
		 */
//		 get order id
//		 look through linked item orders getting quantity costs (join item and itemOrders)
//		 sum the values of quantity cost
//		 apply discount
//		 save total cost
	}

	public void deleteOrder() {
		/**
		 * Delete a record from the Orders table
		 */
		// Look through itemOrders and delete any dependent records
	}
}
