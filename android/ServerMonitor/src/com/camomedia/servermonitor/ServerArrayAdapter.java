package com.camomedia.servermonitor;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ServerArrayAdapter extends ArrayAdapter<String> {
	private HashMap<String, Integer> _map = new HashMap<String, Integer>();

    public ServerArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
      super(context, textViewResourceId, objects);
      for (int i = 0; i < objects.size(); ++i) {
    	  _map.put(objects.get(i), i);
      }	
    }

    @Override
    public long getItemId(int position) {
      String item = getItem(position);
      return _map.get(item);
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }
}
