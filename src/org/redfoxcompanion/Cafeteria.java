package org.redfoxcompanion;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class Cafeteria extends Activity implements OnClickListener
{
	private static final String TAG = "Red Fox Companion";

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cafeteria);
		
		TextView t = (TextView)findViewById(R.id.monday_breakfast);
		t.setMovementMethod(new ScrollingMovementMethod());
		
		Bundle data = getIntent().getExtras();
		t.setText(data.getString("mondayBreakfast"));
		
		Log.d(TAG, "Cafeteria activity successfully launched");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
