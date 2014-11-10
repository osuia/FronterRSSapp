package com.bah.fronterrss;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	EditText username, password, instllation;
	Button login;
	Session session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		hideActionBar();
		setContentView(R.layout.activity_login);
		session = new Session(getApplicationContext());

		username = (EditText) findViewById(R.id.user_UserName);
		password = (EditText) findViewById(R.id.user_Password);
		password.setTypeface(Typeface.DEFAULT);
		instllation = (EditText) findViewById(R.id.user_Installation);
		instllation.setText("uia");
		login = (Button) findViewById(R.id.button1);
		
		session.checkLogin();

	}

	public void login(View view) {
		String uname = username.getText().toString();
		String pword = password.getText().toString();
		String install = instllation.getText().toString();
		session.loginSession(uname, pword, install);
		
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

	public void hideActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}
}
