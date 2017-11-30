package com.gmail.bicycle.mult;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

//public class RequestHandler extends Thread {
public class RequestHandler implements Runnable {
	private Socket socket;
	private String answer;
	private int connectionNumber;

	RequestHandler(Socket socket, String answer, int connectionNumber) {
		this.socket = socket;
		this.answer = answer;
		this.connectionNumber = connectionNumber;		
	}
	
	public void start() {
		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		try {
			System.out.println("Received a connection #" + this.connectionNumber);

			try (InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();
					PrintWriter printWriter = new PrintWriter(outputStream)) {
				byte[] data = new byte[inputStream.available()];
				inputStream.read(data);
				String response = "HTTP/1.1 200 OK\r\n" + "Server: Java based\r\n" + "Content-Type:text/html\r\n"
						+ "Content-Length: " + "\r\n" + "Connection: close\r\n\r\n";
				printWriter.print(response + answer);
				printWriter.flush();
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					socket.close();
					System.out.println("Connection #" + this.connectionNumber + " is closed");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}