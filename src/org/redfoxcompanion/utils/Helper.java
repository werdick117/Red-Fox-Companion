package org.redfoxcompanion.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Helper {

	private static InputStream retrieve(String url) throws IOException {
		URL target = new URL(url);
		URLConnection connection = target.openConnection();
		return connection.getInputStream();
	}
	
	public static String retrieveText(String url) throws IOException {
		BufferedReader stream = new BufferedReader(new InputStreamReader(retrieve(url)));
		String response = "";
		String line;
		
		while((line = stream.readLine()) != null)
			response += line;
			
		return response;
	}
	
	public static Document retrieveHTML(String url) throws IOException {
		return Jsoup.connect(url).get();
	}
	
	public static String getScrapeText(Document doc, String query) {
		return getScrapeText(doc.getAllElements(), query);
	}
	public static String getScrapeText(Element element, String query) {
		return getScrapeText(element.getAllElements(), query);
	}
	public static String getScrapeText(Elements elements, String query) {
		String resultString = "";
		for(Element element : elements.select(query))
			if(element.hasText())
				resultString += element.text().trim() + "\n";
		return resultString.trim();
	}
}