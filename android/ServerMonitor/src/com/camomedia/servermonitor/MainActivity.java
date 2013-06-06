package com.camomedia.servermonitor;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.camomedia.servermonitor.R;
import com.camomedia.servermonitor.R.array;
import com.camomedia.servermonitor.R.id;
import com.camomedia.servermonitor.R.layout;
import com.camomedia.servermonitor.R.menu;
import com.camomedia.servermonitor.R.string;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	public static final String TAG = "ServerMonitor";
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android`.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
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
	    	Intent testIntent = new Intent(this, TestActivity.class);
	    	startActivity(testIntent);		
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
			
			final ProgressBar spinner = (ProgressBar) rootView.findViewById(R.id.progressBar1);
	
			spinner.setVisibility(View.INVISIBLE);
			
			int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
			
			spinner.setVisibility(View.VISIBLE);
			
			ListView listItem = (ListView) rootView.findViewById(R.id.lstItems);
			
			String[] listValues = getResources().getStringArray(R.array.prod_items);
			
			final ArrayList<String> list = new ArrayList<String>();
		    
			for (int i = 0; i < listValues.length; ++i) {
		      list.add(listValues[i]);
		    }
		    
			final ServerArrayAdapter adapter = new ServerArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, list);
				
			Log.d(MainActivity.TAG, "Section: " + Integer.toString(sectionNumber) + ", " + Integer.toString(list.size()));
			
			listItem.setAdapter(adapter);
			
			listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			      @Override
			      public void onItemClick(AdapterView<?> parent, final View view,
			          int position, long id) {
			        final String item = (String) parent.getItemAtPosition(position);
			        view.animate().setDuration(1000).alpha(0)
			            .withEndAction(new Runnable() {
			              @Override
			              public void run() {
			                list.remove(item);
			                adapter.notifyDataSetChanged();
			                view.setAlpha(1);
			              }
			            });
			      }

			    });
			
			
			new JsonDataRetriever()
			{
			    @Override public void onPostExecute(JSONObject jsonResult)
			    {
			    	// TODO: do something with the jsonResult.
			    	spinner.setVisibility(View.INVISIBLE);
			    }
			}.execute("http://dragthor.github.io/southridge/albums-11.json");
			
			return rootView;

		}
	}
}
