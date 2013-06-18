package com.camomedia.servermonitor;

import java.util.ArrayList;
import com.camomedia.servermonitor.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * A fragment representing a section of the app - a listview and a refresh button.
 */
public class MonitoringGroupFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";

	public MonitoringGroupFragment() {
	}
	
	public void refreshItems(int sectionNumber) {
		ListView listItem = (ListView) this.getActivity().findViewById(R.id.lstItems);
		
		String[] listValues = getResources().getStringArray(R.array.prod_items);
		
		final ArrayList<String> list = new ArrayList<String>();
	    
		for (int i = 0; i < listValues.length; ++i) {
	      list.add(listValues[i]);
	    }
	    
		final ServerArrayAdapter adapter = new ServerArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
			
		Log.d(Utils.TAG, "Section: " + Integer.toString(sectionNumber) + ", " + Integer.toString(list.size()));
		
		listItem.setAdapter(adapter);
		
		listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
	        final String item = (String) parent.getItemAtPosition(position);
	        view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
	              @Override
	              public void run() {
	                list.remove(item);
	                adapter.notifyDataSetChanged();
	                view.setAlpha(1);
	              }
	        });
	      }
	    });	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
	
		final Button refresh = (Button) rootView.findViewById(R.id.btnRefresh);
		
		final ProgressBar spinner = (ProgressBar) rootView.findViewById(R.id.prgLoading);
		
		final int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
		
		Toast.makeText(rootView.getContext(), Integer.toString(sectionNumber) + " Created", Toast.LENGTH_SHORT).show();
		
		spinner.setVisibility(View.INVISIBLE);
		
		refresh.setOnClickListener(new OnClickListener() {
		    public void onClick(View arg0) {
		    	spinner.setVisibility(View.VISIBLE);
		    	refresh.setEnabled(false);
		    	
		    	new DummyAsyncTask() {
			    	@Override public void onPostExecute(String result) {
			    		spinner.setVisibility(View.INVISIBLE);
			    		
			    		refreshItems(sectionNumber);
			    		
			    		refresh.setEnabled(true);
			    	}
		    	}
		    	.execute("test");
		    }
		}); 
		
		return rootView;
	}
}
