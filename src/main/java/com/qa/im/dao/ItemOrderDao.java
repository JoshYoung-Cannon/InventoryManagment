package com.qa.im.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.sqldatatypes.ItemOrder;
import com.qa.im.utils.Config;

public class ItemOrderDao implements Dao<ItemOrder> {
	private static String searchID;

	public static String getSearchID() {
		return searchID;
	}

	public static void setSearchID(String searchID) {
		ItemOrderDao.searchID = searchID;
	}

	public void create(ItemOrder r) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db",
				Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into item_orders(item_id, order_id, quantity) values(" + r.getItemID()
					+ ", " + r.getOrderID() + ", " + r.getQuantity() + ")");
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not add: item " + r.getItemID() + " to order " + r.getOrderID());
		}
		// TODO Auto-generated method stub

	}

	public ArrayList<ItemOrder> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ItemOrder> readRecords(int rID) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(ItemOrder r) {
		// TODO Auto-generated method stub

	}

	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
