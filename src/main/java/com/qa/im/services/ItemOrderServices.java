package com.qa.im.services;

import com.qa.im.Runner;
import com.qa.im.dao.ItemOrderDao;
import com.qa.im.dao.OrderDao;
import com.qa.im.dao.OrderSearchTypes;
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
	ItemOrderDao dao = new ItemOrderDao();
	OrderDao orderDao = new OrderDao();
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
		Runner.LOGGER.info("Please enter the Item id you want to add to:");
		itemOrder.setItemID(Utils.idInput());
		while (item.findRecord(itemOrder.getItemID()) == false) {
			Runner.LOGGER.info("Please enter the Order id you want to make an order for:");
			itemOrder.setItemID(Utils.idInput());
		}
		// get quantity
		Runner.LOGGER.info("Please enter the quantity of the item you want:");
		itemOrder.setQuantity(Utils.quantityInput());
		// add to itemOrders
		dao.create(itemOrder);
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
			Runner.LOGGER.info("Please enter the Order id you want to make an order for:");
			itemOrder.setItemID(Utils.idInput());
		}
		// get quantity
		Runner.LOGGER.info("Please enter the quantity of the item you want:");
		itemOrder.setQuantity(Utils.quantityInput());
		// add to itemOrders
		dao.create(itemOrder);
	}
	
	public void updateQuantity() {
		/**
		 * alter the quantity of an item in an order
		 */
		// get order id and item id
		// get new quantity
		// update quantity
		// update order cost
	}
	public void deleteItemOrder() {
		/**
		 * delete any record with a given id
		 */
		// get id
		// store an array of appropriate itemOrder ids
		// go through array removing records
	}
	public void deleteItemOrderByForiegnKey() {
		/**
		 * delete any record with a given item/order id
		 */
		// get item/order id
		// store array of appropriate itemOrder ids
		// go through array removing records
		// check if any orders have no items remove those that dont
		// update all order costs
	}
}
