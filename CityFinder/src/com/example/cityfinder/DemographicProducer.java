package com.example.cityfinder;

import android.database.Cursor;

public class DemographicProducer {
	public static ZipcodeRow getDemographic(Cursor cursor, int position){
		
			cursor.moveToPosition(position);
			ZipcodeRow temp = new ZipcodeRow();
			
	
			temp.zipCodeData.setZipcode(cursor.getString(1));
			temp.zipCodeData.setLatitude(cursor.getString(2));
			temp.zipCodeData.setLongitude(cursor.getString(3));
			temp.zipCodeData.setPopulation(cursor.getString(4));
			temp.zipCodeData.setHousing(cursor.getString(5));
			temp.zipCodeData.setIncome(cursor.getString(6));
			temp.zipCodeData.setLandArea(cursor.getString(7));
			temp.zipCodeData.setWaterArea(cursor.getString(8));
			temp.zipCodeData.setMilitaryRestrictionCodes(cursor.getString(9));
		
			temp.locationData.setCity(cursor.getString(11));
			temp.locationData.setState(cursor.getString(12));
			temp.locationData.setCounty(cursor.getString(13));
			temp.locationData.setType(cursor.getString(14));
			temp.locationData.setPreferred(cursor.getString(15));
			temp.locationData.setWorldregion(cursor.getString(16));
			temp.locationData.setCountry(cursor.getString(17));
			temp.locationData.setLocationText(cursor.getString(18));
			temp.locationData.setLoaction(cursor.getString(19));
	
			return temp;
	
	}
}
