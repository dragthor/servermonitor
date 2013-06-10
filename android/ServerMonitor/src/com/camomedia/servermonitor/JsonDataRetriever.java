package com.camomedia.servermonitor;

import android.os.AsyncTask;
import android.util.Log;
import org.json.*;

public class JsonDataRetriever extends AsyncTask<String, Integer, JSONObject> {

	public JsonDataRetriever() {
	
	}
	
	@Override
	protected JSONObject doInBackground(String...urls) {
		JSONObject json = null;
		
		try {
			Log.e(MainActivity.TAG, urls[0]);
			
			json = Json.getJson(urls[0]);
		} catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.getMessage());
		}
		return json;
	}
}
