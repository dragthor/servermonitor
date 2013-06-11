package com.camomedia.servermonitor.tests;

import java.util.List;

import android.util.Log;

import com.camomedia.servermonitor.MainActivity;
import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ParseTest {
	public static void TestAll() {
		try {
			Monitor_Save_Test();
			Monitor_Query_Test();
		} catch (Exception ex) {
			Log.e(MainActivity.TAG, "ParseTest", ex);
		}
	}
	
	public static void Monitor_Save_Test() {	
		ParseObject objMon = new ParseObject("Monitoring");
		objMon.put("ParseType", 0);
		objMon.put("Pattern", "foo");
		objMon.put("Tab", 0);
		objMon.put("Url", "http://www.google.com");
		objMon.saveInBackground();
	}
	
	public static void Monitor_Query_Test() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Monitoring");
		query.whereEqualTo("Tab", 0);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> list, ParseException e) {
				// TODO Auto-generated method stub
		        if (e == null) {
		        	String pattern = (String) list.get(0).get("Pattern");
		        	
		            Log.d(MainActivity.TAG, "Retrieved " + list.size() + " items");
		            Log.d(MainActivity.TAG, "With pattern  " + pattern);
		        } else {
		            Log.d(MainActivity.TAG, "Error: " + e.getMessage());
		        }	
			}
		});
	}
}
