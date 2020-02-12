package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.ItemOrderSearchTypes;
import com.qa.im.OrderSearchTypes;
import com.qa.im.Runner;
import com.qa.im.dao.ItemOrderDao;
import com.qa.im.dao.OrderDao;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.sqldatatypes.ItemOrder;
import com.qa.im.sqldatatypes.Order;
import com.qa.im.utils.Utils;

public class ItemOrderServices {
	/**
	 * Contains logic for:
	 * Add Item to an Order
	 * Update Item quantity in Order
	 * Delete an Item from Order
	 */
	private ItemOrderDao dao = new ItemOrderDao();
	private OrderDao orderDao = new OrderDao();
	
	public void addItemOrder() {
		/**
		 * Link an item to an order and save the quantity desired
		 */
		// get order id and item id
		ItemOrder itemOrder = new ItemOrder();
		OrderServices order = new OrderServices();
		ItemServices item = new ItemServices();
		orderDao.setSearchID(OrderSearchTypes.ORDER.getSearchType());
		Runner.LOGGER.info("Please enter the Order id you want to add to:");
		itemOrder.setOrderID(Utils.idInput()); 
		while (order.findRecord(itemOrder.getOrderID()).isEmpty() == true) {
			Runner.LOGGER.info("Please enter the Order id you want to make an order for:");
			itemOrder.setOrderID(Utils.idInput());
		}
		Runner.LOGGER.info("Please enter the Item id you want to add to Order:");
		itemOrder.setItemID(Utils.idInput());
		while (item.findRecord(itemOrder.getItemID()) == false) {
			Runner.LOGGER.info("Please enter the Item id you want to add to Order:");
			itemOrder.setItemID(Utils.idInput());
		}
		// get quantity
		Runner.LOGGER.info("Please enter the quantity of the Item you want:");
		itemOrder.setQuantity(Utils.quantityInput());
		// add to itemOrders
		dao.create(itemOrder);
		// update order cost
	}
	
	public void addItemOrder(int orderID) {
		/**
		 * Link an item to an order and save the quantity desired
		 */
		// create itemOrder instance
		ItemOrder itemOrder = new ItemOrder();
		itemOrder.setOrderID(orderID);
		ItemServices item = new ItemServices();
		Runner.LOGGER.info("Please enter the Item id you want to add to:");
		itemOrder.setItemID(Utils.idInput());
		while (item.findRecord(itemOrder.getItemID()) == false) {
			Runner.LOGGER.info("Please enter the Order id you want to make an Order for:");
			itemOrder.setItemID(Utils.idInput());
		}
		// get quantity
		Runner.LOGGER.info("Please enter the quantity of the Item you want:");
		itemOrder.setQuantity(Utils.quantityInput());
		// add to itemOrders
		dao.create(itemOrder);
		// update order cost
	}
	
	public void viewAll() {
		/**
		 * Displays the itemOrder table
		 */
		// create customer arraylist
		ArrayList<ItemOrder> itemOrders = new ArrayList<ItemOrder>();
		// populate arraylist
		itemOrders = dao.readAll();
		// print result
		for (ItemOrder i : itemOrders) {
			Runner.LOGGER.info("id: " + i.getId() + " item_id: " + i.getItemID() + " order_id:  " + i.getOrderID() + " quantity: " + i.getQuantity());
		}
	}
	
	public ArrayList<ItemOrder> findRecord(int recordID) {
		/**
		 * Find all itemOrders linked to a specified id
		 * search id must be set with ItemOrderDao.setSearchID() prior to calling this function
		 */
//		create order listarray
		ArrayList<ItemOrder> itemOrders = new ArrayList<ItemOrder>();
//		get itemOrders
		itemOrders = dao.readRecords(recordID);
		if (itemOrders.size() > 0) {
			for (ItemOrder i : itemOrders) {
				Runner.LOGGER.info("id: " + i.getId() + " item_id: " + i.getItemID() + " order_id:  " + i.getOrderID() + " quantity: " + i.getQuantity());
			}
		}
		else {
			Runner.LOGGER.info("Could not find " + dao.getSearchID() + ": " + recordID);
		}
		return itemOrders;
	}
	
	public void updateQuantity() {
		/**
		 * alter the quantity of an item in an order
		 */
		// get order id and item id
		ItemOrder itemOrder = new ItemOrder();
		dao.setSearchID(ItemOrderSearchTypes.ITEMORDER.getSearchType());
		Runner.LOGGER.info("Please enter the Item Order id you want to update:");
		itemOrder.setId(Utils.idInput()); 
		while (findRecord(itemOrder.getId()).isEmpty() == true) {
			Runner.LOGGER.info("Please enter the Item Order id you want to update:");
			itemOrder.setId(Utils.idInput());
		}
		// get quantity
		Runner.LOGGER.info("Please enter the quantity of the Item you want:");
		itemOrder.setQuantity(Utils.quantityInput());
		// update quantity
		dao.update(itemOrder);
		// update order cost
	}
	public void deleteItemOrder() {
		/**
		 * delete any record with a given id
		 */
		// get id
		ItemOrder itemOrder = new ItemOrder();
		Runner.LOGGER.info("Please enter the ID of the Item Order you want to delete:");
		itemOrder.setId(Utils.idInput());
		dao.setSearchID(ItemOrderSearchTypes.ITEMORDER.getSearchType());
		while (findRecord(itemOrder.getId()).isEmpty() == true) {
			Runner.LOGGER.info("Please enter the ID of the Item Order you want to delete:");
			itemOrder.setId(Utils.idInput());
		}
		dao.delete(itemOrder.getId());
	}
	
	public void deleteItemOrderByForiegnKey(int foriegnID, ItemOrderSearchTypes fieldName) {
		/**
		 * delete any record with a given item/order id
		 */
		// store array of appropriate itemOrder ids
		ArrayList<ItemOrder> itemOrders = new ArrayList<ItemOrder>();
		dao.setSearchID(fieldName.getSearchType());
		itemOrders = findRecord(foriegnID);
		// go through array removing records
		for (ItemOrder i : itemOrders) {
			dao.delete(i.getId());
		}
	}
}
