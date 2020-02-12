package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.dao.ItemDao;
import com.qa.im.enums.ItemOrderSearchTypes;
import com.qa.im.enums.NameTypes;
import com.qa.im.sqldatatypes.Item;
import com.qa.im.utils.Utils;

/**
 * Contains logic for: 
 * Add Item 
 * View all Items 
 * Update Item 
 * Delete Item
 */
public class ItemServices {

	private int itemNameMaxLength = 100;
	private double maxValue = 99999.99;

	private ItemDao dao = new ItemDao();

	/**
	 * Asks for the new Items name and value then adds it to the Items table
	 */
	public void add() {
		Runner.LOGGER.info("Please enter an Item name:");
		String itemName = Utils.strInput(itemNameMaxLength, NameTypes.ITEM_NAME.getNameType());
		Runner.LOGGER.info("Please enter an Item value (range: £0.01 - £99999.99):");
		double itemValue = Utils.valueInput(maxValue);
		dao.create(new Item(itemName, itemValue));
	}

	/**
	 * Displays the Item table
	 */
	public void viewAll() {
		ArrayList<Item> items = new ArrayList<Item>();
		items = dao.readAll();
		for (Item i : items) {
			Runner.LOGGER.info(i.getId() + ": " + i.getName() + " £" + String.format("%.2f", i.getValue()));
		}
	}

	/**
	 * Find all items linked to a specified id and return true if it is in the database
	 * @param recordID
	 * @return
	 */
	public boolean findRecord(int recordID) {
		ArrayList<Item> items = new ArrayList<Item>();
		items = dao.readRecords(recordID);
		boolean itemsFound = false;
		if (items.size() > 0) {
			for (Item i : items) {
				Runner.LOGGER.info(i.getId() + ": " + i.getName() + " £" + String.format("%.2f", i.getValue()));
			}
			itemsFound = true;
		} else {
			Runner.LOGGER.info("Could not find Item record with id: " + recordID);
		}
		return itemsFound;
	}

	/**
	 * Update item name
	 */
	public void updateItem() {
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
		Runner.LOGGER.info("Please enter a Item name:");
		itemName = Utils.strInput(itemNameMaxLength, NameTypes.ITEM_NAME.getNameType());
		Runner.LOGGER.info("Please enter a Item value:");
		itemVal = Utils.valueInput(maxValue);
		item.setName(itemName);
		item.setValue(itemVal);		
		dao.update(item);
	}

	/**
	 * delete an item
	 */
	public void deleteItem() {
		ItemOrderServices itemOrderServices = new ItemOrderServices();
		Item item = new Item();
		Runner.LOGGER.info("Please enter the ID of the Item you want to delete:");
		item.setId(Utils.idInput());
		while (findRecord(item.getId()) == false) {
			Runner.LOGGER.info("Please enter the ID of the Item you want to delete:");
			item.setId(Utils.idInput());
		}
		itemOrderServices.deleteItemOrderByForiegnKey(item.getId(), ItemOrderSearchTypes.ITEM);
		dao.delete(item.getId());
		
	}
}
