package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.sqldatatypes.Order;

public class OrderServices {
	/**
	 * Contains logic for: Calculate cost of Order Add Order View all Orders Delete
	 * Order
	 */
	public void add() {
//		 create order instance
//		 get customer id
//		 get at least 1 item id
//		 add customer id to order
//		 add item to itemOrders and link to current order
	}

	public void viewAll() {
//		 create order listarray
//		 populate listarray
//		 print result

	}
	
	public ArrayList<Order> findRecord(int recordID) {
//		create order listarray
//		create order instance
//		get orders
//		return orders
		return null;
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
