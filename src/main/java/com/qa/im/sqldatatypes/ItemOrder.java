package com.qa.im.sqldatatypes;

/**
 * This class contains all the variables needed for the item_orders table fields
 * The itemOrder.id is generated by the SQL database, if quantity is not given it defaults one
 * @author Admin
 *
 */
public class ItemOrder {
	private int id;
	private int itemID;
	private int orderID;
	private int quantity;

	
	/**
	 * constructor for reading from database
	 * @param id
	 * @param itemID
	 * @param orderID
	 * @param quantity
	 */
	public ItemOrder(int id, int itemID, int orderID, int quantity) {
		super();
		this.id = id;
		this.itemID = itemID;
		this.orderID = orderID;
		this.quantity = quantity;
	}
	
	/**
	 * constructor for making a new item order
	 * @param itemID
	 * @param orderID
	 * @param quantity
	 */
	public ItemOrder(int itemID, int orderID, int quantity) {
		super();
		this.itemID = itemID;
		this.orderID = orderID;
		this.quantity = quantity;
	}

	/**
	 * constructor where quantity will default to 1
	 * @param itemID
	 * @param orderID
	 */
	public ItemOrder(int itemID, int orderID) {
		super();
		this.itemID = itemID;
		this.orderID = orderID;
		this.quantity = 1;
	}

	public ItemOrder() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
