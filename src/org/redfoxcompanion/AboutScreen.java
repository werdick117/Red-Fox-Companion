package org.redfoxcompanion;

import android.app.Activity;
import android.os.Bundle;

/*
 * @author Steve Werdick
 * This screen displays some text information
 * about the app and how to reach the main developer(s)
 */
public class AboutScreen extends Activity
{
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
	}
}
