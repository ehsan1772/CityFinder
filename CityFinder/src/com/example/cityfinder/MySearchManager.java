
package com.example.cityfinder;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * This class offers different methods to perform queries on the database
 * @author Ehsan Barekati
 *
 */
public class MySearchManager {
	
	private Cursor zipcursor;
	private Cursor refcursor;
	private Cursor citycursor;
	private List<BriefResult> intertable;
	private String query;
	private OnSearchListener onSearchListener;
	
	public MySearchManager(OnSearchListener onSearchListener){
		intertable = new ArrayList<BriefResult>();
		this.onSearchListener = onSearchListener;
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


private String getCityBasedQuery(String searchPhrase){
	String query = " SELECT ZipCodeData.*, LocationData.*, CrossReference.* " +
			"FROM ZipCodeData, LocationData, CrossReference " +
			"WHERE LocationData.City LIKE '%" + searchPhrase + "%' " +
			"AND CrossReference.LocationDataId = LocationData._id " +
			"AND CrossReference.ZipCodeId = ZipCodeData._id;";
	
	return query;
}

private String getZipBasedQuery(String searchPhrase){
	String query = " SELECT ZipCodeData.*, LocationData.*, CrossReference.* " +
			"FROM ZipCodeData, LocationData, CrossReference " +
			"WHERE ZipCodeData.ZipCode LIKE '%" + searchPhrase + "%' " +
			"AND CrossReference.LocationDataId = LocationData._id " +
			"AND CrossReference.ZipCodeId = ZipCodeData._id;";
	
	return query;
}

public List<BriefResult> searchByQuery(String searchPhrase, SQLiteDatabase database){
	
	intertable.clear();

	if(isInteger(searchPhrase))
		query = getZipBasedQuery(searchPhrase);
	else
		query = getCityBasedQuery(searchPhrase);

	Log.d("Task : ", "Before query");
	zipcursor = database.rawQuery(query, null);
	onSearchListener.setCursor(zipcursor);
	Log.d("Task : ", "After query, before convertion");
	intertable = cursorToList(zipcursor);
	Log.d("Task : ", "After convertion");
	return intertable;
}

private List<BriefResult> cursorToList(Cursor cursor){
	
	if(cursor == null || cursor.getCount() == 0)
		return null;
	
	cursor.moveToFirst();
	List<BriefResult> result = new ArrayList<BriefResult>();
	
	do
	{
		BriefResult temp = new BriefResult();
		
		temp.setCity(cursor.getString(11));
		temp.setState(cursor.getString(12));
		temp.setZip(cursor.getString(1));
		temp.setRowPosition(cursor.getPosition());
		
		result.add(temp);
	}while (cursor.moveToNext());
	
	return result;
}

//private List<ZipcodeRow> cursorToList(Cursor cursor){
//	
//	if(cursor == null || cursor.getCount() == 0)
//		return null;
//	
//	cursor.moveToFirst();
//	List<ZipcodeRow> result = new ArrayList<ZipcodeRow>();
//	
//	do
//	{
//		ZipcodeRow temp = new ZipcodeRow();
//		
//
//		temp.zipCodeData.setZipcode(cursor.getString(1));
//		temp.zipCodeData.setLatitude(cursor.getString(2));
//		temp.zipCodeData.setLongitude(cursor.getString(3));
//		temp.zipCodeData.setPopulation(cursor.getString(4));
//		temp.zipCodeData.setHousing(cursor.getString(5));
//		temp.zipCodeData.setIncome(cursor.getString(6));
//		temp.zipCodeData.setLandArea(cursor.getString(7));
//		temp.zipCodeData.setWaterArea(cursor.getString(8));
//		temp.zipCodeData.setMilitaryRestrictionCodes(cursor.getString(9));
//	
//		temp.locationData.setCity(cursor.getString(11));
//		temp.locationData.setState(cursor.getString(12));
//		temp.locationData.setCounty(cursor.getString(13));
//		temp.locationData.setType(cursor.getString(14));
//		temp.locationData.setPreferred(cursor.getString(15));
//		temp.locationData.setWorldregion(cursor.getString(16));
//		temp.locationData.setCountry(cursor.getString(17));
//		temp.locationData.setLocationText(cursor.getString(18));
//		temp.locationData.setLoaction(cursor.getString(19));
//
//		result.add(temp);
//	}while (cursor.moveToNext());
//	
//	return result;
//}

}
