package com.qa.im.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.sqldatatypes.Item;
import com.qa.im.utils.Config;

/**
 * Contains all DAO actions for the Items table
 * 
 * @author Admin
 *
 */
public class ItemDao implements Dao<Item> {

	/**
	 * Create a new Item record
	 */
	public void create(Item r) {
		try (Connection connection = DriverManager.getConnection(Config.databaseConnection,
				Config.username, Config.password); Statement statement = connection.createStatement()) {
			statement.executeUpdate(
					"insert into items(item_name, item_value) values('" + r.getName() + "', " + r.getValue() + ")");
			Runner.LOGGER.info("Added item: " + r.getName() + " £" + r.getValue());
		} catch (Exception e) {
			Runner.LOGGER.error("Error could not add: " + r.getName());
			Runner.LOGGER.error(e);
		}

	}

	/*
	 * Copy the entire Items table into a ArrayList
	 */
	public ArrayList<Item> readAll() {
		ArrayList<Item> items = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(Config.databaseConnection,
				Config.username, Config.password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from items")) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String itemName = resultSet.getString("item_name");
				double itemVal = resultSet.getDouble("item_value");
				Item item = new Item(id, itemName, itemVal);
				items.add(item);
			}
		} catch (Exception e) {
			Runner.LOGGER.error("Error could not read Items table");
			Runner.LOGGER.error(e);
		}

		return items;
	}

	/**
	 * Copy all responses to a value in a specific field of the Items table into a
	 * ArrayList
	 */
	public ArrayList<Item> readRecords(int rID) {
		ArrayList<Item> items = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(Config.databaseConnection,
				Config.username, Config.password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from items where id = " + rID)) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String itemName = resultSet.getString("item_name");
				double itemVal = resultSet.getDouble("item_value");
				Item item = new Item(id, itemName, itemVal);
				items.add(item);
			}
		} catch (Exception e) {
			Runner.LOGGER.error("Error could not read Items table");
			Runner.LOGGER.error(e);
		}

		return items;
	}

	/**
	 * Update the Item name and value of a specific record
	 */
	public void update(Item r) {
		try (Connection connection = DriverManager.getConnection(Config.databaseConnection,
				Config.username, Config.password); Statement statement = connection.createStatement()) {
			statement.executeUpdate("update items set item_name = '" + r.getName() + "', item_value = " + r.getValue()
					+ " where id = " + r.getId());
			Runner.LOGGER.info("Updated item: " + r.getId() + " to: " + r.getName() + " £" + r.getValue());
		} catch (Exception e) {
			Runner.LOGGER.error("Error could not update Item record");
			Runner.LOGGER.error(e);
		}

	}

	/**
	 * Delete a record from the Items table
	 */
	public void delete(int id) {
		try (Connection connection = DriverManager.getConnection(Config.databaseConnection,
				Config.username, Config.password); Statement statement = connection.createStatement()) {
			statement.executeUpdate("delete from items where id = " + id);
			Runner.LOGGER.info("Item deleted");
		} catch (Exception e) {
			Runner.LOGGER.error("Error could not delete Item record");
			Runner.LOGGER.error(e);
		}

	}

}
