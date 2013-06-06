package com.camomedia.servermonitor;

import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_test);

		LinearLayout layout = (LinearLayout) this.findViewById(R.id.layoutTests);
		
		LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		 for (int i = 0; i < 100; i++) {
			TextView test = new TextView(this);
			test.setText("Unit Test " + Integer.toString(i));
			test.setLayoutParams(lparams);
			
			layout.addView(test);
		} 
	}
	
	private void Test_JsonSimple() {
    	new JsonDataRetriever()
		{
		    @Override public void onPostExecute(JSONObject jsonResult)
		    {
		    	/*
		    	try {
					JSONObject json2 = jsonResult.getJSONObject("data");
					
					JSONArray names = json2.getJSONArray("name");
					
					for (int i = 0; i < names.length(); i++) {
						Log.d(TAG, names.getString(i));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
		    	
		    	//Log.e(MainActivity.TAG, jsonResult);
		    }
		}.execute("http://dragthor.github.io/southridge/albums-11.json");		
	}
}
