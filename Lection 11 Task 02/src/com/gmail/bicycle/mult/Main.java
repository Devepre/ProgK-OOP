package com.gmail.bicycle.mult;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: <port> <minutes>");
			System.exit(0);
		}
		int port = Integer.parseInt(args[0]);
		int minutes = Integer.parseInt(args[1]);

		System.out.println("Starting server on port: " + port);
		System.out.printf("Minimum time to Server work: %s minutes" + System.lineSeparator(), minutes);

		InfoSocketServer server = new InfoSocketServer(port);
		server.startServer();

		try {
			TimeUnit.MINUTES.sleep(minutes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		server.stopServer();
	
	}

}
