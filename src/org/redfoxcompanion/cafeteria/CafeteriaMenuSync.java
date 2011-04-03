package org.redfoxcompanion.cafeteria;

import android.os.AsyncTask;

import java.io.IOException;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.redfoxcompanion.utils.Helper;

import android.os.Bundle;
import android.util.Log;

/*
 * @author Steve Werdick
 * This is the worker thread that scrapes 
 * the marist dining website for the purpose
 * of retreiving menu items
 */
public class CafeteriaMenuSync extends
		AsyncTask<CafeteriaMenuDaySelector, Integer, Bundle> {
	
	private static final String TAG = "Red Fox Companion";
	private CafeteriaMenuDaySelector callingScreen;

	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Bundle doInBackground(CafeteriaMenuDaySelector... params) {
		callingScreen = params[0];
		Bundle data = null;

		try {
			Log.d(TAG, callingScreen.getDayPressed());

			return data = scrape(callingScreen.getDayPressed());

		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.wtf(TAG, "This should not be");
		return data;
	}

	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Bundle data) {
		callingScreen.createCafeteriaMenu(data);
	}

	/*
	 * @param day The button pressed and consequently the
	 * day to scrape for menu information
	 */
	private Bundle scrape(String day) throws XPatherException, IOException {
		String breakfastXpath = "//td[@id='"
				+ day
				+ "']//table//tbody//tr[@class='brk']//td[@class='menuitem']//div[@class='menuitem']//span";
		String lunchXpath = "//td[@id='"
				+ day
				+ "']//table//tbody//tr[@class='lun']//td[@class='menuitem']//div[@class='menuitem']//span";
		String dinnerXpath = "//td[@id='"
				+ day
				+ "']//table//tbody//tr[@class='din']//td[@class='menuitem']//div[@class='menuitem']//span";

		TagNode root = Helper
				.retrieveHTML("http://maristdining.com/WeeklyMenu.htm");

		String breakfast = Helper.getScrapeText(root, breakfastXpath);
		String lunch = Helper.getScrapeText(root, lunchXpath);
		String dinner = Helper.getScrapeText(root, dinnerXpath);

		Log.d(TAG, "finished scrape");
		Bundle data = new Bundle();

		data.putString("daySelected", callingScreen.getDayPressed());
		data.putString("breakfast", breakfast);
		data.putString("lunch", lunch);
		data.putString("dinner", dinner);

		return data;
	}
}
