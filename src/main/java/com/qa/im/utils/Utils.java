package com.qa.im.utils;

import java.util.Scanner;

import com.qa.im.Runner;

/**
 * Holds all user input methods
 * @author Admin
 *
 */
public class Utils {
	/**
	 * Used to get username and password string values from the user
	 * inputName is simply a string to inform the user what field they are filling
	 * @param inputName
	 * @return
	 */
	public static String logInput(String inputName) {
		Scanner scanner = new Scanner(System.in);
		String strVal = null;
		while (strVal == null) {
			strVal = scanner.nextLine();
			if (strVal.length() == 0) {
				Runner.LOGGER.info("Please enter a " + inputName);
				strVal = null;
			}
		}
		return strVal;
	}
	
	/**
	 * Used to get name values e.g customer name
	 * charLimit is used to ensure the input with match with the SQL varchar size
	 * inputName is simply a string to inform the user what field they are filling
	 * @param charLimit
	 * @param inputName
	 * @return
	 */
	public static String strInput(int charLimit, String inputName) {
		Scanner scanner = new Scanner(System.in);
		String strVal = null;
		while (strVal == null) {
			strVal = scanner.nextLine().toUpperCase();
			if ((strVal.length() == 0) || (strVal.length() > charLimit)) {
				Runner.LOGGER.info("Please enter a " + inputName + " with 0-" + charLimit + " characters");
				strVal = null;
			}
		}
		return strVal;
	}

	/**
	 * Used to get int id values e.g customer id
	 * will only return when a int is given
	 * @return
	 */
	public static int idInput() {
		Scanner scanner = new Scanner(System.in);
		String temp = null;
		int idVal = 0;
		while (temp == null) {
			temp = scanner.nextLine();
			try {
				idVal = Integer.parseInt(temp);
			}
			catch (Exception e) {
				Runner.LOGGER.info("Please enter a valid ID value:");
				temp = null;
			}
		}
		return idVal;
	}
	
	/**
	 * Used to get double values e.g item value
	 * will only return when a valid input is given (must be > 0 and less than the max value the field can hold)
	 * maxInputValue holds the max value the field can hold
	 * @return
	 */
	public static double valueInput(double maxInputValue) {
		Scanner scanner = new Scanner(System.in);
		String temp = null;
		double value = 0;
		while (temp == null) {
			temp = scanner.nextLine();
			try {
				value = Double.parseDouble(temp);
				if ((value > maxInputValue) || (value <= 0)){
					Runner.LOGGER.info("Please enter a valid value (range: £0.01 - £" + maxInputValue + "):");
					temp = null;
				}
			}
			catch (Exception e) {
				Runner.LOGGER.info("Please enter a valid value (range: £0.01 - £" + maxInputValue + "):");
				temp = null;
			}
		}
		return value;
	}
	
	/**
	 * Used to get int quantity values
	 * will only return when a int is given
	 * @return
	 */
	public static int quantityInput() {
		Scanner scanner = new Scanner(System.in);
		String temp = null;
		int quantity = 0;
		while (temp == null) {
			temp = scanner.nextLine();
			try {
				quantity = Integer.parseInt(temp);
				if (quantity == 0) {
					Runner.LOGGER.info("Please enter a valid quantity value:");
					temp = null;
				}
			}
			catch (Exception e) {
				Runner.LOGGER.info("Please enter a valid quantity value:");
				temp = null;
			}
		}
		return quantity;
	}
}
