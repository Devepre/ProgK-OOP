package com.gmail.bicycle.watcher;

import java.io.File;
import java.util.Scanner;

public class Main {
	private static final String STOP_SIGNAL = "exit";

	public static void main(String[] args) {
		File folder = new File(".");
		Thread thread = new Thread(new FileWatcher(folder));
		thread.start();
		
		Scanner sc = new Scanner(System.in);
		String input;
		System.out.println("In order to stop program please type: " + STOP_SIGNAL);
		do {
			input = sc.nextLine();
		} while (!input.equals(STOP_SIGNAL));
		thread.interrupt();
		sc.close();

		System.out.println("End main");
	}

}
