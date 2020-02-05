package com.qa.inventorymanagement;

import java.util.ArrayList;

public interface Dao<Record> {
	//C
	public void create(Record r);
	//R
	public ArrayList<Record> readAll();
	public ArrayList<Record> readRecords(int id);
	//U
	public void update(Record r);
	//D
	public void delete(int id);
}
