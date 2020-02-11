package com.qa.im.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.sqldatatypes.Item;
import com.qa.im.utils.Config;

public class ItemDao implements Dao<Item> {

	public void create(Item r) {
		// insert into items(item_name, item_value) values('Item Name', 101.00);
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db",
				Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into items(item_name, item_value) values('" + r.getName() + "', "
					+ r.getValue() + ")");
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not add: " + r.getName());
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub
	}

	public ArrayList<Item> readAll() {
		// select * from items;
		ArrayList<Item> items = new ArrayList<Item>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db",
				Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from items");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String itemName = resultSet.getString("item_name");
				double itemVal = resultSet.getDouble("item_value");
				Item item = new Item(id, itemName, itemVal);
				items.add(item);
			}
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not read Items table");
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub
		return items;
	}

	public ArrayList<Item> readRecords(int rID) {
		ArrayList<Item> items = new ArrayList<Item>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db",
				Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from items where id = " + rID);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String itemName = resultSet.getString("item_name");
				double itemVal = resultSet.getDouble("item_value");
				Item item = new Item(id, itemName, itemVal);
				items.add(item);
			}
		} catch (Exception e) {
			Runner.LOGGER.info("Error could not read Customers table");
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub
		return items;
	}

	public void update(Item r) {
		//update items set  item_name = "Updated name", item_value = 55 where id = 1;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("update items set item_name = '" + r.getName() + "', item_value = " + r.getValue() + " where id = " + r.getId());
			}
		catch (Exception e) {
			Runner.LOGGER.info("Error could not update Customer record");
			Runner.LOGGER.info(e);
		}
		
		// TODO Auto-generated method stub

	}

	public void delete(int id) {
		//delete from items where id = 1;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.246.120.12/inventory_db", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("delete from items where id = " + id);
			}
		catch (Exception e) {
			Runner.LOGGER.info("Error could not delete item record");
			Runner.LOGGER.info(e);
		}
		// TODO Auto-generated method stub

	}

}
