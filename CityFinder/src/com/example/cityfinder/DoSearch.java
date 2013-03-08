package com.example.cityfinder;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;


public class DoSearch extends AsyncTask<Void, ProgressBar,List<ZipcodeRow>>{
	
	private SQLiteDatabase db;
	private String searchPhrase;
	private OnSearchCompleteListener listener;
	private List<ZipcodeRow> intertable;
	
	public DoSearch(SQLiteDatabase db, String searchPhrase, OnSearchCompleteListener listener)
	{		
		this.searchPhrase = searchPhrase;
		this.db = db;
		this.listener = listener;
	}


	@Override
	protected List<ZipcodeRow> doInBackground(Void... params) {
		// TODO Auto-generated method stub
		intertable = new ArrayList<ZipcodeRow>();

    	Cursor zipcursor = null;
    	Cursor refcursor = null;
    	Cursor citycursor = null;
    	
    	int zipcode;
    	try{
    	zipcode = Integer.valueOf(searchPhrase);
    	}
    	catch(NumberFormatException exeption)
    	{
    		zipcode = -1;
    	}
    	
    	if (zipcode != -1)
    	{
    		zipcursor = db.rawQuery("SELECT * FROM ZipCodeData;", null);	    	
    	if (zipcursor != null && zipcursor.moveToFirst())
    	{
    		do
    		{
    			String Zipcode = zipcursor.getString(1);
    			String ZIP_id = zipcursor.getString(0);
    			
    			if (Zipcode.contains(String.valueOf(zipcode)))
    			{

    				refcursor = db.rawQuery("SELECT * FROM CrossReference WHERE ZipCodeId =" + String.valueOf(ZIP_id) , null);
    		    	if (refcursor != null && refcursor.moveToFirst())
    		    	{
    		    		do
    		    		{
    		    		String relatedcity = refcursor.getString(1);
    		    		Log.d("Query is:","SELECT * FROM  LocationData WHERE _id =" + relatedcity);
    		    		citycursor = db.rawQuery("SELECT * FROM  LocationData WHERE _id =" + relatedcity , null);
	    		    		do
	    		    		{
	    		    			ZipcodeRow temp = new ZipcodeRow();
	    		    			
	    		    			citycursor.moveToFirst();
	    		    			
	    		    			
	    		    			temp.zipCodeData.setZipcode(zipcursor.getString(1));
	    		    			temp.zipCodeData.setLatitude(zipcursor.getString(2));
	    		    			temp.zipCodeData.setLongitude(zipcursor.getString(3));
	    		    			temp.zipCodeData.setPopulation(zipcursor.getString(4));
	    		    			temp.zipCodeData.setHousing(zipcursor.getString(5));
	    		    			temp.zipCodeData.setIncome(zipcursor.getString(6));
	    		    			temp.zipCodeData.setLandArea(zipcursor.getString(7));
	    		    			temp.zipCodeData.setWaterArea(zipcursor.getString(8));
	    		    			temp.zipCodeData.setMilitaryRestrictionCodes(zipcursor.getString(9));
	    		    		
	    		    			temp.locationData.setCity(citycursor.getString(1));
	    		    			temp.locationData.setState(citycursor.getString(2));
	    		    			temp.locationData.setCounty(citycursor.getString(3));
	    		    			temp.locationData.setType(citycursor.getString(4));
	    		    			temp.locationData.setPreferred(citycursor.getString(5));
	    		    			temp.locationData.setWorldregion(citycursor.getString(6));
	    		    			temp.locationData.setCountry(citycursor.getString(7));
	    		    			temp.locationData.setLocationText(citycursor.getString(8));
	    		    			temp.locationData.setLoaction(citycursor.getString(9));

	    		    			intertable.add(temp);
	    		    		}while (citycursor.moveToNext());
    		    		
    		    		}while (refcursor.moveToNext());
    				
    		    	}
    			}

    			
    		}
    		while (zipcursor.moveToNext());
    	}

    	}
    	else
    	{
    		
    		citycursor = db.rawQuery("SELECT * FROM LocationData;", null);	    	
    	if (citycursor != null && citycursor.moveToFirst())
    	{
    		do
    		{
    			String City = citycursor.getString(1);
    			String City_id = citycursor.getString(0);
    			
    			if (City.contains(searchPhrase))
    			{
    				Log.d("City is:", City);
    				//zipcodes.add(ZIP_id);
    				Log.d("Query is:", "SELECT * FROM CrossReference WHERE LocationDataId =" + City_id);
    				refcursor = db.rawQuery("SELECT * FROM CrossReference WHERE LocationDataId =" + City_id , null);
    		    	if (refcursor != null && refcursor.moveToFirst())
    		    	{
    		    		do
    		    		{
    		    		String relatedzip = refcursor.getString(2);
    		    		
    		    		zipcursor = db.rawQuery("SELECT * FROM  ZipCodeData WHERE _id =" + relatedzip , null);
	    		    		do
	    		    		{
	    		    			ZipcodeRow temp = new ZipcodeRow();
	    		    			
	    		    			zipcursor.moveToFirst();
	    		    			
	    		    			
	    		    			temp.zipCodeData.setZipcode(zipcursor.getString(1));
	    		    			temp.zipCodeData.setLatitude(zipcursor.getString(2));
	    		    			temp.zipCodeData.setLongitude(zipcursor.getString(3));
	    		    			temp.zipCodeData.setPopulation(zipcursor.getString(4));
	    		    			temp.zipCodeData.setHousing(zipcursor.getString(5));
	    		    			temp.zipCodeData.setIncome(zipcursor.getString(6));
	    		    			temp.zipCodeData.setLandArea(zipcursor.getString(7));
	    		    			temp.zipCodeData.setWaterArea(zipcursor.getString(8));
	    		    			temp.zipCodeData.setMilitaryRestrictionCodes(zipcursor.getString(9));
	    		    		
	    		    			temp.locationData.setCity(citycursor.getString(1));
	    		    			temp.locationData.setState(citycursor.getString(2));
	    		    			temp.locationData.setCounty(citycursor.getString(3));
	    		    			temp.locationData.setType(citycursor.getString(4));
	    		    			temp.locationData.setPreferred(citycursor.getString(5));
	    		    			temp.locationData.setWorldregion(citycursor.getString(6));
	    		    			temp.locationData.setCountry(citycursor.getString(7));
	    		    			temp.locationData.setLocationText(citycursor.getString(8));
	    		    			temp.locationData.setLoaction(citycursor.getString(9));

	    		    			intertable.add(temp);
	    		    		}while (zipcursor.moveToNext());
    		    		
    		    		}while (refcursor.moveToNext());
    				
    		    	}
    			}
    		}
    		while (citycursor.moveToNext());
			
    	}
		
    	}
		return intertable;
	}
    	


	@Override
	protected void onPostExecute(List<ZipcodeRow> result) {
		// TODO Auto-generated method stub
		
		listener.onSearchComplete(result);


		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		searchPhrase = searchPhrase.toUpperCase();
		super.onPreExecute();
	}


	
}
