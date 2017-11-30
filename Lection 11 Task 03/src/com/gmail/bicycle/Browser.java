package com.gmail.bicycle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

public class Browser {
	public List<String> getListOfURLs(String urlString) throws IOException, SAXException, ParserConfigurationException {
		List<String> result = new ArrayList<>();

		String responseString = this.getResponse(urlString);

		Document document = Jsoup.parse(responseString);
		Elements links = document.select("a");
		for (Element link : links) {
			String value =link.attr("href");
			if (value != "") {
				result.add(value);
			}
		}
		return result;
	}

	public String getResponse(String urlString) throws IOException {
		StringBuilder sb = new StringBuilder();
		URL userURL = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) userURL.openConnection();

		long connLength = connection.getContentLengthLong();
		if (connLength != 0) {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					sb.append(inputLine);
					sb.append(System.lineSeparator());
				}
			}
		}

		return sb.toString().trim();
	}

}
