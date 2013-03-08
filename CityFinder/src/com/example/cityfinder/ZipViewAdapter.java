package com.example.cityfinder;

import java.util.List;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ZipViewAdapter extends ArrayAdapter<ZipcodeRow>{
	
	ZipcodeRow[] zipviews;
	List<ZipcodeRow> lzipview;
	Context context;
	ZipcodeRow zipview;

	public ZipViewAdapter(Context context, int textViewResourceId, ZipcodeRow[] objects) {
		super(context, textViewResourceId, objects);
		zipviews = objects;
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		ZipcodeRow zipview = zipviews[position];
		zipview = lzipview.get(position);
		
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowview = inflator.inflate(R.layout.zipcodeview, parent, false);
		TextView zip = (TextView) rowview.findViewById(R.id.zip);
		TextView city = (TextView) rowview.findViewById(R.id.city);
		TextView state = (TextView) rowview.findViewById(R.id.state);

		
		zip.setText("Zip : " + zipview.zipCodeData.getZipcode());
		city.setText("City : " +zipview.locationData.getCity());
		state.setText("State : " + zipview.locationData.getState());

	
		return rowview;

		
	}

	public ZipViewAdapter(Context context, int textViewResourceId,
			List<ZipcodeRow> objects) {
		super(context, textViewResourceId, objects);
		lzipview = objects;
		this.context = context;
		// TODO Auto-generated constructor stub
	}


}
