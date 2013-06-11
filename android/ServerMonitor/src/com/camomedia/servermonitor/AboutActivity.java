package com.camomedia.servermonitor;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.text.util.Linkify;
import android.widget.TextView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_about);

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
        
        TextView privacy = (TextView) this.findViewById(R.id.lblPrivacyUrl);
        
        Linkify.addLinks(privacy, Linkify.WEB_URLS);
	}
}
