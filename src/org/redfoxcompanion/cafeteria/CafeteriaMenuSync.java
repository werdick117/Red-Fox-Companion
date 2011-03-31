package org.redfoxcompanion.cafeteria;

import android.os.AsyncTask;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import android.os.Bundle;
import android.util.Log;

public class CafeteriaMenuSync extends AsyncTask<CafeteriaMenuDaySelector,Integer,Bundle>
{
	private static final String TAG = "Red Fox Companion";
	private static URL url;
	private static URLConnection connection;
	private static TagNode node;
	private CafeteriaMenuDaySelector callingScreen;
	
	@Override
	protected Bundle doInBackground(CafeteriaMenuDaySelector... params) 
	{
		callingScreen = params[0];
		Bundle data = null;
		
		try
		{
		setupScraper();
		
		Log.d(TAG, callingScreen.getDayPressed());
		
		return data = scrape(callingScreen.getDayPressed());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Log.wtf(TAG, "This should not be");
		return data;
	}
	
	@Override
	protected void onPostExecute(Bundle data)
	{
		callingScreen.createCafeteriaMenu(data);
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
	
	private Bundle scrape(String day)throws XPatherException
	{
		 String breakfastXpath = "//td[@id='" + day + "']//table//tbody//tr[@class='brk']//td[@class='menuitem']//div[@class='menuitem']//span";
		 String lunchXpath = "//td[@id='" + day + "']//table//tbody//tr[@class='lun']//td[@class='menuitem']//div[@class='menuitem']//span";
		  String dinnerXpath = "//td[@id='" + day + "']//table//tbody//tr[@class='din']//td[@class='menuitem']//div[@class='menuitem']//span";
		 
		 String breakfast;
		 String lunch;
		 String dinner;
		 
		 String value = "";
		 
		 Log.d(TAG, "start breakfast scrape");
		 Object[] menuNodes = node.evaluateXPath(breakfastXpath);
		 if(menuNodes.length >0)
			{
				for(Object o: menuNodes)
				{
				TagNode me = (TagNode)o;
				value += me.getChildren().iterator().next().toString().trim() + "\n";
				
				}
			}
		 	Log.d(TAG, "end breakfast scrape");

			breakfast = value;
			value = "";
			
			Log.d(TAG, "start lunch scrape");
			menuNodes = node.evaluateXPath(lunchXpath);
			if(menuNodes.length >0)
			{
				for(Object o: menuNodes)
				{
				TagNode me = (TagNode)o;
				value += me.getChildren().iterator().next().toString().trim() + "\n";
				
				}
			}
			Log.d(TAG, "end lunch scrape");
			lunch = value;
			value="";
			
			Log.d(TAG, "start dinner scrape");
			menuNodes = node.evaluateXPath(dinnerXpath);
			if(menuNodes.length >0)
			{
				for(Object o: menuNodes)
				{
				TagNode me = (TagNode)o;
				value += me.getChildren().iterator().next().toString().trim() + "\n";
				
				}
			}
			Log.d(TAG, "end dinner scrape");
			
			dinner = value;
		 
		    Log.d(TAG, "finished scrape");		
			Bundle data = new Bundle();
			
			data.putString("daySelected", callingScreen.getDayPressed());
			data.putString("breakfast", breakfast);
			data.putString("lunch", lunch);
			data.putString("dinner", dinner);
			
			return data;
	}
}
