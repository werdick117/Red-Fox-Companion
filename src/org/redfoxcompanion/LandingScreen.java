package org.redfoxcompanion;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import org.redfoxcompanion.cafeteria.CafeteriaMenuDaySelector;


/**
 * 
 * @author Steve Werdick
 * This is the screen the user can first interact with
 */
public class LandingScreen extends Activity implements OnClickListener
{
	private static final String TAG = "Red Fox Companion";
	
	
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Add action listeners for buttons
        View cafeteriaButton = findViewById(R.id.cafeteria_button);
        cafeteriaButton.setOnClickListener(this);
        
        View academicButton = findViewById(R.id.academic_button);
        academicButton.setOnClickListener(this);
        
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
    }
    
    /*
     * (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
	@Override
	public void onClick(View v) 
	{
		Intent intent = null;
		switch(v.getId())
		{
		case R.id.cafeteria_button:
			Log.d(TAG, "Attempting to start CafeteriaMenuDaySelector activity");
		    intent = new Intent(this, CafeteriaMenuDaySelector.class);
			startActivity(intent);
			break;
		case R.id.about_button:
			Log.d(TAG, "Attempting to start AboutScreen activity");
			intent = new Intent(this, AboutScreen.class);
			startActivity(intent);
			break;
		}
	}

}