package com.qa.im.sqldatatypes;

/**
 * This class contains all the variables needed for the Customers table fields
 * The customer.id is generated by the SQL database
 * 
 * @author Admin
 *
 */
public class Customer {
	private int id;
	private String forname;
	private String surname;

	/**
	 * constructor for reading from the database
	 * 
	 * @param forname
	 * @param surname
	 */

	public Customer(int id, String forname, String surname) {
		super();
		this.id = id;
		this.forname = forname;
		this.surname = surname;
	}

	/**
	 * constructor for making new record
	 * 
	 * @param forname
	 * @param surname
	 */
	public Customer(String forname, String surname) {
		super();
		this.forname = forname;
		this.surname = surname;
	}

	public Customer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getForname() {
		return forname;
	}

	public void setForname(String forname) {
		this.forname = forname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
