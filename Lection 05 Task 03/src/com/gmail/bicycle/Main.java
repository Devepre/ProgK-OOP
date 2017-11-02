package com.gmail.bicycle;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.gmail.bicycle.model.Group;

public class Main {
	private static Scanner sc;

	public static void main(String[] args) {
		System.out.println("Creating some default group...");
		Group group = UserService.createDefaultGroup();
		System.out.println("Group created:");
		System.out.println(group);

		int choice = 0;
		while (choice != -1) {
			try {
				choice = getUserInput();
				switch (choice) {
				case 1:
					UserService.demoDAOPlainText(group);
					break;
				case 2:
					UserService.demoDAOJSONText(group);
					break;
				case 3:
					UserService.demoJSON(group);
					break;
				default:
					;
				}
			} catch (IllegalArgumentException | InputMismatchException e) {
				System.out.println("Wrong input. Try again.");
			}
		}		
		sc.close();

		System.out.println("<<<End of program>>>");
	}

	private static int getUserInput() throws IllegalArgumentException, InputMismatchException {
		int choice = -1;
		System.out.println("Please provide your choice");
		System.out.println("1. Demo for DAO plain");
		System.out.println("2. Demo for DAO JSON");
		System.out.println("3. Demo for JSON");
		System.out.println("-1. Exit");
		sc = new Scanner(System.in);
		choice = sc.nextInt();		
		if (choice < -1 || choice > 4) {
			throw new IllegalArgumentException("Wrong input choice");
		}

		return choice;
	}

}
