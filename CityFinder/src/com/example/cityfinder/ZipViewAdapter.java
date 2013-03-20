
package com.example.cityfinder;

import java.util.List;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ZipViewAdapter extends ArrayAdapter<BriefResult>{
	
	BriefResult[] zipviews;
	List<BriefResult> lzipview;
	Context context;
	BriefResult zipview;
	private ViewHolder viewHolder;

	public ZipViewAdapter(Context context, int textViewResourceId, BriefResult[] objects) {
		super(context, textViewResourceId, objects);
		zipviews = objects;
		this.context = context;
		viewHolder = new ViewHolder();
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		zipview = lzipview.get(position);
		
		if (convertView==null)
		{
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflator.inflate(R.layout.zipcodeview, parent, false);
		viewHolder = new ViewHolder();
		viewHolder.zip = (TextView) convertView.findViewById(R.id.zip);
		viewHolder.city = (TextView) convertView.findViewById(R.id.city);
		viewHolder.state = (TextView) convertView.findViewById(R.id.state);
		
		convertView.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		
		viewHolder.zip.setText("Zip : " + zipview.getZip());
		viewHolder.city.setText("City : " +zipview.getCity());
		viewHolder.state.setText("State : " + zipview.getState());

	
		return convertView;

		
	}

	public ZipViewAdapter(Context context, int textViewResourceId,
			List<BriefResult> objects) {
		super(context, textViewResourceId, objects);
		lzipview = objects;
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	static private class ViewHolder {
		TextView zip;
		TextView city;
		TextView state;
		
	}

}
