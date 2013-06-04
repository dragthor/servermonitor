package com.camomedia.servermonitor;

import android.os.AsyncTask;
import android.util.Log;
import org.json.*;

public class JsonDataRetriever extends AsyncTask<String, Integer, String> {

	public JsonDataRetriever() {
	
	}
	
	@Override
	protected String doInBackground(String...urls) {
		String result = "Done and done.";
		
		try {
			Log.e(MainActivity.TAG, urls[0]);
			
			JSONObject json = Json.getJson(urls[0]);
			
			result = json.toString();
			
			// publishProgress(i);
		} catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.getMessage());
		}
		return result;
	}

	/* protected void onProgressUpdate(Integer... progress) {
		 Log.e(MainActivity.TAG, "Progress... " + Integer.toString(progress[0]));
    } */
}
