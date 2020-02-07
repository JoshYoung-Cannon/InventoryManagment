package com.qa.im.services;

public class OrderServices {
	/**
	 * Contains logic for:
	 * Calculate cost of Order
	 * Add Order
	 * View all Orders
	 */
	public void add() {
		/**
		 * create order instance
		 * get customer id
		 * get at least 1 item id
		 * add customer id to order
		 * add item to itemOrders and link to current order
		 */
	}
	public void viewAll() {
		/**
		 * create order listarray
		 * populate listarray
		 * print result
		 */
	}
	public void calculateCost() {
		/**
		 * get order id
		 * look through linked item orders getting quantity costs (join item and itemOrders)
		 * sum the values of quantity cost
		 * apply discount
		 * save total cost
		 */
	}
}
