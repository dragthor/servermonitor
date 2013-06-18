package com.camomedia.servermonitor;

import java.util.Locale;

import com.camomedia.servermonitor.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
	private Context _context = null;
	
	public SectionsPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
	
		_context = context;
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a MonitoringGroupFragment with the page number as its lone argument.
		Fragment fragment = new MonitoringGroupFragment();
		Bundle args = new Bundle();
		args.putInt(MonitoringGroupFragment.ARG_SECTION_NUMBER, position + 1);
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
			return _context.getString(R.string.title_prod).toUpperCase(l);
		case 1:
			return _context.getString(R.string.title_qa).toUpperCase(l);
		case 2:
			return _context.getString(R.string.title_shop).toUpperCase(l);
		}
		return null;
	}
}