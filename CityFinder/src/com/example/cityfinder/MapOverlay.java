
package com.example.cityfinder;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MapOverlay extends ItemizedOverlay {
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	Context mcontext;
	String[] demo;
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}

	@Override
	protected boolean onTap(int arg0) {
		// TODO Auto-generated method stub
		
			Intent intent = new Intent(mcontext, demoinfo.class);
			intent.putExtra("info", demo);
			mcontext.startActivity(intent);
			
//		  OverlayItem item = mOverlays.get(arg0);
//		  AlertDialog.Builder dialog = new AlertDialog.Builder(mcontext);
//		  dialog.setTitle(item.getTitle());
//		  dialog.setMessage(item.getSnippet());
//		  dialog.show();
		  return true;
	//	return super.onTap(arg0);
	}

	public MapOverlay(Drawable arg0) {
		super(arg0);
//		mContext = context;
		// TODO Auto-generated constructor stub
	}
	
	public MapOverlay(Drawable arg0, Context context, String[] info) {

		super(boundCenterBottom(arg0));
		demo = info;
		mcontext = context;
//		mContext = context;
		// TODO Auto-generated constructor stub
	}


	@Override
	protected OverlayItem createItem(int arg0) {
		// TODO Auto-generated method stub
		return mOverlays.get(arg0);
	//	return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}

}
