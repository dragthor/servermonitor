package com.camomedia.servermonitor;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Intent;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		setTitle("About");
		
		// Get instance of Vibrator from current Context
		Vibrator v = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);

		// Vibrate for 200 milliseconds
		v.vibrate(200);

        TextView email = (TextView) this.findViewById(R.id.lblEmail);
        
        Linkify.addLinks(email, Linkify.EMAIL_ADDRESSES);
        
        TextView wiki = (TextView) this.findViewById(R.id.lblWiki);
        
        Linkify.addLinks(wiki, Linkify.WEB_URLS);
        
        TextView projectHome = (TextView) this.findViewById(R.id.lblProjectHome);
        
        Linkify.addLinks(projectHome, Linkify.WEB_URLS);
	}
}
