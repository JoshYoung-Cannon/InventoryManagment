package com.qa.inventorymanagement;

import java.util.Scanner;

// add exceptions

public class Utils {
	public static String strInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	public static int intInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}
}
