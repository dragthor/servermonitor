package com.camomedia.servermonitor;

import com.camomedia.servermonitor.R;
import com.camomedia.servermonitor.tests.JsonTest;
import com.camomedia.servermonitor.tests.ParseTest;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android`.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter _sectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager _viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
				
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		_sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this.getBaseContext());

		// Set up the ViewPager with the sections adapter.
		_viewPager = (ViewPager) findViewById(R.id.pager);
		_viewPager.setAdapter(_sectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		_viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < _sectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(_sectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
				
		ParseManager.Initialize(this, getIntent());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
		    case R.id.about_settings:
		    	Intent aboutIntent = new Intent(this, AboutActivity.class);
		    	startActivity(aboutIntent);
		        return true;
		    case R.id.action_settings:
		    	Intent settingsIntent = new Intent(this, SettingsActivity.class);
		    	startActivity(settingsIntent);
		        return true;
		    case R.id.unit_test_settings:
		    	ParseTest.TestAll();
		    	JsonTest.TestAll();
		    	return true;
		    case R.id.close_settings:
		    	this.finish();
		    	return true;
		    default:
		        return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		int pos = tab.getPosition();
		
		Toast.makeText(this.getBaseContext(), Integer.toString(pos) + " Selected", Toast.LENGTH_SHORT).show();
		
		// When the given tab is selected, switch to the corresponding page in the ViewPager.
		_viewPager.setCurrentItem(pos);
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		int pos = tab.getPosition();
		
		Toast.makeText(this.getBaseContext(), Integer.toString(pos) + " Unselected", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		int pos = tab.getPosition();
		
		Toast.makeText(this.getBaseContext(), Integer.toString(pos) + " Reselected", Toast.LENGTH_SHORT).show();
	}
}
