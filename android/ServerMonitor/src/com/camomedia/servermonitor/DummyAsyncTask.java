package com.camomedia.servermonitor;

import android.os.AsyncTask;

public class DummyAsyncTask extends AsyncTask<String, Integer, String> {
	public DummyAsyncTask() {
		super();
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
