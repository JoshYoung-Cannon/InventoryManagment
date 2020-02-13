package com.qa.im.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.sqldatatypes.Order;
import com.qa.im.utils.Config;

/**
 * Contains all DAO actions for the Orders table
 * @author Admin
 *
 */
public class OrderDao implements Dao<Order> {
	private static String searchID;
	private double discountThreshold = 10000;
	private double discountRate = 0.1;

	public String getSearchID() {
		return searchID;
	}

	/**
	 * Define which field you want to search through the orders table with
	 * 
	 * @param searchID
	 */
	public void setSearchID(String searchID) {
		OrderDao.searchID = searchID;
	}

	public double getDiscountThreshold() {
		return discountThreshold;
	}

	/**
	 * Define the highest cost of a value that does not require a discount
	 * 
	 * @param discountThreshold
	 */
	public void setDiscountThreshold(double discountThreshold) {
		this.discountThreshold = discountThreshold;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	/**
	 * Define the % discount as a decimal e.g. 0.1 = 10%
	 * 
	 * @param discountRate
	 */
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	/**
	 * Create a new Order record
	 */
	public void create(Order r) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql:35.246.120.12/inventory_db",
				Config.username, Config.password); Statement statement = connection.createStatement()) {
			statement.executeUpdate("insert into orders(customer_id) values(" + r.getCustomer_id() + ")");
			Runner.LOGGER.info("Order added");
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not add: Customer " + r.getCustomer_id() + " to Orders table");
			Runner.LOGGER.info(e);
		}
	}

	/*
	 * Copy the entire Orders table into a ArrayList
	 */
	public ArrayList<Order> readAll() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql:35.246.120.12/inventory_db",
				Config.username, Config.password); Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("select * from orders");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int customerID = resultSet.getInt("customer_id");
				double total = resultSet.getDouble("total");
				Order order = new Order(id, customerID, total);
				orders.add(order);
			}
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not read Customers table");
			Runner.LOGGER.info(e);
		}
		return orders;
	}

	/**
	 * Copy all responses to a value in a specific field of the Orders table into a
	 * ArrayList
	 */
	public ArrayList<Order> readRecords(int rID) {
		ArrayList<Order> orders = new ArrayList<Order>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql:35.246.120.12/inventory_db",
				Config.username, Config.password); Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement
					.executeQuery("select * from orders where " + OrderDao.searchID + " = " + rID);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int customerID = resultSet.getInt("customer_id");
				double total = resultSet.getDouble("total");
				Order order = new Order(id, customerID, total);
				orders.add(order);
			}
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not read Customers table");
			Runner.LOGGER.info(e);
		}
		return orders;
	}

	/**
	 * Find the total cost of each order, apply relevant discounts and save the
	 * results to the database
	 */
	public void update(Order r) {
		double total = 0;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql:35.246.120.12/inventory_db",
				Config.username, Config.password); Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(
					"select sum(quantity * item_value) from (select quantity, item_value, order_id from items join item_orders on items.id = item_orders.item_id) as item_quantity where order_id = "
							+ r.getId());
			while (resultSet.next()) {
				total = resultSet.getDouble("sum(quantity * item_value)");
			}
			if (total > discountThreshold) {
				double discount = total * discountRate;
				total -= discount;
			}
			r.setTotal(total);
			try {
				statement.executeUpdate("update orders set total = " + r.getTotal() + " where id = " + r.getId());
			} catch (Exception e) {
				Runner.LOGGER.info("Error could not update Order total");
				Runner.LOGGER.info(e);
			}
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not read Item / Item Orders join");
			Runner.LOGGER.info(e);
		}
	}

	/**
	 * Delete a record from the Orders table
	 */
	public void delete(int id) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql:35.246.120.12/inventory_db",
				Config.username, Config.password); Statement statement = connection.createStatement()) {
			statement.executeUpdate("delete from orders where id = " + id);
			Runner.LOGGER.info("Order deleted");
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not delete order record");
			Runner.LOGGER.info(e);
		}
	}
}
