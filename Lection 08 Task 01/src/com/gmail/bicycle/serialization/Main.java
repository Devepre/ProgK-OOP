package com.gmail.bicycle.serialization;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Please provide your choice:");
		System.out.println("1. Save groups to the file");
		System.out.println("2. Load groups from the file");
		System.out.println("3. Exit");		
		Scanner sc = new Scanner(System.in);		
		try {
			int choice = sc.nextInt();
			if (choice == 1 || choice == 2 || choice == 3) {
				(new DataWorker()).action(choice, sc);
				main(args);
			} else {
				System.out.println("Wrong input. Try again");
				main(args);
			}
		} catch (NoSuchElementException e) {
			System.out.println("Wrong input. Try again");
			main(args);
		}
	}

}
