package com.qa.im.dao;

import java.util.ArrayList;

public interface Dao<Record> {
	//C
	public void create(Record r);
	//R
	public ArrayList<Record> readAll(ArrayList<Record> r);
	public ArrayList<Record> readRecords(int rID);
	//U
	public void update(Record r);
	//D
	public void delete(int id);
}
