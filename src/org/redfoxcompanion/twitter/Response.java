package org.redfoxcompanion.twitter;

import java.util.List;

public class Response {
	private List<Tweet> results;
	private String nextPage;
	
	public List<Tweet> getTweets() { return this.results; };
	public String getNextPage() { return this.nextPage; };
}