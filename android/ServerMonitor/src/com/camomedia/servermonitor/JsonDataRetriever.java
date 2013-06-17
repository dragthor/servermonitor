package com.camomedia.servermonitor;

import android.os.AsyncTask;
import android.util.Log;
import org.json.*;

public class JsonDataRetriever extends AsyncTask<String, Integer, JSONObject> {

	public JsonDataRetriever() {
		super();
	}
	
	@Override
	protected JSONObject doInBackground(String...urls) {
		JSONObject json = null;
		
		try {
			Log.e(Utils.TAG, urls[0]);
			
			json = Json.getJson(urls[0]);
		} catch (Exception ex) {
			Log.e(Utils.TAG, ex.getMessage());
		}
		return json;
	}
}
