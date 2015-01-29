package com.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Menu extends Activity {

	Button calendar, createEvent,events;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//makes activity full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	

		setContentView(R.layout.menu);
		
		//adds widgets
		calendar = (Button) findViewById(R.id.bCalendar);
		createEvent = (Button) findViewById(R.id.bCreateEvent);
		events=(Button) findViewById(R.id.bEvents);
		
		//button listener
		calendar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(
						"com.calendar.CALENDAR"));
			}
		});
		
		
		createEvent.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.calendar.CREATENEW"));
			}
		});

		events.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			startActivity(new Intent("com.calendar.EVENTS"));	
			}
		});
	}

}
