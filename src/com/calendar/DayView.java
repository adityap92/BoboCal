package com.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DayView extends Activity {

	TextView date;
	Bundle extras;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// makes it full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		

		setContentView(R.layout.dayview);

		date = (TextView) findViewById(R.id.tvDayDate);

		// pass date into dayView form
		String newString;
		if (savedInstanceState == null) {
			extras = getIntent().getExtras();
			if (extras == null) {
				newString = null;
			} else {
				newString = extras.getString("day");
			}
		} else {
			newString = (String) savedInstanceState.getSerializable("date");
		}

		if (newString != null) {
			date.setText(newString);
		}

	}

}
