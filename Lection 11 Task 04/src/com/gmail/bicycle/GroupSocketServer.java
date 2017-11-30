package com.gmail.bicycle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GroupSocketServer extends Thread {
	static int connections = 0;

	private ServerSocket serverSocket;
	private int port;
	private Map<Integer, String> methods;

	public GroupSocketServer(int port) {
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

				this.connections++;
				String answer = generateHTML();

				Socket socket = serverSocket.accept();
				RequestHandler requestHandler = new RequestHandler(socket, answer, this.connections, this.methods);
				requestHandler.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String generateHTML() {
		 String result = "<!DOCTYPE html>"
				 + System.lineSeparator()
					+ "<html class=\"no-js\" lang=\"en\">"
					+ System.lineSeparator()
					+ "<head>"
					+ System.lineSeparator()
					+ "<title>Group Info</title>"
					+ System.lineSeparator()
					+ "<meta charset='utf-8'>"
					+ System.lineSeparator()
					+ "</head>"
					+ System.lineSeparator()
					+ "<style>"
					+ System.lineSeparator()
					+ "table, th, td {"
					+ System.lineSeparator() 
					+ "border: 1px solid black;"
					+ System.lineSeparator()
					+ "}"
					+ System.lineSeparator()
					+ "</style>"
					+ System.lineSeparator()
					+ "<body>"
					+ System.lineSeparator()
					+ "<p>Connection # " + connections + "</p>"
					+ System.lineSeparator()
					+ "<p>List of Group methods:</p>"
					+ System.lineSeparator()
					+ generateTable(Main.classToInvoke)
					+"<br>"
					+ System.lineSeparator()
					+ "</body>"
					+ System.lineSeparator()
					+ "</html>";
			
			return result;
	}

	private String generateTable(Class<?> clazz) {
		this.methods = ReflectionHandler.processFields(clazz);
		StringBuilder sb = new StringBuilder("<table>");
		sb.append(System.lineSeparator());
		Set<Entry<Integer, String>> rows = methods.entrySet();
		for (Entry<Integer, String> row : rows) {
			sb.append(generateRow(row));
		}
		sb.append("</table>");

		return sb.toString().trim();
	}

	private String generateRow(Entry<Integer, String> row) {
		StringBuilder sb = new StringBuilder("<tr>");
		{
			sb.append("<td>");
			String url = "<a href=\"" + row.getKey() + "\"" + ">" + row.getValue() + "</a>";
			sb.append(url);
			sb.append("</td>");
		}
		sb.append("</tr>");
		sb.append(System.lineSeparator());

		return sb.toString().trim();
	}

}
