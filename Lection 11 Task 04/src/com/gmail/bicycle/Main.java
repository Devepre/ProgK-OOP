package com.gmail.bicycle;

import java.util.concurrent.TimeUnit;

public class Main {
	private static final int PORT = 8080;
	private static final int MINUTES = 10;

	public static void main(String[] args) {
		GroupSocketServer server = new GroupSocketServer(PORT);
		server.startServer();

		try {
			TimeUnit.MINUTES.sleep(MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
		}

		server.stopServer();
	}

}
