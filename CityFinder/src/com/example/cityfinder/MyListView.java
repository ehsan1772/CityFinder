
package com.example.cityfinder;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * This class extends ListView and registered appropriate callbacks through implementing OnItemLongClickListener and MyAlterDialogueOwner interfaces
 * It gets added to the activity in the XML layout
 * @author Ehsan Barekati
 *
 */
public class MyListView extends ListView implements OnItemClickListener {

	private MyListViewOwner theOwner; 
	private Context context;

	
	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize(context);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context) {
		super(context);
		initialize(context);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * gets calls in constructors and registers the onClickListeners
	 * @param context
	 */
	private void initialize(Context context){
		this.context = context;
		this.setOnItemClickListener(this);
	}
	 
	 public void setTheOwner(MyListViewOwner theOwner){
		 this.theOwner = theOwner;
	 }



	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		BriefResult temp = (BriefResult) theOwner.getClickedItem(arg2);
		ZipcodeRow map = DemographicProducer.getDemographic(theOwner.getCursor(), temp.getRowPosition());
		
	   	Intent intent = new Intent(context.getApplicationContext(), CityMap.class);
	   	
	   	intent.putExtra("info", makeExtraData(map));
	   			
		context.startActivity(intent);
		
	}

	private String[] makeExtraData(ZipcodeRow theRow){
		String[] result = new String[10];
		
		result[0] = theRow.locationData.getCity();
		result[1] = theRow.locationData.getState();
		result[2] = theRow.zipCodeData.getPopulation();
		result[3] = theRow.zipCodeData.getHousing();
		result[4] = theRow.zipCodeData.getIncome();
		
		result[5] = theRow.zipCodeData.getLatitude();
		result[6] = theRow.zipCodeData.getLongitude();
		result[7] = theRow.locationData.getCounty();
		result[8] = theRow.zipCodeData.getLandArea();
		result[9] = theRow.zipCodeData.getWaterArea();
			
		return result;
	}


}
