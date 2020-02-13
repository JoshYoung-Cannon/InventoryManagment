package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.dao.OrderDao;
import com.qa.im.enums.ItemOrderSearchTypes;
import com.qa.im.enums.OrderSearchTypes;
import com.qa.im.sqldatatypes.Order;
import com.qa.im.utils.Utils;

/**
 * Contains logic for: 
 * Calculate cost of Order 
 * Add Order 
 * View all Orders 
 * Delete Order
 */
public class OrderServices {
	
	private OrderDao dao = new OrderDao(); 

	/**
	 * adds a new Order to the database with at least one item
	 * also ensures that the linked customer exists in the database before creating the order
	 */
	public void add() {
		ItemOrderServices itemOrderServices = new ItemOrderServices();
		CustomerServices customerServices = new CustomerServices();
		int customerID;
		Runner.LOGGER.info("Please enter the Customer id you want to make an Order for:");
		customerID = Utils.idInput();
		while (customerServices.findRecord(customerID) == false) {
			Runner.LOGGER.info("Please enter the Customer id you want to make an Order for:");
			customerID = Utils.idInput();
		}
		Order order = new Order(customerID);
		dao.create(order);
		dao.setSearchID(OrderSearchTypes.TOTAL.getSearchType());
		order = findRecord(0).get(0);
		itemOrderServices.addItemOrder(order.getId());
	}
	
	/**
	 * Finds and displays all Order records
	 */
	public void viewAll() {
		ArrayList<Order> orders = new ArrayList<>();
		orders = dao.readAll();
		if (orders.size() > 0) {
			for (Order i : orders) {
				Runner.LOGGER
						.info("Order: " + i.getId() + " Customer: " + i.getCustomer_id() + " Total: £" + i.getTotal());
			}
		} else {
			Runner.LOGGER.info("There are no Orders");
		}

	}

	/**
	 * Find all orders linked to a specified id search id and return an ArrayList of found records
	 * must be set with OrderDao.setSearchID() prior to calling this function to define which field to search by using the Orders Enum
	 * @param recordID
	 * @return
	 */
	public ArrayList<Order> findRecord(int recordID) {
		ArrayList<Order> orders = new ArrayList<>();
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

	/**
	 * Calculate the total cost of the Order and apply any appropriate discounts
	 * also remove any empty orders
	 */
	public void calculateCost() {
		ArrayList<Order> orders = new ArrayList<>();
		orders = dao.readAll();
		for (Order i : orders) {
			dao.update(i);
		}
		for (Order i : orders) {
			if (i.getTotal() == 0) {
				dao.delete(i.getId());
			}
		}
	}

	/**
	 * Delete a record from the Orders table
	 */
	public void deleteOrder() {
		ItemOrderServices itemOrderServices = new ItemOrderServices();
		Order order = new Order();
		dao.setSearchID(OrderSearchTypes.ORDER.getSearchType());
		Runner.LOGGER.info("Please enter the ID of the Order you want to delete:");
		order.setId(Utils.idInput());
		while (findRecord(order.getId()).size() <= 0) {
			Runner.LOGGER.info("Please enter the ID of the Order you want to delete:");
			order.setId(Utils.idInput());
		}
		itemOrderServices.deleteItemOrderByForiegnKey(order.getId(), ItemOrderSearchTypes.ORDER);
		dao.delete(order.getId());
	}

	/**
	 * Delete a record from the Orders table
	 */
	public void deleteOrder(int id) {
		ItemOrderServices itemOrderServices = new ItemOrderServices();
		ArrayList<Order> orders = new ArrayList<>();
		dao.setSearchID(OrderSearchTypes.CUSTOMER.getSearchType());
		orders = findRecord(id);
		for (Order i : orders) {
			itemOrderServices.deleteItemOrderByForiegnKey(i.getId(), ItemOrderSearchTypes.ORDER);
			dao.delete(i.getId());
		}
	}
}
