package com.gmail.bicycle.mult;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class InfoSocketServer extends Thread {
	static int connections = 0;
	
	private ServerSocket serverSocket;
	private int port;

	public InfoSocketServer(int port) {
		super();
		this.port = port;
	}

	public void startServer() {
		try {
			serverSocket = new ServerSocket(port);
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopServer() {
		this.interrupt();
	}

	@Override
	public void run() {
		while (!this.isInterrupted()) {
			try {
				System.out.println("Listening for a connection");
				
				String answer = "<!doctype html><html class=\"no-js\" lang=\"en\"><head><title>System Info</title>" + "<meta charset='utf-8'></head>" + "<body>";
				connections++;
				answer += "<p>Connection # " + connections + "</p><p>General System Info:</p>";
				answer += "<pre>" + new SystemInfo() + "</pre>";
				answer += "<br>";
				answer += "</body></html>";

				Socket socket = serverSocket.accept();
				RequestHandler requestHandler = new RequestHandler(socket, answer, connections);
				requestHandler.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

}