package com.camomedia.servermonitor;

import android.content.Context;
import android.content.Intent;

import com.parse.Parse;
import com.parse.ParseAnalytics;

public class ParseManager {
	public static final String PARSE_APPID = "KQLXx6w5tQmzP9qacxwac4HLfy1eewISrBPPyvnU";
	public static final String PARSE_CLIENTKEY = "3bvLwQwruTX4GgeI1EqBHDV4MoCW7CQ3WO3YnPc5";
	
	public static void Initialize(Context context, Intent intent) {
		Parse.initialize(context, ParseManager.PARSE_APPID, ParseManager.PARSE_CLIENTKEY);
		
		ParseAnalytics.trackAppOpened(intent);
	}
}