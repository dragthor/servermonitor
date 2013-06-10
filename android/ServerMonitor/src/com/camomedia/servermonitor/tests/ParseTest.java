package com.camomedia.servermonitor.tests;

import android.util.Log;

import com.camomedia.servermonitor.MainActivity;
import com.parse.ParseObject;

public class ParseTest {
	public static void TestAll() {
		try {
			Sanity_Object_Save_Test();
		} catch (Exception ex) {
			Log.e(MainActivity.TAG, "ParseTest", ex);
		}
	}
	
	public static void Sanity_Object_Save_Test() {	
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("foo", "bar");
		testObject.saveInBackground();
	}
}
