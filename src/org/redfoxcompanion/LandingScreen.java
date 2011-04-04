package org.redfoxcompanion;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import org.redfoxcompanion.twitter.ScrollingTextView;
import org.redfoxcompanion.twitter.TwitterWorkerThread;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

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
        
        new TwitterWorkerThread().execute(this);
    }
    
    /*
     * This method sets the twitter display box to a tweet
     * @param The tweet to display
     */
    public void setTwitterText(String text)
    {
    	TextView t = (TextView)findViewById(R.id.twitter_display);
    	t.setText(text);
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