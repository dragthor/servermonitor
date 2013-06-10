package com.camomedia.servermonitor.tests;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.camomedia.servermonitor.JsonDataRetriever;
import com.camomedia.servermonitor.MainActivity;

public class JsonTest {
	public static void TestAll() {
		try {
			Sanity_Json_Get_Test();
		} catch (Exception ex) {
			Log.e(MainActivity.TAG, "JsonTest", ex);
		}	
	}
	
	public static void Sanity_Json_Get_Test() {
	
		new JsonDataRetriever()
		{
		    @Override public void onPostExecute(JSONObject jsonResult)
		    {
		    	try {
			    	if (jsonResult == null) {
						throw new Exception("Json object is null");
			    	} else {
			    		JSONArray data	= (JSONArray) jsonResult.get("data");
			    		
			    		if (data == null) {
			    			throw new Exception("Json data object is null");
			    		}
			    		
			    		if (data.length() == 0) {
			    			throw new Exception("Json data array is zero");
			    		}
			    		
			    		 Log.d(MainActivity.TAG, Integer.toString(data.length()));
			    		 
			    		 
			    		/*
			    		JSONArray jsonPersonData = data.getJSONArray(1);
			    		
			    		for (int i=0; i<jsonPersonData.length(); i++) {
			    		    JSONObject item = jsonPersonData.getJSONObject(i);
			    		    String name = item.getString("name");
			   
			    		    Log.d(MainActivity.TAG, name);
			    		} */
			    	}
		    	} catch (Exception ex) {
		    		Log.e(MainActivity.TAG, "JsonTest", ex);
		    	}
		    }
		}.execute("http://dragthor.github.io/southridge/albums-11.json");	
	}
}
