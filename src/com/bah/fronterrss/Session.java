package com.bah.fronterrss;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Session {

	SharedPreferences pref;
	Editor editor;
	Context context;
	private static final String IS_LOGIN = "UserIsLoggedIn";
	public final String KEY_USERNAME = "name";
	public final String KEY_PASSWORD = "password";
	public final String KEY_INSTALLATION = "installation";

	public Session(Context context) {
		this.context = context;
		pref = PreferenceManager.getDefaultSharedPreferences(context);
		editor = pref.edit();
		editor.commit();
	}

	public void loginSession(String name, String password, String install) {
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(KEY_USERNAME, name);
		editor.putString(KEY_PASSWORD, password);
		editor.putString(KEY_INSTALLATION, install);
		editor.commit();
	}

	public void checkLogin() {
		if (isLoggedIn()) {
			Intent p = new Intent(context, MainActivity.class);
			p.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(p);
		}
	}

	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
		user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
		user.put(KEY_INSTALLATION, pref.getString(KEY_INSTALLATION, null));
		return user;
	}

	public void logoutUser() {
		editor.clear();
		editor.commit();
		Intent i = new Intent(context, LoginActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		context.startActivity(i);
	}

	public boolean isLoggedIn() {
		return pref.getBoolean(IS_LOGIN, false);
	}

}