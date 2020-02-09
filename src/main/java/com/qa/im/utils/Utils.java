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
}
