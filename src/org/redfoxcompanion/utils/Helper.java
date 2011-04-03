package org.redfoxcompanion.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class Helper {

	private static InputStream retrieve(String url) throws IOException {
		URL target = new URL(url);
		URLConnection connection = target.openConnection();
		return connection.getInputStream();
	}

	/**
	 * Retrieves the page at the given URL as a string
	 * 
	 * @param url
	 *            the URL to retrieve from
	 * @return the page as a string
	 * @throws IOException
	 *             thrown if URL is malformed or a retrieval error occurs
	 */
	public static String retrieveText(String url) throws IOException {
		BufferedReader stream = new BufferedReader(new InputStreamReader(
				retrieve(url)));
		String response = "";
		String line;

		while ((line = stream.readLine()) != null)
			response += line;

		return response;
	}

	/**
	 * Retrieves the page at a given URL as a TagNode representing the root node
	 * 
	 * @param url
	 *            the URL to retrieve from
	 * @return the page's root node
	 * @throws IOException
	 *             thrown if URL is malformed or a retrieval error occurs
	 */
	public static TagNode retrieveHTML(String url) throws IOException {
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties properties = cleaner.getProperties();

		properties.setAllowHtmlInsideAttributes(true);
		properties.setAllowMultiWordAttributes(true);
		properties.setRecognizeUnicodeChars(true);
		properties.setOmitComments(true);
		return cleaner.clean(new InputStreamReader(retrieve(url)));
	}

	public static String getScrapeText(TagNode root, String query)
			throws XPatherException {
		String resultText = "";
		for (Object node : root.evaluateXPath(query))
			for (Object child : ((TagNode) node).getChildren())
				resultText += child.toString().trim() + "\n";
		return resultText;
	}
}