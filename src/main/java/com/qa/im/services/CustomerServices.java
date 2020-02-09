package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.NameTypes;
import com.qa.im.Runner;
import com.qa.im.dao.CustomerDao;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.utils.Utils;

public class CustomerServices {
	/**
	 * Contains logic for: Add Customer View all Customers Update Customer Delete
	 * Customer
	 */
	private int firstNameMaxLength = 20, lastNameMaxLength = 30;

	CustomerDao dao = new CustomerDao();
	public void add() {
		/**
		 * Asks for the new Customers name and adds it to the Customers table
		 */
		// try to get a first name
		Runner.LOGGER.info("Please enter a frist name:");
		String firstName = Utils.strInput(firstNameMaxLength, NameTypes.FORENAME.getNameType());
		// try to get a last name
		Runner.LOGGER.info("Please enter a last name:");
		String lastName = Utils.strInput(lastNameMaxLength, NameTypes.SURNAME.getNameType());
		// create customer instance
		Customer customer = new Customer(firstName, lastName);
		// add to database
		dao.create(customer);
		Runner.LOGGER.info("Added customer: " + lastName + ", " + firstName);
		

	}

	public void viewAll() {
		/**
		 * Displays the Customer table
		 */
		// create customer arraylist
		ArrayList<Customer> customers = new ArrayList<Customer>();
		// populate arraylist
		dao.readAll(customers);
		// print result
		for (Customer i : customers) {
			Runner.LOGGER.info(i.getId() + ": " + i.getSurname() + ", " + i.getForname());
		}
	}

	public void findRecord(int recordID) {
		/**
		 * Search for a record by ID
		 */
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers = dao.readRecords(recordID);
		for (Customer i : customers) {
			Runner.LOGGER.info(i.getId() + ": " + i.getSurname() + ", " + i.getForname());
		}
	}
	
	public void update() {
		/**
		 * Updates a record in the Customer table
		 */
		// get customer selection
		// enter modification
		// update record
	}

	public void delete() {
		/**
		 * Removes a record from the Customer table
		 */
		// get customer selection
		// delete any connected order records
		// delete record
	}
}
