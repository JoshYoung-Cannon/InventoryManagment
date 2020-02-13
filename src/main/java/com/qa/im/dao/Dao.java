package com.qa.im.dao;

import java.util.ArrayList;

/**
 * basic functions required by all DAOs
 * @author Admin
 *
 * @param <Record>
 */
public interface Dao<Record> {
	//C
	public void create(Record r);
	//R
	public ArrayList<Record> readAll();
	public ArrayList<Record> readRecords(int rID);
	//U
	public void update(Record r);
	//D
	public void delete(int id);
}
