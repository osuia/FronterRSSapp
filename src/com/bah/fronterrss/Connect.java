package com.bah.fronterrss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;


public class Connect extends AsyncTask<String, String, String>{
	AsyncResponse mDelegate;
	String result;
	Session sessionCon;
	Context mContext;
		
	String USER_NAME;
	String PASSWORD;
	
	public Connect(Context context, AsyncResponse delegate){
		mContext = context;
		mDelegate = delegate;
	}
	
	@Override
	protected void onPreExecute() {
				
		super.onPreExecute();
	}
	@Override
	protected String doInBackground(String... urls) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(urls[0]);
		
		sessionCon = new Session(mContext);
		HashMap<String, String> user = sessionCon.getUserDetails();
		USER_NAME = user.get(sessionCon.KEY_USERNAME);
		PASSWORD = user.get(sessionCon.KEY_PASSWORD);
		
		String credentials = USER_NAME + ":" + PASSWORD;
		String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
		request.setHeader("Authorization", "Basic " + base64EncodedCredentials);
		Log.v("TEST", urls[0]);
		
		try {
			HttpResponse response = client.execute(request);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content, "iso-8859-1"));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				content.close();
				result = builder.toString();
			}
			else {
				sessionCon = new Session(mContext);
				sessionCon.logoutUser();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	@Override
	protected void onPostExecute(String result) {
		try {
			mDelegate.processFinished(result);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
