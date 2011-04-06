package org.redfoxcompanion.twitter;

import java.util.Date;

public class Tweet {
	private String text;
	private Date createdAt;
	
	public String getText() { return this.text; };
	public Date getCreatedAt() { return this.createdAt; };
	
	public String toString() {
		return String.format("%s\n\t%s", this.text, this.createdAt);
	}
}