package org.redfoxcompanion.cafeteria;

import org.redfoxcompanion.R;

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
		String daySelected = getIntent().getStringExtra("daySelected");
		
		String menu = "BREAKFAST\n" + breakfast + "\n\nLUNCH\n" + lunch + "\n\nDINNER\n" + dinner;
		
		TextView dayText = (TextView) findViewById(R.id.day_selected);
		dayText.setText(daySelected);
		
		TextView menuText = (TextView) findViewById(R.id.menu_display);
		menuText.setMovementMethod(new ScrollingMovementMethod());
		menuText.setMaxLines(Integer.MAX_VALUE);
		menuText.setBackgroundResource(R.color.translucent_black);
		menuText.setText(menu);
	}
}
