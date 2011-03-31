package org.redfoxcompanion.threads;

import android.os.AsyncTask;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.redfoxcompanion.LandingScreen;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import android.os.Bundle;
import android.util.Log;

public class CafeteriaMenuSync extends AsyncTask<LandingScreen,Integer,Bundle>
{
	private static final String TAG = "Red Fox Companion";
	private static URL url;
	private static URLConnection connection;
	private static TagNode node;
	private LandingScreen callingLandingScreen;
	private HashMap<String,String> data = new HashMap<String,String>();
	
	private static String SUNDAY_BREAKFAST = "//td[@id='sunday']//table//tbody//tr[@class='brk']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String SUNDAY_LUNCH = "//td[@id='sunday']//table//tbody//tr[@class='lun']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String SUNDAY_DINNER = "//td[@id='sunday']//table//tbody//tr[@class='din']//td[@class='menuitem']//div[@class='menuitem']//span";
	
	private static String MONDAY_BREAKFAST = "//td[@id='monday']//table//tbody//tr[@class='brk']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String MONDAY_LUNCH = "//td[@id='monday']//table//tbody//tr[@class='lun']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String MONDAY_DINNER = "//td[@id='monday']//table//tbody//tr[@class='din']//td[@class='menuitem']//div[@class='menuitem']//span";
	
	private static String TUESDAY_BREAKFAST = "//td[@id='tuesday']//table//tbody//tr[@class='brk']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String TUESDAY_LUNCH = "//td[@id='tuesday']//table//tbody//tr[@class='lun']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String TUESDAY_DINNER = "//td[@id='tuesday']//table//tbody//tr[@class='din']//td[@class='menuitem']//div[@class='menuitem']//span";
	
	private static String WEDNESDAY_BREAKFAST = "//td[@id='wednesdsay']//table//tbody//tr[@class='brk']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String WEDNESDAY_LUNCH = "//td[@id='wednesday']//table//tbody//tr[@class='lun']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String WEDNESDAY_DINNER = "//td[@id='wednesday']//table//tbody//tr[@class='din']//td[@class='menuitem']//div[@class='menuitem']//span";
	
	private static String THURSDAY_BREAKFAST = "//td[@id='thursday']//table//tbody//tr[@class='brk']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String THURSDAY_LUNCH = "//td[@id='thursday']//table//tbody//tr[@class='lun']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String THURSDAY_DINNER = "//td[@id='thursday']//table//tbody//tr[@class='din']//td[@class='menuitem']//div[@class='menuitem']//span";
	
	private static String FRIDAY_BREAKFAST = "//td[@id='friday']//table//tbody//tr[@class='brk']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String FRIDAY_LUNCH = "//td[@id='friday']//table//tbody//tr[@class='lun']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String FRIDAY_DINNER = "//td[@id='friday']//table//tbody//tr[@class='din']//td[@class='menuitem']//div[@class='menuitem']//span";
	
	private static String SATURDAY_BREAKFAST = "//td[@id='saturday']//table//tbody//tr[@class='brk']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String SATURDAY_LUNCH = "//td[@id='saturday']//table//tbody//tr[@class='lun']//td[@class='menuitem']//div[@class='menuitem']//span";
	private static String SATURDAY_DINNER = "//td[@id='saturday']//table//tbody//tr[@class='din']//td[@class='menuitem']//div[@class='menuitem']//span";
	
	private String sundayBreakfast;
	private String sundayLunch;
	private String sundayDinner;

	private String mondayBreakfast;
	private String mondayLunch;
	private String mondayDinner;
	
	private String tuesdayBreakfast;
	private String tuesdayLunch;
	private String tuesdayDinner;
	
	private String wednesdayBreakfast;
	private String wednesdayLunch;
	private String wednesdayDinner;
	
	private String thursdayBreakfast;
	private String thursdayLunch;
	private String thursdayDinner;
	
	private String fridayBreakfast;
	private String fridayLunch;
	private String fridayDinner;
	
	private String saturdayBreakfast;
	private String saturdayLunch;
	private String saturdayDinner;
	
	@Override
	protected Bundle doInBackground(LandingScreen... params) 
	{
		
		scrape();
		
		callingLandingScreen = params[0];
		
		Bundle data = new Bundle();
		
		data.putString("sundayBreakfast", sundayBreakfast);
		data.putString("sundayLunch", sundayLunch);
		data.putString("sundayDinner", sundayDinner);
		
		data.putString("mondayBreakfast", mondayBreakfast);
		data.putString("mondayLunch", mondayLunch);
		data.putString("mondayDinner", mondayDinner);
		
		data.putString("tuesdayBreakfast", tuesdayBreakfast);
		data.putString("tuesdayLunch", tuesdayLunch);
		data.putString("tuesdayDinner", tuesdayDinner);
		
		data.putString("wednesdayBreakfast", wednesdayBreakfast);
		data.putString("wednesdayLunch", wednesdayLunch);
		data.putString("wednesdayDinner", wednesdayDinner);
		
		data.putString("thursdayBreakfast", thursdayBreakfast);
		data.putString("thursdayLunch", thursdayLunch);
		data.putString("thursdayDinner", thursdayDinner);
		
		data.putString("fridayBreakfast", fridayBreakfast);
		data.putString("fridayLunch", fridayLunch);
		data.putString("fridayDinner", fridayDinner);
		
		data.putString("saturdayBreakfast", saturdayBreakfast);
		data.putString("saturdayLunch", saturdayLunch);
		data.putString("saturdayDinner", saturdayDinner);
		
		return data;
	}
	
	@Override
	protected void onPostExecute(Bundle data)
	{
		callingLandingScreen.destroyProgressDialog();
		callingLandingScreen.createCafeteriaMenu(data);
	}
	
	/**
	 * Scrapes the website for each day, catches exceptions if any
	 */
	private void scrape()
	{
		try
		{
			if(!setupScraper())
				return;
			
			scrapeSunday();
			Log.d(TAG, "Day done");
			scrapeMonday();
			Log.d(TAG, "Day done");
			scrapeTuesday();
			Log.d(TAG, "Day done");
			scrapeWednesday();
			Log.d(TAG, "Day done");
			scrapeThursday();
			Log.d(TAG, "Day done");
			scrapeFriday();
			Log.d(TAG, "Day done");
			scrapeSaturday();
			Log.d(TAG, "Day done");
		}catch(Exception e)
		{
			Log.d(TAG, "Scrape failure");
			e.printStackTrace();
		}
	}
	
	private boolean setupScraper()
	{
		try
		{
			Log.d(TAG, "Starting download");
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties properties = cleaner.getProperties();
		
		properties.setAllowHtmlInsideAttributes(true);
		properties.setAllowMultiWordAttributes(true);
		properties.setRecognizeUnicodeChars(true);
		properties.setOmitComments(true);
		
	    url = new URL("http://maristdining.com/WeeklyMenu.htm");
		connection = url.openConnection();
		
		node = cleaner.clean(new InputStreamReader(connection.getInputStream()));
		Log.d(TAG, "Download complete");
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private void scrapeSunday() throws XPatherException
	{
		Log.d(TAG, "Begin sunday evaluation");
		Object[] menuNodes = node.evaluateXPath(SUNDAY_BREAKFAST);
		Log.d(TAG, "End sunday evaluation");
		String value = "";
		
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		sundayBreakfast = value;
		value = "";
		
		menuNodes = node.evaluateXPath(SUNDAY_LUNCH);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		sundayLunch = value;
		value="";
		
		menuNodes = node.evaluateXPath(SUNDAY_DINNER);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		sundayDinner = value;
	}
	
	private void scrapeMonday() throws XPatherException
	{
		Object[] menuNodes = node.evaluateXPath(MONDAY_BREAKFAST);
		String value = "";
		
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		mondayBreakfast = value;
		value = "";
		
		menuNodes = node.evaluateXPath(MONDAY_LUNCH);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		mondayLunch = value;
		value="";
		
		menuNodes = node.evaluateXPath(MONDAY_DINNER);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		mondayDinner = value;
	}

	private void scrapeTuesday() throws XPatherException
	{
		Object[] menuNodes = node.evaluateXPath(TUESDAY_BREAKFAST);
		String value = "";
		
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		tuesdayBreakfast = value;
		value = "";
		
		menuNodes = node.evaluateXPath(TUESDAY_LUNCH);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		tuesdayLunch = value;
		value="";
		
		menuNodes = node.evaluateXPath(TUESDAY_DINNER);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		tuesdayDinner = value;
	}
	
	private void scrapeWednesday() throws XPatherException
	{
		Object[] menuNodes = node.evaluateXPath(WEDNESDAY_BREAKFAST);
		String value = "";
		
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		wednesdayBreakfast = value;
		value = "";
		
		menuNodes = node.evaluateXPath(WEDNESDAY_LUNCH);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		wednesdayLunch = value;
		value="";
		
		menuNodes = node.evaluateXPath(WEDNESDAY_DINNER);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		wednesdayDinner = value;
	}
	
	private void scrapeThursday() throws XPatherException
	{
		Object[] menuNodes = node.evaluateXPath(THURSDAY_BREAKFAST);
		String value = "";
		
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		thursdayBreakfast = value;
		value = "";
		
		menuNodes = node.evaluateXPath(THURSDAY_LUNCH);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		thursdayLunch = value;
		value="";
		
		menuNodes = node.evaluateXPath(THURSDAY_DINNER);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		thursdayDinner = value;
	}
	
	private void scrapeFriday() throws XPatherException
	{
		Object[] menuNodes = node.evaluateXPath(FRIDAY_BREAKFAST);
		String value = "";
		
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		fridayBreakfast = value;
		value = "";
		
		menuNodes = node.evaluateXPath(FRIDAY_LUNCH);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		fridayLunch = value;
		value="";
		
		menuNodes = node.evaluateXPath(FRIDAY_DINNER);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		fridayDinner = value;
	}
	
	private void scrapeSaturday() throws XPatherException
	{
		Object[] menuNodes = node.evaluateXPath(SATURDAY_BREAKFAST);
		String value = "";
		
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		saturdayBreakfast = value;
		value = "";
		
		menuNodes = node.evaluateXPath(SATURDAY_LUNCH);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		saturdayLunch = value;
		value="";
		
		menuNodes = node.evaluateXPath(SATURDAY_DINNER);
		if(menuNodes.length >0)
		{
			for(Object o: menuNodes)
			{
			TagNode me = (TagNode)o;
			value += me.getChildren().iterator().next().toString().trim() + "\n";
			
			}
		}
		saturdayDinner = value;
	}
}
