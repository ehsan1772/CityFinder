
package com.example.cityfinder;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * This class represents the pin that is displayed on top of the map to whoe the city location
 * @author Ehsan Barekati
 *
 */
public class MapOverlay extends ItemizedOverlay {
	
	private ArrayList<OverlayItem> mOverlays;
	private Context mcontext;
	private String[] demo;
	
	public MapOverlay(Drawable arg0, Context context, String[] info) {

		super(boundCenterBottom(arg0));
		demo = info;
		mcontext = context;
		mOverlays = new ArrayList<OverlayItem>();
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}

	/**
	 * This method is the onClickListener for the view and starts another activity
	 */
	@Override
	protected boolean onTap(int arg0) {

			Intent intent = new Intent(mcontext, DemographicInfo.class);
			intent.putExtra("info", demo);
			mcontext.startActivity(intent);
			
		  return true;
	}

	public MapOverlay(Drawable arg0) {
		super(arg0);
	}
	
	@Override
	protected OverlayItem createItem(int arg0) {
		return mOverlays.get(arg0);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

}
