package com.qa.im.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.sqldatatypes.Order;
import com.qa.im.utils.Config;

public class OrderDao implements Dao<Order>{

	public void create(Order r) {
		//insert into orders(customer_id) values(2);
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into orders(customer_id) values(" + r.getCustomer_id() + ")");
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not add: Customer " + r.getCustomer_id() + " to Orders table");
		}
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Order> readAll() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
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
		}
		// TODO Auto-generated method stub
		return orders;
	}

	public ArrayList<Order> readRecords(int rID) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Order r) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
