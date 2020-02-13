package com.qa.im.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.utils.Config;

/**
 * Contains all DAO actions for the Customers table
 * 
 * @author Admin
 *
 */
public class CustomerDao implements Dao<Customer> {

	/**
	 * Create a new Customer record
	 */
	public void create(Customer r) {
		try (Connection connection = DriverManager.getConnection(Config.databaseConnection, Config.username,
				Config.password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(
					"insert into customers(customer_name) values('" + r.getSurname() + ", " + r.getForname() + "')");
			Runner.LOGGER.info("Added customer: " + r.getSurname() + ", " + r.getForname());
		} catch (Exception e) {
			Runner.LOGGER.error("Error could not add: " + r.getSurname() + ", " + r.getForname());
			Runner.LOGGER.error(e.getMessage());
		}
	}

	/*
	 * Copy the entire Customers table into a ArrayList
	 */
	public ArrayList<Customer> readAll() {
		ArrayList<Customer> customers = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(Config.databaseConnection, Config.username,
				Config.password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from customers")) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String fullName[] = resultSet.getString("customer_name").split(", ");
				Customer customer = new Customer(id, fullName[1], fullName[0]);
				customers.add(customer);
			}
		} catch (Exception e) {
			Runner.LOGGER.error("Error could not read Customers table");
			Runner.LOGGER.error(e);
		}

		return customers;
	}

	/**
	 * Copy all responses to a value in a specific field of the Customers table into
	 * a ArrayList
	 */
	public ArrayList<Customer> readRecords(int rID) {
		ArrayList<Customer> customers = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(Config.databaseConnection, Config.username,
				Config.password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from customers where id = " + rID)) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String fullName[] = resultSet.getString("customer_name").split(", ");
				Customer customer = new Customer(id, fullName[1], fullName[0]);
				customers.add(customer);
			}
		} catch (Exception e) {
			Runner.LOGGER.error("Error could not read Customers table");
			Runner.LOGGER.error(e);
		}

		return customers;
	}

	/**
	 * Update the Customer name of a specific record
	 */
	public void update(Customer r) {
		try (Connection connection = DriverManager.getConnection(Config.databaseConnection, Config.username,
				Config.password); Statement statement = connection.createStatement()) {
			statement.executeUpdate("update customers set customer_name = '" + r.getSurname() + ", " + r.getForname()
					+ "' where id = " + r.getId());
			Runner.LOGGER.info("Updated customer: " + r.getId() + " to: " + r.getSurname() + ", " + r.getForname());
		} catch (Exception e) {
			Runner.LOGGER.error("Error could not update Customer record");
			Runner.LOGGER.error(e);
		}

	}

	/**
	 * Delete a record from the Customers table
	 */
	public void delete(int id) {
		try (Connection connection = DriverManager.getConnection(Config.databaseConnection, Config.username,
				Config.password); Statement statement = connection.createStatement()) {
			statement.executeUpdate("delete from customers where id = " + id);
			Runner.LOGGER.info("Customer deleted");
		} catch (Exception e) {
			Runner.LOGGER.error("Error could not delete Customer record");
			Runner.LOGGER.error(e);
		}

	}

}
