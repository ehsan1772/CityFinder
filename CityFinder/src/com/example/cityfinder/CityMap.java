
package com.example.cityfinder;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

/**
 * This class represents the activity that shows a map zoomed on a specific point(the clicked city)
 * There is a pin on the map showing the exact location
 * @author Ehsan Barekati
 *
 */
public class CityMap extends MapActivity {

	private MapView mapView;
	private String[] info;
	private Drawable pin;
	private List<Overlay> mapOverlays;
	private MapOverlay itemizedoverlay;
	private String lat;
	private String longi;
	private GeoPoint point;
	private OverlayItem overlayitem;
	
	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		setContentView(R.layout.mapview);
		pin = this.getResources().getDrawable(R.drawable.pin);
		mapView = (MapView) findViewById(R.id.mapview);
		
		info = getIntent().getExtras().getStringArray("info");
		
		setThePoint();
		prepareMapView();
		
		overlayitem = new OverlayItem(point, null, null);
		
		itemizedoverlay = new MapOverlay(pin, this, info);
		itemizedoverlay.addOverlay(overlayitem);

		mapOverlays = mapView.getOverlays();
		mapOverlays.add(itemizedoverlay);
				
	}
	
	private void prepareMapView(){
		mapView.setBuiltInZoomControls(true);
		mapView.getController().setCenter(point);
		mapView.getController().setZoom(12);
	}
	
	private void setThePoint(){
		lat = info[5].replace("\"", "");
		longi = info[6].replace("\"", "");
		
		lat = lat.replace(" ", "");
		longi = longi.replace(" ", "");
		point = new GeoPoint((int)(Double.valueOf(lat) * 1E6),(int)(Double.valueOf(longi) * 1E6));
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
