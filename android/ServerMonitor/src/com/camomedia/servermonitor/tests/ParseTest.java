package com.camomedia.servermonitor.tests;

import java.util.List;

import android.util.Log;

import com.camomedia.servermonitor.MainActivity;
import com.camomedia.servermonitor.MonitoringItem;
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
		ParseObject objMon = new ParseObject(MonitoringItem.PARSE_CLASS);
		objMon.put(MonitoringItem.PARSE_PARSETYPE, 0);
		objMon.put(MonitoringItem.PARSE_PATTERN, "foo");
		objMon.put(MonitoringItem.PARSE_TAB, 0);
		objMon.put(MonitoringItem.PARSE_URL, "http://www.google.com");
		objMon.saveInBackground();
	}
	
	public static void Monitor_Query_Test() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Monitoring");
		query.whereEqualTo(MonitoringItem.PARSE_TAB, 0);
		query.whereNotEqualTo(MonitoringItem.PARSE_PATTERN, "foo");
		
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> list, ParseException e) {
				// TODO Auto-generated method stub
		        if (e == null) {
		        	for (int i = 0; i < list.size(); i++) {
			        	MonitoringItem item = new MonitoringItem();
			        	
			        	item.set_objectId(list.get(i).getObjectId());
			        	item.set_pattern((String) list.get(i).get(MonitoringItem.PARSE_PATTERN));
			        	item.set_url((String) list.get(i).get(MonitoringItem.PARSE_URL));
			        	
			        	Log.d(MainActivity.TAG, "Retrieved object " + item.get_objectId());
			            Log.d(MainActivity.TAG, "With url " + item.get_url());
			            Log.d(MainActivity.TAG, "With pattern  " + item.get_pattern());
		        	}
		        } else {
		            Log.d(MainActivity.TAG, "Error: " + e.getMessage());
		        }	
			}
		});
	}
}
