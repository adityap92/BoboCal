package com.calendar;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateNew extends FragmentActivity {

	private Button createNew, startTime, endTime;
	private Calendar cal;
	CalendarView cv;
	private TextView dateUpdate, startUpdate, endUpdate, time;
	private int id;
	private Toast toast;
	private Date date, sTime, eTime, now;
	private Bundle extras;
	private ArrayList<String> events;
	private EditText description, eventName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// makes activity full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		

		setContentView(R.layout.createnew);

		// initialize widgets
		dateUpdate = (TextView) findViewById(R.id.tvDateUpdate);
		startUpdate = (TextView) findViewById(R.id.tvStartUpdate);
		endUpdate = (TextView) findViewById(R.id.tvEndUpdate);
		time = (TextView) findViewById(R.id.tvEventDate);
		createNew = (Button) findViewById(R.id.bCreateNew);
		startTime = (Button) findViewById(R.id.bStartTime);
		endTime = (Button) findViewById(R.id.bEndTime);
		cal = Calendar.getInstance();
		now = new Date();
		events = new ArrayList<String>();
		description= (EditText) findViewById(R.id.etDescript);
		eventName = (EditText) findViewById(R.id.etTitle);
		// pass date into create event form
		String newString;
		if (savedInstanceState == null) {
			extras = getIntent().getExtras();
			if (extras == null) {
				newString = null;
			} else {
				newString = extras.getString("date");
			}
		} else {
			newString = (String) savedInstanceState.getSerializable("date");
		}

		if (newString != null) {
			dateUpdate.setText(newString);
		}

		// button listeners
		createNew.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Event(dateUpdate.getText()+"", startUpdate.getText()+"", endUpdate.getText()+"",description.getText() +"");
				Event.events.add(dateUpdate.getText()+"");
				
				boolean working=true;
				/*try{
				String date=dateUpdate.getText()+"";
				String sUpdate=startUpdate.getText()+"";
				String eUpdate=endUpdate.getText()+"";
				String descript=description.getText() +"";
				
				
				
				EventSQL entry = new EventSQL(CreateNew.this);
				
				entry.open();
				
				entry.createEntry(date, sUpdate, eUpdate, descript);
				
				entry.close();
				}catch(Exception e){
					working=false;
				}finally{
					if(working){
						Dialog d = new Dialog(CreateNew.this);
						d.setTitle("Uploading..");
						TextView tv = new TextView(CreateNew.this);
						tv.setText("Complete!");
						d.setContentView(tv);
						d.show();
					}*/
				
		
			}
		});

		startTime.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				id = v.getId();
				showTimePickerDialog(v);
			}
		});

		endTime.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				id = v.getId();
				showTimePickerDialog(v);
			}
		});
	}

	private class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		int year, month, day;

		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker

			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			day = cal.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int y, int m, int d) {
			// Do something with the date chosen by the user
			date = new Date(y, m, d, 0, 0, 0);
			if (date.before(now)) {
				toast = Toast.makeText(getApplicationContext(),
						"Choose a date in the present.", Toast.LENGTH_LONG);
				toast.show();
			} else
				dateUpdate.setText(android.text.format.DateFormat.format(
						"MM/dd/20yy", date));

		}
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}

	private class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {
		int hour, minute;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker

			hour = cal.get(Calendar.HOUR_OF_DAY);
			minute = cal.get(Calendar.MINUTE);

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}

		public void onTimeSet(TimePicker view, int h, int m) {
			// Do something with the time chosen by the user

			switch (id) {
			case R.id.bStartTime:
				if (dateUpdate.length() == 0) {
					toast = Toast.makeText(getApplicationContext(),
							"Choose a Date first.", Toast.LENGTH_SHORT);
					toast.show();
				} else
					sTime = new Date(Integer.parseInt(""
							+ dateUpdate.getText().subSequence(6, 10)),
							Integer.parseInt(""
									+ dateUpdate.getText().subSequence(0, 2)),
							Integer.parseInt(""
									+ dateUpdate.getText().subSequence(3, 5)),
							h, m);
				if (sTime.before(now)) {
					toast = Toast
							.makeText(getApplicationContext(),
									"Choose a time in the present.",
									Toast.LENGTH_SHORT);
					toast.show();
				} else
					startUpdate.setText("  "
							+ android.text.format.DateFormat.format("hh:mm aa",
									sTime));
				break;
			case R.id.bEndTime:
				if (dateUpdate.length() == 0) {
					toast = Toast.makeText(getApplicationContext(),
							"Choose a Date first.", Toast.LENGTH_SHORT);
					toast.show();
				} else
					eTime = new Date(Integer.parseInt(""
							+ dateUpdate.getText().subSequence(6, 10)),
							Integer.parseInt(""
									+ dateUpdate.getText().subSequence(0, 2)),
							Integer.parseInt(""
									+ dateUpdate.getText().subSequence(3, 5)),
							h, m);

				if (sTime == null) {
					toast = Toast.makeText(getApplicationContext(),
							"Choose a Start Time first.", Toast.LENGTH_SHORT);
					toast.show();
				} else {

					if (eTime.before(sTime)) {
						toast = Toast.makeText(getApplicationContext(),
								"Choose a time AFTER the start time.",
								Toast.LENGTH_SHORT);
						toast.show();
					} else
						endUpdate.setText("  "
								+ android.text.format.DateFormat.format(
										"hh:mm aa", eTime));
				}
				break;
			}

		}

	}

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(getSupportFragmentManager(), "timePicker");
	}

	public void setDate(int m, int d, int y) {

		dateUpdate.setText(android.text.format.DateFormat.format("hh:mm aa",
				new Date(y, m, d)));

	}

	private void postEvents()
	{
		DefaultHttpClient client = new DefaultHttpClient();

		/** FOR LOCAL DEV   HttpPost post = new HttpPost("http://192.168.0.186:3000/events"); //works with and without "/create" on the end */
		HttpPost post = new HttpPost("http://bobocal.herokuapp.com");
	    JSONObject holder = new JSONObject();
	    JSONObject eventObj = new JSONObject();

	    String date = "";
	    date = dateUpdate.getText().toString();

	    try {	
	    	
		    eventObj.put("name", eventName.getText().toString());
		    eventObj.put("date", date);
		    eventObj.put("start", startUpdate.getText().toString());
		    eventObj.put("end", endUpdate.getText().toString());
		    eventObj.put("comment", description.getText().toString());

		    holder.put("myevent", eventObj);

		    Log.e("Event JSON", "Event JSON = "+ holder.toString());

	    	StringEntity se = new StringEntity(holder.toString());
	    	post.setEntity(se);
	    	post.setHeader("Content-Type","application/json");


	    } catch (UnsupportedEncodingException e) {
	    	Log.e("Error",""+e);
	        e.printStackTrace();
	    } catch (JSONException js) {
	    	js.printStackTrace();
	    }

	    HttpResponse response = null;

	    try {
	        response = client.execute(post);
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	        Log.e("ClientProtocol",""+e);
	    } catch (IOException e) {
	        e.printStackTrace();
	        Log.e("IO",""+e);
	    }

	    HttpEntity entity = response.getEntity();

	    if (entity != null) {
	        try {
	            entity.consumeContent();
	        } catch (IOException e) {
	        	Log.e("IO E",""+e);
	            e.printStackTrace();
	        }
	    }

	    Toast.makeText(this, "Your post was successfully uploaded", Toast.LENGTH_LONG).show();

	}

}
