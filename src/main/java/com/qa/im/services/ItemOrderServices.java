package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.dao.ItemOrderDao;
import com.qa.im.dao.OrderDao;
import com.qa.im.enums.ItemOrderSearchTypes;
import com.qa.im.enums.OrderSearchTypes;
import com.qa.im.sqldatatypes.ItemOrder;
import com.qa.im.utils.Utils;

/**
 *
 * Contains logic for:
 * Add Item to an Order
 * Update Item quantity in Order
 * Delete an Item from Order
 * @author Admin
 *
 */
public class ItemOrderServices {
	private ItemOrderDao dao = new ItemOrderDao();
	private OrderDao orderDao = new OrderDao();
	
	/**
	 * Link an item to an order and save the quantity desired
	 */
	public void addItemOrder() {
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
		Runner.LOGGER.info("Please enter the quantity of the Item you want:");
		itemOrder.setQuantity(Utils.quantityInput());
		dao.create(itemOrder);
	}
	
	/**
	 * Link an item to an order and save the quantity desired
	 */
	public void addItemOrder(int orderID) {
		ItemOrder itemOrder = new ItemOrder();
		itemOrder.setOrderID(orderID);
		ItemServices item = new ItemServices();
		Runner.LOGGER.info("Please enter the Item id you want to add to:");
		itemOrder.setItemID(Utils.idInput());
		while (item.findRecord(itemOrder.getItemID()) == false) {
			Runner.LOGGER.info("Please enter the Order id you want to make an Order for:");
			itemOrder.setItemID(Utils.idInput());
		}
		Runner.LOGGER.info("Please enter the quantity of the Item you want:");
		itemOrder.setQuantity(Utils.quantityInput());
		dao.create(itemOrder);
	}
	
	/**
	 * Displays the itemOrder table
	 */
	public void viewAll() {
		ArrayList<ItemOrder> itemOrders = new ArrayList<ItemOrder>();
		itemOrders = dao.readAll();
		for (ItemOrder i : itemOrders) {
			Runner.LOGGER.info("id: " + i.getId() + " item_id: " + i.getItemID() + " order_id:  " + i.getOrderID() + " quantity: " + i.getQuantity());
		}
	}
	
	/**
	 * Find all Item Orders linked to a specified id search id and return an ArrayList of found records
	 * must be set with ItemOrderDao.setSearchID() prior to calling this function to define which field to search by using the ItemOrders Enum
	 * @param recordID
	 * @return
	 */
	public ArrayList<ItemOrder> findRecord(int recordID) {
		ArrayList<ItemOrder> itemOrders = new ArrayList<ItemOrder>();
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
	
	/**
	 * alter the quantity of an item in an order
	 */
	public void updateQuantity() {
		ItemOrder itemOrder = new ItemOrder();
		dao.setSearchID(ItemOrderSearchTypes.ITEM_ORDER.getSearchType());
		Runner.LOGGER.info("Please enter the Item Order id you want to update:");
		itemOrder.setId(Utils.idInput()); 
		while (findRecord(itemOrder.getId()).isEmpty() == true) {
			Runner.LOGGER.info("Please enter the Item Order id you want to update:");
			itemOrder.setId(Utils.idInput());
		}
		Runner.LOGGER.info("Please enter the quantity of the Item you want:");
		itemOrder.setQuantity(Utils.quantityInput());
		dao.update(itemOrder);
	}
	
	/**
	 * delete any record with a given id
	 */
	public void deleteItemOrder() {
		ItemOrder itemOrder = new ItemOrder();
		Runner.LOGGER.info("Please enter the ID of the Item Order you want to delete:");
		itemOrder.setId(Utils.idInput());
		dao.setSearchID(ItemOrderSearchTypes.ITEM_ORDER.getSearchType());
		while (findRecord(itemOrder.getId()).isEmpty() == true) {
			Runner.LOGGER.info("Please enter the ID of the Item Order you want to delete:");
			itemOrder.setId(Utils.idInput());
		}
		dao.delete(itemOrder.getId());
	}
	
	/**
	 * delete any record with a given item/order id
	 * @param foriegnID
	 * @param fieldName
	 */
	public void deleteItemOrderByForiegnKey(int foriegnID, ItemOrderSearchTypes fieldName) {
		ArrayList<ItemOrder> itemOrders = new ArrayList<ItemOrder>();
		dao.setSearchID(fieldName.getSearchType());
		itemOrders = findRecord(foriegnID);
		for (ItemOrder i : itemOrders) {
			dao.delete(i.getId());
		}
	}
}
