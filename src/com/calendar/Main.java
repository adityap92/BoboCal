package com.calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {
	/** Called when the activity is first created. */

	//instance variables
	TextView username, pass, forgot, signUp;
	EditText user, password;
	Button login;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//makes activity full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		//adds widgets
		username = (TextView) findViewById(R.id.tvUser);
		pass = (TextView) findViewById(R.id.tvPass);
		user = (EditText) findViewById(R.id.etUser);
		password = (EditText) findViewById(R.id.etPass);
		login = (Button) findViewById(R.id.bLogin);
		forgot = (TextView) findViewById(R.id.tvForgot);
		signUp= (TextView) findViewById(R.id.tvSignUp);
		
		
		//button listener
		login.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.calendar.MENU"));
			}
		});

	}
}