package com.qa.im.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.utils.Config;

public class CustomerDao implements Dao<Customer> {

	public void create(Customer r) {
		// insert into customers(customer_name) values('Customer Name1');
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into customers(customer_name) values('" + r.getSurname() + ", " + r.getForname() + "')");
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not add: " + r.getSurname() + ", " + r.getForname());
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub

	}

	public ArrayList<Customer> readAll() {
		// select * from customers;
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from customers");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String fullName[] = resultSet.getString("customer_name").split(", ");
				Customer customer = new Customer(id, fullName[1], fullName[0]);
				customers.add(customer);
			}
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not read Customers table");
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub
		return customers;
	}

	public ArrayList<Customer> readRecords(int rID) {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from customers where id = " + rID);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String fullName[] = resultSet.getString("customer_name").split(", ");
				Customer customer = new Customer(id, fullName[1], fullName[0]);
				customers.add(customer);
			}
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not read Customers table");
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub
		return customers;
	}

	public void update(Customer r) {
		// update customers set customer_name = "Updated name" where id = 1;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("update customers set customer_name = '" + r.getSurname() + ", " + r.getForname() + "' where id = " + r.getId());
			}
		catch (Exception e) {
			Runner.LOGGER.info("Error could not update Customer record");
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub

	}

	public void delete(int id) {
		//delete from customers where id = 1;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("delete from customers where id = " + id);
			}
		catch (Exception e) {
			Runner.LOGGER.info("Error could not delete Customer record");
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub
	}

}
