package com.qa.im.utils;

/**
 * Holds the SQL username and password
 * @author Admin
 *
 */
public class Config {
	private static String username;
	private static String password;
	public final static String databaseConnection = "jdbc:mysql://35.246.120.12:3306/inventory_db";
	
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		Config.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		Config.password = password;
	}
	
}
