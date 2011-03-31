package org.redfoxcompanion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;

import org.redfoxcompanion.threads.CafeteriaMenuSync;

/**
 * 
 * @author Steve Werdick
 *
 */
public class LandingScreen extends Activity implements OnClickListener
{
	private static final String TAG = "Red Fox Companion";
	private boolean menuReady = false;
	private ProgressDialog dialog;
	
	
    /** Called when the activity is first created. */
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
    }
    
	@Override
	public void onClick(View v) 
	{
		
		switch(v.getId())
		{
		case R.id.cafeteria_button:
			Log.d(TAG, "Attempting to start Cafeteria activity");
			dialog = ProgressDialog.show(LandingScreen.this, "Retrieving Menu Information", "Please wait...", true);
			CafeteriaMenuSync menuSync = new CafeteriaMenuSync();
			menuSync.execute(this);
			
			break;
		}
	}
	
	public void destroyProgressDialog()
	{
		dialog.dismiss();
	}
	
	public void createCafeteriaMenu(Bundle data)
	{
		Intent cafeteriaActivityIntent = new Intent(this, CafeteriaMenuDaySelector.class);
		cafeteriaActivityIntent.putExtras(data);
		
		startActivity(cafeteriaActivityIntent);
	} 
}