package com.gmail.bicycle;

import java.util.concurrent.TimeUnit;

public class Main {
	public static Class<?> classToInvoke = com.gmail.bicycle.stud.model.Group.class;
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: <port> <minutes>");
			System.exit(0);
		}
		
		GroupSocketServer server = new GroupSocketServer(Integer.parseInt(args[0]));
		server.startServer();

		try {
			TimeUnit.MINUTES.sleep(Integer.parseInt(args[1]));
		} catch (Exception e) {
			e.printStackTrace();
		}

		server.stopServer();
	}

}
