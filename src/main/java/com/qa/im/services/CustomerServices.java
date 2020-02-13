package com.qa.im.services;

import java.util.ArrayList;

import com.qa.im.Runner;
import com.qa.im.dao.CustomerDao;
import com.qa.im.enums.NameTypes;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.utils.Utils;

/**
 * Contains logic for: 
 * Add Customer 
 * View all Customers 
 * Update Customer 
 * Delete Customer
 */
public class CustomerServices {
	
	private int firstNameMaxLength = 20, lastNameMaxLength = 30;

	private CustomerDao dao = new CustomerDao();

	public int getFirstNameMaxLength() {
		return firstNameMaxLength;
	}

	public void setFirstNameMaxLength(int firstNameMaxLength) {
		this.firstNameMaxLength = firstNameMaxLength;
	}

	public int getLastNameMaxLength() {
		return lastNameMaxLength;
	}

	public void setLastNameMaxLength(int lastNameMaxLength) {
		this.lastNameMaxLength = lastNameMaxLength;
	}

	/**
	 * Asks for the new Customers name and adds it to the Customers table
	 */
	public void add() {
		Runner.LOGGER.info("Please enter a frist name:");
		String firstName = Utils.strInput(firstNameMaxLength, NameTypes.FORENAME.getNameType());
		Runner.LOGGER.info("Please enter a last name:");
		String lastName = Utils.strInput(lastNameMaxLength, NameTypes.SURNAME.getNameType());
		Customer customer = new Customer(firstName, lastName);
		dao.create(customer);

	}

	/**
	 * Displays the Customer table
	 */
	public void viewAll() {
		ArrayList<Customer> customers = new ArrayList<>();
		customers = dao.readAll();
		for (Customer i : customers) {
			Runner.LOGGER.info(i.getId() + ": " + i.getSurname() + ", " + i.getForname());
		}
	}

	/**
	 * Search for a record by ID returns true if it is in the database
	 * @param recordID
	 * @return
	 */
	public boolean findRecord(int recordID) {
		ArrayList<Customer> customers = new ArrayList<>();
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

	/**
	 * Updates a record in the Customer table
	 */
	public void update() {
		Customer customer = new Customer();
		Runner.LOGGER.info("Please enter the ID of the Customer you want to update:");
		customer.setId(Utils.idInput());
		Runner.LOGGER.info("Update Customer: ");
		while (findRecord(customer.getId()) == false) {
			Runner.LOGGER.info("Please enter the ID of the Customer you want to update:");
			customer.setId(Utils.idInput());
			Runner.LOGGER.info("Update Customer: ");
		}
		Runner.LOGGER.info("Please enter a frist name:");
		String firstName = Utils.strInput(firstNameMaxLength, NameTypes.FORENAME.getNameType());
		Runner.LOGGER.info("Please enter a last name:");
		String lastName = Utils.strInput(lastNameMaxLength, NameTypes.SURNAME.getNameType());
		customer.setForname(firstName);
		customer.setSurname(lastName);
		dao.update(customer);
	}

	/**
	 * Removes a record from the Customer table
	 */
	public void delete() {
		OrderServices orderServices = new OrderServices();
		Customer customer = new Customer();
		Runner.LOGGER.info("Please enter the ID of the Customer you want to delete:");
		customer.setId(Utils.idInput());
		while (findRecord(customer.getId()) == false) {
			Runner.LOGGER.info("Please enter the ID of the Customer you want to delete:");
			customer.setId(Utils.idInput());
		}
		orderServices.deleteOrder(customer.getId());
		dao.delete(customer.getId());
	}
}
