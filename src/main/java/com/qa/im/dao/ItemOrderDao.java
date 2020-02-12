package com.qa.im.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.sqldatatypes.ItemOrder;
import com.qa.im.utils.Config;

public class ItemOrderDao implements Dao<ItemOrder> {
	private static String searchID;

	public String getSearchID() {
		return searchID;
	}

	public void setSearchID(String searchID) {
		ItemOrderDao.searchID = searchID;
	}

	public void create(ItemOrder r) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db",
				Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into item_orders(item_id, order_id, quantity) values(" + r.getItemID()
					+ ", " + r.getOrderID() + ", " + r.getQuantity() + ")");
			Runner.LOGGER.info("Created Item Order: item_id: " + r.getItemID() + " order_id:  " + r.getOrderID() + " quantity: " + r.getQuantity());
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not add: item " + r.getItemID() + " to order " + r.getOrderID());
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub

	}

	public ArrayList<ItemOrder> readAll() {
		// select * from item_orders;
		ArrayList<ItemOrder> itemOrders = new ArrayList<ItemOrder>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db",
				Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from item_orders");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int itemID = resultSet.getInt("item_id");
				int orderID = resultSet.getInt("order_id");
				int quantity = resultSet.getInt("quantity");
				ItemOrder itemOrder = new ItemOrder(id, itemID, orderID, quantity);
				itemOrders.add(itemOrder);
			}
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not read ItemOrders table");
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub
		return itemOrders;
	}

	public ArrayList<ItemOrder> readRecords(int rID) {
		ArrayList<ItemOrder> itemOrders = new ArrayList<ItemOrder>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db",
				Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from item_orders where " + ItemOrderDao.searchID + " = " + rID);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int itemID = resultSet.getInt("item_id");
				int orderID = resultSet.getInt("order_id");
				int quantity = resultSet.getInt("quantity");
				ItemOrder itemOrder = new ItemOrder(id, itemID, orderID, quantity);
				itemOrders.add(itemOrder);
			}
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not read ItemOrders table");
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub
		return itemOrders;
	}

	public void update(ItemOrder r) {
		// update item_orders set quantity = 100 where order_id = 2 and item_id = 4
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db",
				Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("update item_orders set quantity = " + r.getQuantity() + " where id = " + r.getId());
			Runner.LOGGER.info("Updated Item Order: " + r.getId() + " To item_id: " + r.getItemID() + " order_id:  " + r.getOrderID() + " quantity: " + r.getQuantity());
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not update item_order record: " + r.getId());
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub

	}

	public void delete(int id) {
		//delete from item_orders where id = 2
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("delete from item_orders where id = " + id);
			Runner.LOGGER.info("Deleted Item Order");
			}
		catch (Exception e) {
			Runner.LOGGER.info("Error could not delete Item Order record");
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub
	}

}
