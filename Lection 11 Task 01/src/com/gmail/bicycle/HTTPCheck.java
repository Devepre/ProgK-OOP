package com.gmail.bicycle;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTTPCheck {
	public String checkURL(String urlAddress) {
		String result = null;
		urlAddress = handleURL(urlAddress);
		try {
			URL url = new URL(urlAddress);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			int resp = connection.getResponseCode();

			if (resp >= 200 && resp <= 300) {
				result = "Content is easily available";
			} else if (resp == 301) {
				result = "Redirection to another content";
				String newLocation = connection.getHeaderField("Location");
				HTTPCheck check = new HTTPCheck();
				result += System.lineSeparator() + "Redirection availability check:" + System.lineSeparator()
						+ newLocation + " -> " + check.checkURL(newLocation);
			} else {
				result = "Content is unavailable";
			}

		} catch (MalformedURLException e) {
			result = String.format("Malformed URL: %s", urlAddress);
		} catch (IOException e) {
			result = String.format("Can't create connection");
		}

		return result;
	}
	
	public List<String> checkURLs(List<String> listOfResources){
		List<String> result = new ArrayList<>();
		for (String urlAddress : listOfResources) {
			result.add(checkURL(urlAddress));
		}
		return result;
	}

	private String handleURL(String urlAddress) {
		return urlAddress.toLowerCase().startsWith("http") ? urlAddress : "http://" + urlAddress;
	}

}
