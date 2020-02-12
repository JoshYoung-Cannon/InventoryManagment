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

	private CustomerDao dao = new CustomerDao();

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

	}

	public void viewAll() {
		/**
		 * Displays the Customer table
		 */
		// create customer arraylist
		ArrayList<Customer> customers = new ArrayList<Customer>();
		// populate arraylist
		customers = dao.readAll();
		// print result
		for (Customer i : customers) {
			Runner.LOGGER.info(i.getId() + ": " + i.getSurname() + ", " + i.getForname());
		}
	}

	public boolean findRecord(int recordID) {
		/**
		 * Search for a record by ID
		 */
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers = dao.readRecords(recordID);
		boolean customersFound = false;
		if (customers.size() > 0) {
			for (Customer i : customers) {
				Runner.LOGGER.info(i.getId() + ": " + i.getSurname() + ", " + i.getForname());
			}
			customersFound = true;
		}
		else {
			Runner.LOGGER.info("Could not find Customer with id: " + recordID);
		}
		return customersFound;
	}

	public void update() {
		/**
		 * Updates a record in the Customer table
		 */
		Customer customer = new Customer();
		Runner.LOGGER.info("Please enter the ID of the Customer you want to update:");
		customer.setId(Utils.idInput());
		Runner.LOGGER.info("Update Customer: ");
		while (findRecord(customer.getId()) == false) {
			Runner.LOGGER.info("Please enter the ID of the Customer you want to update:");
			customer.setId(Utils.idInput());
			Runner.LOGGER.info("Update Customer: ");
		}
		// enter modification
		Runner.LOGGER.info("Please enter a frist name:");
		String firstName = Utils.strInput(firstNameMaxLength, NameTypes.FORENAME.getNameType());
		Runner.LOGGER.info("Please enter a last name:");
		String lastName = Utils.strInput(lastNameMaxLength, NameTypes.SURNAME.getNameType());
		// update record
		customer.setForname(firstName);
		customer.setSurname(lastName);
		dao.update(customer);
	}

	public void delete() {
		/**
		 * Removes a record from the Customer table
		 */
		OrderServices orderServices = new OrderServices();
		// get customer selection
		Customer customer = new Customer();
		Runner.LOGGER.info("Please enter the ID of the Customer you want to delete:");
		customer.setId(Utils.idInput());
		while (findRecord(customer.getId()) == false) {
			Runner.LOGGER.info("Please enter the ID of the Customer you want to delete:");
			customer.setId(Utils.idInput());
		}
		// delete any connected order records
		orderServices.deleteOrder(customer.getId());
		// delete record
		dao.delete(customer.getId());
	}
}
