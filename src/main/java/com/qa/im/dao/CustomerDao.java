package com.qa.im.dao;

import java.util.ArrayList;

import com.qa.im.sqldatatypes.Customer;

public class CustomerDao implements Dao<Customer>{

	public void create(Customer r) {
		//insert into customers(customer_name) values('Customer Name1');
		
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Customer> readAll() {
		//select * from customers;
		
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Customer> readRecords(Customer r) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Customer r) {
		//update customers set customer_name = "Updated name" where id = 1;
		
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
