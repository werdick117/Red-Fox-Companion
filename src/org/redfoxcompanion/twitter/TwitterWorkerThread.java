package org.redfoxcompanion.twitter;

import org.redfoxcompanion.LandingScreen;

import org.redfoxcompanion.utils.Helper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.os.AsyncTask;
import android.os.Bundle;

public class TwitterWorkerThread extends
AsyncTask<LandingScreen, Integer, Bundle>{

	private LandingScreen callingScreen;
	
	@Override
	protected Bundle doInBackground(LandingScreen... params) {
		callingScreen = params[0];
		callingScreen.setTwitterText("Loading . . .");
		
		GsonBuilder builder = new GsonBuilder();
		builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		builder.setDateFormat("EEE, d MMM yyyy kk:mm:ss Z");
		Gson jsonParser = builder.create();
		
		String baseURL = "http://search.twitter.com/search.json?q=from%3AMarist";
		try
		{
		String json = Helper.retrieveText(baseURL + "&rpp=1");
		printTweets(json, jsonParser);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private String printTweets(String json, Gson parser) {	
		Response response = parser.fromJson(json, Response.class);
		String results = "";
		
		for(Tweet tweet : response.getTweets())
			results += tweet;
		
		callingScreen.setTwitterText(results);
		
		return response.getNextPage();
	}

}
