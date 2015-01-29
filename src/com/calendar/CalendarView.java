package com.calendar;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class CalendarView extends Activity {
	Intent i;
	String asdf;
	public Calendar month;
	public CalendarAdapter adapter;
	public Handler handler;
	public ArrayList<Event> items; // container to store some random calendar
									// items
	Bundle extras;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// makes it full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		

		// set layout
		setContentView(R.layout.calendar);

		month = Calendar.getInstance();
		onNewIntent(getIntent());

		items = new ArrayList<Event>();
		adapter = new CalendarAdapter(this, month);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(adapter);
	

		handler = new Handler();
		handler.post(calendarUpdater);

		TextView title = (TextView) findViewById(R.id.title);
		title.setText(android.text.format.DateFormat
				.format("MMMM  20yy", month));

		TextView previous = (TextView) findViewById(R.id.previous);
		previous.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (month.get(Calendar.MONTH) == month
						.getActualMinimum(Calendar.MONTH)) {
					month.set((month.get(Calendar.YEAR) - 1),
							month.getActualMaximum(Calendar.MONTH), 1);
				} else {
					month.set(Calendar.MONTH, month.get(Calendar.MONTH) - 1);
				}
				refreshCalendar();
			}
		});

		TextView next = (TextView) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (month.get(Calendar.MONTH) == month
						.getActualMaximum(Calendar.MONTH)) {
					month.set((month.get(Calendar.YEAR) + 1),
							month.getActualMinimum(Calendar.MONTH), 1);
				} else {
					month.set(Calendar.MONTH, month.get(Calendar.MONTH) + 1);
				}
				refreshCalendar();

			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				TextView date = (TextView) v.findViewById(R.id.date);
				Intent i = new Intent("com.calendar.DAYVIEW");
				i.putExtra("day",android.text.format.DateFormat.format("MM/" + date.getText()
						+ "/yyyy", month)
						+ "");
				startActivity(i);
				

			}
		});

		gridview.setLongClickable(true);
		gridview.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				TextView date = (TextView) v.findViewById(R.id.date);
				i = new Intent("com.calendar.CREATENEW");

				if (date instanceof TextView && !date.getText().equals("")) {

					String day = date.getText().toString();
					if (day.length() == 1) {
						day = "0" + day;
					}
					// return chosen date as string format
					asdf = android.text.format.DateFormat.format("MM/" + day
							+ "/yyyy", month)
							+ "";
					i.putExtra("date", asdf);

				}

				AlertDialog dialog = new AlertDialog.Builder(CalendarView.this)
						.create();

				dialog.setTitle("Create Event for " + asdf + " ?");

				dialog.setCancelable(true);

				dialog.setButton("Yes", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						startActivity(i);
					}
				});

				dialog.setButton2("Cancel",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						});

				dialog.show();
				return false;
			}

		});

	}

	public void refreshCalendar() {
		TextView title = (TextView) findViewById(R.id.title);

		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater); // generate some random calendar items

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}

	public void onNewIntent(Intent intent) {
		month.set(month.get(Calendar.YEAR), month.get(Calendar.MONTH),
				month.get(Calendar.DAY_OF_MONTH));
	}

	public Runnable calendarUpdater = new Runnable() {

		public void run() {
			adapter.setItems(Event.events, month.getTime().getMonth()+1, month.getTime().getYear()%100);
			adapter.notifyDataSetChanged();
		}
	};

}
