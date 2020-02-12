package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.ItemOrderSearchTypes;
import com.qa.im.OrderSearchTypes;
import com.qa.im.Runner;
import com.qa.im.dao.OrderDao;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.sqldatatypes.Item;
import com.qa.im.sqldatatypes.Order;
import com.qa.im.utils.Utils;

public class OrderServices {
	/**
	 * Contains logic for: Calculate cost of Order Add Order View all Orders Delete
	 * Order
	 */
	private OrderDao dao = new OrderDao(); 

	public void add() {
		ItemOrderServices itemOrderServices = new ItemOrderServices();
		CustomerServices customerServices = new CustomerServices();
//		 get customer id
		int customerID;
		Runner.LOGGER.info("Please enter the Customer id you want to make an Order for:");
		customerID = Utils.idInput();
		while (customerServices.findRecord(customerID) == false) {
			Runner.LOGGER.info("Please enter the Customer id you want to make an Order for:");
			customerID = Utils.idInput();
		}
//		 add customer id to order
		Order order = new Order(customerID);
//		 add order to database
		dao.create(order);
//		 get orders id
		dao.setSearchID(OrderSearchTypes.TOTAL.getSearchType());
		order = findRecord(0).get(0);
//		 add a selected item to itemOrders and link to current order
		itemOrderServices.addItemOrder(order.getId());
	}

	public void viewAll() {
//		 create order listarray
		ArrayList<Order> orders = new ArrayList<Order>();
//		 populate listarray
		orders = dao.readAll();
//		 print result
		if (orders.size() > 0) {
			for (Order i : orders) {
				Runner.LOGGER
						.info("Order: " + i.getId() + " Customer: " + i.getCustomer_id() + " Total: £" + i.getTotal());
			}
		} else {
			Runner.LOGGER.info("There are no Orders");
		}

	}

	public ArrayList<Order> findRecord(int recordID) {
		/**
		 * Find all orders linked to a specified id search id must be set with
		 * OrderDao.setSearchID() prior to calling this function
		 */
//		create order listarray
		ArrayList<Order> orders = new ArrayList<Order>();
//		get orders
		orders = dao.readRecords(recordID);
		if (orders.size() > 0) {
			for (Order i : orders) {
				Runner.LOGGER
						.info("Order: " + i.getId() + " Customer: " + i.getCustomer_id() + " Total: £" + i.getTotal());
			}
		} else {
			Runner.LOGGER.info("Could not find " + dao.getSearchID() + ": " + recordID);
		}
		return orders;
	}

	public void calculateCost() {
		/**
		 * Calculate the total cost of the Order and apply any appropriate discounts
		 */
//		 loop through all orders
		ArrayList<Order> orders = new ArrayList<Order>();
		orders = dao.readAll();
//		 look through linked item orders getting quantity costs (join item and itemOrders)
		for (Order i : orders) {
//			 sum the values of quantity cost and apply discounts
			dao.update(i);
		}
		// delete empty orders
		for (Order i : orders) {
			if (i.getTotal() == 0) {
				dao.delete(i.getId());
			}
		}
	}

	public void deleteOrder() {
		/**
		 * Delete a record from the Orders table
		 */
		ItemOrderServices itemOrderServices = new ItemOrderServices();
		// Get Order id
		Order order = new Order();
		dao.setSearchID(OrderSearchTypes.ORDER.getSearchType());
		Runner.LOGGER.info("Please enter the ID of the Order you want to delete:");
		order.setId(Utils.idInput());
		while (findRecord(order.getId()).size() <= 0) {
			Runner.LOGGER.info("Please enter the ID of the Order you want to delete:");
			order.setId(Utils.idInput());
		}
		// Look through itemOrders and delete any dependent records
		itemOrderServices.deleteItemOrderByForiegnKey(order.getId(), ItemOrderSearchTypes.ORDER);
		// Delete Order
		dao.delete(order.getId());
	}

	public void deleteOrder(int id) {
		/**
		 * Delete a record from the Orders table
		 */
		ItemOrderServices itemOrderServices = new ItemOrderServices();
		// Get Order id
		ArrayList<Order> orders = new ArrayList<Order>();
		dao.setSearchID(OrderSearchTypes.CUSTOMER.getSearchType());
		orders = findRecord(id);
		for (Order i : orders) {
			// Look through itemOrders and delete any dependent records
			itemOrderServices.deleteItemOrderByForiegnKey(i.getId(), ItemOrderSearchTypes.ORDER);
			// Delete Order
			dao.delete(i.getId());
		}
	}
}
