package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.NameTypes;
import com.qa.im.Runner;
import com.qa.im.dao.ItemDao;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.sqldatatypes.Item;
import com.qa.im.utils.Utils;

public class ItemServices {
	/**
	 * Contains logic for: Add Item View all Items Update Item Delete Item
	 */

	private int itemNameMaxLength = 100;
	private double maxValue = 99999.99;

	private ItemDao dao = new ItemDao();

	public void add() {
		/**
		 * Asks for the new Items name and value then adds it to the Items table
		 */
		// try to get a name
		Runner.LOGGER.info("Please enter an item name:");
		String itemName = Utils.strInput(itemNameMaxLength, NameTypes.FORENAME.getNameType());
		// try to get a value
		Runner.LOGGER.info("Please enter an item value (range: £0.00 - £99999.99):");
		double itemValue = Utils.valueInput(maxValue);
		// add to database
		dao.create(new Item(itemName, itemValue));
		Runner.LOGGER.info("Added item: " + itemName + " £" + itemValue);
	}

	public void viewAll() {
		/**
		 * Displays the Item table
		 */
		// create customer arraylist
		ArrayList<Item> items = new ArrayList<Item>();
		// populate arraylist
		items = dao.readAll();
		// print result
		for (Item i : items) {
			Runner.LOGGER.info(i.getId() + ": " + i.getName() + " £" + String.format("%.2f", i.getValue()));
		}
	}

	public boolean findRecord(int recordID) {
		/**
		 * Search for a record by ID
		 */
		ArrayList<Item> items = new ArrayList<Item>();
		items = dao.readRecords(recordID);
		boolean itemsFound = false;
		if (items.size() > 0) {
			for (Item i : items) {
				Runner.LOGGER.info(i.getId() + ": " + i.getName() + " £" + String.format("%.2f", i.getValue()));
			}
			itemsFound = true;
		} else {
			Runner.LOGGER.info("Could not find item record with id: " + recordID);
		}
		return itemsFound;
	}

	public void updateItem() {
		/**
		 * Update item name
		 */
		// get item selection
		String itemName;
		double itemVal;
		Item item = new Item();
		Runner.LOGGER.info("Please enter the ID of the Item you want to update:");
		item.setId(Utils.idInput());
		Runner.LOGGER.info("Update name of Item: ");
		while (findRecord(item.getId()) == false) {
			Runner.LOGGER.info("Please enter the ID of the Item you want to update:");
			item.setId(Utils.idInput());
			Runner.LOGGER.info("Update Item: ");
		}
		// enter modification
		Runner.LOGGER.info("Please enter a item name:");
		itemName = Utils.strInput(itemNameMaxLength, NameTypes.ITEMNAME.getNameType());
		Runner.LOGGER.info("Please enter a item value:");
		itemVal = Utils.valueInput(maxValue);
		item.setName(itemName);
		item.setValue(itemVal);		
		// update record
		dao.update(item);
	}

	public void deleteItem() {
		/**
		 * delete an item
		 */
		// get item selection
		Item item = new Item();
		Runner.LOGGER.info("Please enter the ID of the item you want to delete:");
		item.setId(Utils.idInput());
		while (findRecord(item.getId()) == false) {
			Runner.LOGGER.info("Please enter the ID of the item you want to delete:");
			item.setId(Utils.idInput());
		}
		// delete any connected itemOrder records
		// update orders
		// delete item
		dao.delete(item.getId());
		Runner.LOGGER.info("Item deleted");
	}
}
