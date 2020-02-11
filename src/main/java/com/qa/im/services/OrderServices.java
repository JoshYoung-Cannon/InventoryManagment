package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.dao.OrderDao;
import com.qa.im.dao.OrderSearchTypes;
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
	private CustomerServices customerServices = new CustomerServices();
	private ItemOrderServices itemOrderServices = new ItemOrderServices();

	public void add() {

//		 get customer id
		int customerID;
		Runner.LOGGER.info("Please enter the Customer id you want to make an order for:");
		customerID = Utils.idInput();
		while (customerServices.findRecord(customerID) == false) {
			Runner.LOGGER.info("Please enter the Customer id you want to make an order for:");
			customerID = Utils.idInput();
		}
//		 add customer id to order
		Order order = new Order(customerID);
//		 add order to database
		dao.create(order);
		Runner.LOGGER.info("Order added");
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
				Runner.LOGGER.info("Order: " + i.getId() + " Customer: " + i.getCustomer_id() + " Total: �" + i.getTotal());
			}
		}
		else {
			Runner.LOGGER.info("There are no Orders");
		}

	}

	public ArrayList<Order> findRecord(int recordID) {
		/**
		 * Find all orders linked to a specified id
		 * search id must be set with OrderDao.setSearchID() prior to calling this function
		 */
//		create order listarray
		ArrayList<Order> orders = new ArrayList<Order>();
//		get orders
		orders = dao.readRecords(recordID);
		if (orders.size() > 0) {
			for (Order i : orders) {
				Runner.LOGGER.info("Order: " + i.getId() + " Customer: " + i.getCustomer_id() + " Total: �" + i.getTotal());
			}
		}
		else {
			Runner.LOGGER.info("Could not find " + dao.getSearchID() + ": " + recordID);
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
		// Get Order id
		Order order = new Order();
		dao.setSearchID(OrderSearchTypes.ORDER.getSearchType());
		Runner.LOGGER.info("Please enter the ID of the order you want to delete:");
		order.setId(Utils.idInput());
		while (findRecord(order.getId()).size() <= 0) {
			Runner.LOGGER.info("Please enter the ID of the order you want to delete:");
			order.setId(Utils.idInput());
		}
		// Look through itemOrders and delete any dependent records
		// Delete Order
		dao.delete(order.getId());
		Runner.LOGGER.info("Item deleted");
	}
}
