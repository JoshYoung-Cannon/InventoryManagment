package com.qa.im.services;

public class ItemOrderServices {
	/**
	 * Contains logic for:
	 * Add Item to an Order
	 * Update Item quantity in Order
	 * Delete an Item from Order
	 */
	public void addItemOrder() {
		/**
		 * Link an item to an order and save the quantity desired
		 */
		// create itemOrder instance
		// get order id and item id
		// get quantity
		// add to itemOrders
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
	public void deleteItemOrderByOrders() {
		/**
		 * delete any record with a given order id
		 */
		// get order id
		// store an array of appropriate itemOrder ids
		// go through array removing records
	}
	public void deleteItemOrderByItems() {
		/**
		 * delete any record with a given item id
		 */
		// get item id
		// store array of appropriate itemOrder ids
		// go through array removing records
		// check if any orders have no items remove those that dont
		// update all order costs
	}
}
