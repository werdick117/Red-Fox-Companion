package org.redfoxcompanion;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MenuDisplay extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menudisplay);
		
		String breakfast = getIntent().getStringExtra("breakfast");
		String lunch = getIntent().getStringExtra("lunch");
		String dinner = getIntent().getStringExtra("dinner");
		
		String menu = "BREAKFAST\n" + breakfast + "\n\nLUNCH\n" + lunch + "\n\nDINNER\n" + dinner;
		
		TextView t = (TextView) findViewById(R.id.menu_display);
		t.setMovementMethod(new ScrollingMovementMethod());
		t.setText(menu);
	}
}
