package com.qa.im.utils;

import java.util.Scanner;

import com.qa.im.Runner;

// add exceptions

public class Utils {
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
	
	public static double valueInput(double maxInputValue) {
		Scanner scanner = new Scanner(System.in);
		String temp = null;
		double value = 0;
		while (temp == null) {
			temp = scanner.nextLine();
			try {
				value = Double.parseDouble(temp);
				if (value > maxInputValue) {
					Runner.LOGGER.info("Please enter a valid value (range: £0.00 - £" + maxInputValue + "):");
					temp = null;
				}
			}
			catch (Exception e) {
				Runner.LOGGER.info("Please enter a valid value (range: £0.00 - £" + maxInputValue + "):");
				temp = null;
			}
		}
		return value;
	}
	
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
