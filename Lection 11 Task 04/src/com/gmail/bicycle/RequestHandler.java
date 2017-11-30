package com.gmail.bicycle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

import com.gmail.bicycle.stud.model.Group;

public class RequestHandler implements Runnable {
	private Socket socket;
	private String answer;
	private int connectionNumber;
	private Map<Integer, String> methods;

	RequestHandler(Socket socket, String answer, int connectionNumber, Map<Integer, String> methods) {
		this.socket = socket;
		this.answer = answer;
		this.connectionNumber = connectionNumber;
		this.methods = methods;
	}

	public void start() {
		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		try {
			System.out.println("Received a connection #" + this.connectionNumber);

			try (InputStream inputStream = this.socket.getInputStream();
					OutputStream outputStream = this.socket.getOutputStream();
					PrintWriter printWriter = new PrintWriter(outputStream)) {
				byte[] data = new byte[inputStream.available()];
				inputStream.read(data);
				
				this.answer = extractParameter(data);

				printWriter.println("HTTP/1.1 200 OK");
				printWriter.println("Content-Type: text/html");
				printWriter.println("Content-Length: " + this.answer.length());
				printWriter.println();
				printWriter.print(this.answer);
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
	
	private String extractParameter(byte[] data) {
		String result = this.answer;
		String response = convertResponse(data);
		if (response.length() > 1) {
			String arg = response.substring(response.indexOf("/") + 1, response.indexOf(" HTTP"));
			if (!arg.equals("")) {
				result = generateTable(arg);
			}
		}
		return result;
	}
	
	private String convertResponse(byte[] data) {
		StringBuilder sbResponse = new StringBuilder();
		for (int i : data) {
			sbResponse.append((char) i);
		}
		return sbResponse.toString().trim();
	}

	private String generateTable(String arg) {
		String methodResult = "";
		String methodName = null;
		Class<?> clazz = Group.class;
		try {
			Object group = clazz.newInstance();
			methodName = this.methods.get(Integer.parseInt(arg));

			Method method = clazz.getDeclaredMethod(methodName);
			System.out.println("Invoking method " + method.getName());
			Object obj = method.invoke(group, null);
			methodResult = obj.toString();
		} catch (Exception e) {
			methodResult = "can't invoke this method";
			e.printStackTrace();
		}
		
		String result = "<!DOCTYPE html>"
				+ System.lineSeparator()
				+ "<html class=\"no-js\" lang=\"en\">"
				+ System.lineSeparator()
				+ "<head>"
				+ System.lineSeparator()
				+ "<title>System Info</title>"
				+ System.lineSeparator()
				+ "<meta charset='utf-8'>"
				+ System.lineSeparator()
				+ "</head>"
				+ System.lineSeparator()
				+ "<body>"
				+ System.lineSeparator()
				+ "here is result of method " + methodName
				+ "<pre>"
				+ methodResult
				+"</pre>"
		        + "</body>"
				+ System.lineSeparator()
				+ "</html>";

		return result;

	}
}