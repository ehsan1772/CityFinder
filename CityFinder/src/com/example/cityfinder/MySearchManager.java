
package com.example.cityfinder;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MySearchManager {
	
	private Cursor zipcursor;
	private Cursor refcursor;
	private Cursor citycursor;
	private ArrayList<ZipcodeRow> intertable;
	
	public MySearchManager(){
		intertable = new ArrayList<ZipcodeRow>();
	}
	
	/**
	 * Checks if the input is an integer
	 * @param input 
	 * @return true if it is an integer
	 */
public boolean isInteger(String input){
	try {
	Integer.valueOf(input);
	} catch(NumberFormatException exeption)	{
		return false;
	}
	return true;
}

/**
 * Searches tha database based on the zip code
 * @param searchPhrase The zip code that should be searched
 * @param database The database
 * @return The found rows
 */
public List<ZipcodeRow> searchByZip(String searchPhrase, SQLiteDatabase database){
	intertable.clear();
	zipcursor = database.rawQuery("SELECT * FROM ZipCodeData;", null);	    	
if (zipcursor != null && zipcursor.moveToFirst())
{
	do
	{
		String Zipcode = zipcursor.getString(1);
		String ZIP_id = zipcursor.getString(0);
		
		if (Zipcode.contains(searchPhrase))
		{

			refcursor = database.rawQuery("SELECT * FROM CrossReference WHERE ZipCodeId =" + String.valueOf(ZIP_id) , null);
	    	if (refcursor != null && refcursor.moveToFirst())
	    	{
	    		do
	    		{
	    		String relatedcity = refcursor.getString(1);
	    		citycursor = database.rawQuery("SELECT * FROM  LocationData WHERE _id =" + relatedcity , null);
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
	
	return intertable;
}

public List<ZipcodeRow> searchByLocation(String searchPhrase, SQLiteDatabase database){
	
	intertable.clear();
	citycursor = database.rawQuery("SELECT * FROM LocationData;", null);	    	
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
				refcursor = database.rawQuery("SELECT * FROM CrossReference WHERE LocationDataId =" + City_id , null);
		    	if (refcursor != null && refcursor.moveToFirst())
		    	{
		    		do
		    		{
		    		String relatedzip = refcursor.getString(2);
		    		
		    		zipcursor = database.rawQuery("SELECT * FROM  ZipCodeData WHERE _id =" + relatedzip , null);
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
	return intertable;
}

}
