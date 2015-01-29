package com.calendar;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyEvents extends Activity {

	ListView listView;
	Event ev;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// makes activity full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.events);

		listView = (ListView) findViewById(R.id.mylist);

	
		
		String[] values = new String[Event.events.size()];

		for(int i=0; i<Event.events.size();i++){
			values[i]=Event.events.get(i).toString();
		}
		//if(Event.events.size()==0)
			
		// First paramenter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the View to which the data is written
		// Forth - the Array of data
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"Click ListItem Number " + pos, Toast.LENGTH_LONG)
						.show();
			}
		});
	}

}
