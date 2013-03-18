
package com.example.cityfinder;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class Citymap extends MapActivity {

	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub

		super.onCreate(arg0);
		setContentView(R.layout.mapview);
		
		String[] info = getIntent().getExtras().getStringArray("info");
		
		MapView mapview = (MapView) findViewById(R.id.mapview);
		mapview.setBuiltInZoomControls(true);
		
		
		List<Overlay> mapOverlays = mapview.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
		MapOverlay itemizedoverlay = new MapOverlay(drawable, this, info);
		
//		GeoPoint point = new GeoPoint(19240000,-99120000);
//		GeoPoint point = new GeoPoint(35410000, 139460000);

		//double lat = Double.valueOf(info[4]);
		
		Log.d("size is: ", String.valueOf(info.length));
		
		String lat = info[5].replace("\"", "");
		String longi = info[6].replace("\"", "");
		
		lat = lat.replace(" ", "");
		longi = longi.replace(" ", "");
		
		Log.d("lat is: ", lat);
		Log.d("longi is: ", longi);
		
		
		
		GeoPoint point = new GeoPoint((int)(Double.valueOf(lat) * 1E6),(int)(Double.valueOf(longi) * 1E6));
		OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
		
		mapview.getController().setCenter(point);
		mapview.getController().setZoom(12);
		


		
		itemizedoverlay.addOverlay(overlayitem);

		mapOverlays.add(itemizedoverlay);
		
//		mapview.invalidate();
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
