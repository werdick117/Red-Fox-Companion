package org.redfoxcompanion;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CafeteriaMenuDaySelector extends Activity implements OnClickListener
{
	private static final String TAG = "Red Fox Companion";
	private HashMap<String,String> menuItems;
	private Bundle data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weekdays);
		data = getIntent().getExtras();
		
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
		
	}

	@Override
	public void onClick(View v) 
	{
		Log.d(TAG, "View clicked");
		Intent intent = new Intent(this, MenuDisplay.class);
		switch(v.getId())
		{
		case R.id.sunday_button:
			Log.d(TAG, "Sunday Pressed");
			intent.putExtra("breakfast", data.getString("sundayBreakfast"));
			intent.putExtra("lunch", data.getString("sundayLunch"));
			intent.putExtra("dinner", data.getString("sundayDinner"));
			
			startActivity(intent);
			break;
			
		case R.id.monday_button:
			Log.d(TAG, "Sunday Pressed");
			intent.putExtra("breakfast", data.getString("mondayBreakfast"));
			intent.putExtra("lunch", data.getString("mondayLunch"));
			intent.putExtra("dinner", data.getString("mondayDinner"));
			
			startActivity(intent);
			break;
			
		case R.id.tuesday_button:
			Log.d(TAG, "Sunday Pressed");
			intent.putExtra("breakfast", data.getString("tuesdayBreakfast"));
			intent.putExtra("lunch", data.getString("tuesdayLunch"));
			intent.putExtra("dinner", data.getString("tuesdayDinner"));
			
			startActivity(intent);
			break;
			
		case R.id.wednesday_button:
			Log.d(TAG, "Sunday Pressed");
			intent.putExtra("breakfast", data.getString("wednesdayBreakfast"));
			intent.putExtra("lunch", data.getString("wednesdayLunch"));
			intent.putExtra("dinner", data.getString("wednesdayDinner"));
			
			startActivity(intent);
			break;
			
		case R.id.thursday_button:
			Log.d(TAG, "Sunday Pressed");
			intent.putExtra("breakfast", data.getString("thusdayBreakfast"));
			intent.putExtra("lunch", data.getString("thursdayLunch"));
			intent.putExtra("dinner", data.getString("thursdayDinner"));
			
			startActivity(intent);
			break;
			
		case R.id.friday_button:
			Log.d(TAG, "Sunday Pressed");
			intent.putExtra("breakfast", data.getString("fridayBreakfast"));
			intent.putExtra("lunch", data.getString("fridayLunch"));
			intent.putExtra("dinner", data.getString("fridayDinner"));
			
			startActivity(intent);
			break;
			
		case R.id.saturday_button:
			Log.d(TAG, "Sunday Pressed");
			intent.putExtra("breakfast", data.getString("saturdayBreakfast"));
			intent.putExtra("lunch", data.getString("saturdayLunch"));
			intent.putExtra("dinner", data.getString("saturdayDinner"));
			
			startActivity(intent);
			break;
		}
	}
}
