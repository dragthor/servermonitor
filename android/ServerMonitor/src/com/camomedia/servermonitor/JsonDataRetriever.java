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
			
			result = "Success";
			
			 for(int i=0;i<5;i++) {
                 try {
                     Thread.sleep(1000);
                     
                     publishProgress(i);
                     
                 } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                	 Log.e("App", e.getMessage());
                 }
             }
			 
			result = "Success";
		} catch (Exception ex) {
			Log.e("App", ex.getMessage());
		}
		return result;
	}

	protected void onProgressUpdate(Integer... progress) {
		 Log.e("App", "Progress... " + Integer.toString(progress[0]));
    }
}
