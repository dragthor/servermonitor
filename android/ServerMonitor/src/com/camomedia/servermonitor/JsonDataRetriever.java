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
			Log.e("App", urls[0]);
			
			JSONObject json = Json.getJson(urls[0]);
			
			result = json.toString();
			
			// publishProgress(i);
		} catch (Exception ex) {
			Log.e("App", ex.getMessage());
		}
		return result;
	}

	/* protected void onProgressUpdate(Integer... progress) {
		 Log.e("App", "Progress... " + Integer.toString(progress[0]));
    } */
}
