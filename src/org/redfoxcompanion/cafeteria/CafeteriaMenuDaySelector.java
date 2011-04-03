package org.redfoxcompanion.cafeteria;

import org.redfoxcompanion.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/*
 * @author Steve Werdick
 * This is the screen from which the user
 * selects which day to display menu information for
 */
public class CafeteriaMenuDaySelector extends Activity implements
		OnClickListener {
	private static final String TAG = "Red Fox Companion";
	private ProgressDialog dialog;
	private String dayPressed = null;

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weekdays);

		View sundayButton = findViewById(R.id.sunday_button);
		sundayButton.setOnClickListener(this);

		View mondayButton = findViewById(R.id.monday_button);
		mondayButton.setOnClickListener(this);

		View tuesdayButton = findViewById(R.id.tuesday_button);
		tuesdayButton.setOnClickListener(this);

		View wednesdayButton = findViewById(R.id.wednesday_button);
		wednesdayButton.setOnClickListener(this);

		View thursdayButton = findViewById(R.id.thursday_button);
		thursdayButton.setOnClickListener(this);

		View fridayButton = findViewById(R.id.friday_button);
		fridayButton.setOnClickListener(this);

		View saturdayButton = findViewById(R.id.saturday_button);
		saturdayButton.setOnClickListener(this);

		Log.d(TAG, "CafeteriaMenuDaySelector started");
	}

	/*
	 * @return Which day button was pressed
	 */
	public String getDayPressed() {
		return dayPressed;
	}

	/*
	 * @param data Menu information to be displayed
	 * 
	 * Destroys the progress dialog and displays the 
	 * menu information scraped
	 */
	public void createCafeteriaMenu(Bundle data) {
		dialog.dismiss();
		Intent menuActivityIntent = new Intent(this, MenuDisplay.class);
		menuActivityIntent.putExtras(data);

		startActivity(menuActivityIntent);
	}

	/*
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		CafeteriaMenuSync menuSync;

		switch (v.getId()) {
		case R.id.sunday_button:
			dayPressed = "sunday";
			dialog = ProgressDialog.show(CafeteriaMenuDaySelector.this,
					"Retrieving Menu Information", "Please wait...", true);
			menuSync = new CafeteriaMenuSync();
			menuSync.execute(this);

			break;

		case R.id.monday_button:

			dayPressed = "monday";
			dialog = ProgressDialog.show(CafeteriaMenuDaySelector.this,
					"Retrieving Menu Information", "Please wait...", true);
			menuSync = new CafeteriaMenuSync();
			menuSync.execute(this);
			break;

		case R.id.tuesday_button:
			dayPressed = "tuesday";
			dialog = ProgressDialog.show(CafeteriaMenuDaySelector.this,
					"Retrieving Menu Information", "Please wait...", true);
			menuSync = new CafeteriaMenuSync();
			menuSync.execute(this);
			break;

		case R.id.wednesday_button:
			dayPressed = "wednesday";
			dialog = ProgressDialog.show(CafeteriaMenuDaySelector.this,
					"Retrieving Menu Information", "Please wait...", true);
			menuSync = new CafeteriaMenuSync();
			menuSync.execute(this);
			break;

		case R.id.thursday_button:
			dayPressed = "thursday";
			dialog = ProgressDialog.show(CafeteriaMenuDaySelector.this,
					"Retrieving Menu Information", "Please wait...", true);
			menuSync = new CafeteriaMenuSync();
			menuSync.execute(this);
			break;

		case R.id.friday_button:
			dayPressed = "friday";
			dialog = ProgressDialog.show(CafeteriaMenuDaySelector.this,
					"Retrieving Menu Information", "Please wait...", true);
			menuSync = new CafeteriaMenuSync();
			menuSync.execute(this);
			break;

		case R.id.saturday_button:
			dayPressed = "saturday";
			dialog = ProgressDialog.show(CafeteriaMenuDaySelector.this,
					"Retrieving Menu Information", "Please wait...", true);
			menuSync = new CafeteriaMenuSync();
			menuSync.execute(this);
			break;
		}
	}
}
