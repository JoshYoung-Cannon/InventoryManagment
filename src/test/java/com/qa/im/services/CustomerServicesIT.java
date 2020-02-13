package com.qa.im.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.im.dao.CustomerDao;
import com.qa.im.enums.NameTypes;
import com.qa.im.sqldatatypes.Customer;
import com.qa.im.utils.Utils;

import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServicesIT {

	@Mock
	private CustomerDao dao;
	private OrderServices orderServices;
	
	@Spy	
	@InjectMocks
	private CustomerServices customerServices;
	
	@Test
	public void getStrInputTest() {
		String firstName = "John";
		String lastName = "Smith";
		Mockito.doReturn(firstName).when(customerServices).getStrInput(20, NameTypes.FORENAME.getNameType());
		Mockito.doReturn(lastName).when(customerServices).getStrInput(30, NameTypes.SURNAME.getNameType());
		assertEquals("John", customerServices.getStrInput(20, NameTypes.FORENAME.getNameType()));
		assertEquals("Smith", customerServices.getStrInput(30, NameTypes.SURNAME.getNameType()));
	}
	
	@Test
	public void getIdInputTest() {
		int id = 1;
		Mockito.doReturn(id).when(customerServices).getIdInput();
		assertEquals(1, customerServices.getIdInput());
	}
	
	@Test
	public void customerCreateIT() {
		String firstName = "John";
		String lastName = "Smith";
		Mockito.doReturn(firstName).when(customerServices).getStrInput(20, NameTypes.FORENAME.getNameType());
		Mockito.doReturn(lastName).when(customerServices).getStrInput(30, NameTypes.SURNAME.getNameType());
		Customer customer = new Customer(firstName, lastName);
		customerServices.add();
		Mockito.verify(dao, Mockito.times(1)).create(customer);
	}
	
	@Test
	public void customerViewAllIT() {
		customerServices.viewAll();
		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void customerFindRecordIT() {
		int recordID = 1;
		String firstName = "John";
		String lastName = "Smith";
		ArrayList<Customer> customers = new ArrayList<>();
		Customer customer = new Customer(recordID, firstName, lastName);
		customers.add(customer);
		Mockito.doReturn(customers).when(dao).readRecords(recordID);
		assertEquals(true, customerServices.findRecord(recordID));
	}
	
	@Test
	public void customerUpdateIT() {
		String firstName = "John";
		String lastName = "Smith";
		Mockito.doReturn(firstName).when(customerServices).getStrInput(20, NameTypes.FORENAME.getNameType());
		Mockito.doReturn(lastName).when(customerServices).getStrInput(30, NameTypes.SURNAME.getNameType());
		int id = 1;
		Mockito.doReturn(id).when(customerServices).getIdInput();
		Mockito.doReturn(true).when(customerServices).findRecord(id);
		Customer customer = new Customer(id, firstName, lastName);
		customerServices.update();
		Mockito.verify(dao, Mockito.times(1)).update(customer);
	}
	
	@Test
	public void customerDeleteIT() {
		
	}
	
}
