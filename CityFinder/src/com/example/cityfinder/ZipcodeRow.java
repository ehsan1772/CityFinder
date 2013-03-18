
package com.example.cityfinder;

public class ZipcodeRow {
	
	public ZipCodeData zipCodeData;
	public LocationData locationData;
	
	public ZipcodeRow()
	{
		zipCodeData = new ZipCodeData();
		locationData = new LocationData();
	}
	
	public String dropQuotationMarks(String instring)
	{
		String outstring = instring.replace('"', ' ');
		return outstring;
	}
	
	public class ZipCodeData
	{
		public String zipcode;
		private String latitude;
		private String longitude;
		private String population;
		private String housing;
		private String income;
		private String landArea;
		private String waterArea;
		private String militaryRestrictionCodes;
		private int id;
		
		public String getZipcode()
		{
			return dropQuotationMarks(zipcode);
		}
		public void setZipcode(String value)
		{
			zipcode = value;
		}
		
		public int get_id()
		{
			return id;
		}
		public void set_id(int value)
		{
			id = value;
		}
		
		public String getLatitude()
		{
			return dropQuotationMarks(latitude);
		}
		public void setLatitude(String value)
		{
			latitude = value;
		}
		
		public String getLongitude()
		{
			return dropQuotationMarks(longitude);
		}
		public void setLongitude(String value)
		{
			longitude = value;
		}
		
		public String getPopulation()
		{
			return dropQuotationMarks(population);
		}
		public void setPopulation(String value)
		{
			population = value;
		}
		
		public String getHousing()
		{
			return dropQuotationMarks(housing);
		}
		public void setHousing(String value)
		{
			housing = value;
		}
		
		
		public String getIncome()
		{
			return dropQuotationMarks(income);
		}
		public void setIncome(String value)
		{
			income= value;
		}
		
		public String getLandArea()
		{
			return dropQuotationMarks(landArea);
		}
		public void setLandArea(String value)
		{
			landArea = value;
		}
		
		public String getWaterArea()
		{
			return dropQuotationMarks(waterArea);
		}
		public void setWaterArea(String value)
		{
			waterArea = value;
		}
		
		
		public String getMilitaryRestrictionCodes()
		{
			return dropQuotationMarks(militaryRestrictionCodes);
		}
		public void setMilitaryRestrictionCodes(String value)
		{
			militaryRestrictionCodes = value;
		}
		
		
		
	}
	public class LocationData
	{
		private String city;
		private String county;
		private String state;
		private String type;
		private String preferred;
		private String worldregion;
		private String country;
		private String locationText;
		private String loaction;
		private int id;
		
		public int get_id()
		{
			return id;
		}
		public void set_id(int value)
		{
			id = value;
		}
		public String getCity()
		{
			return dropQuotationMarks(city);
		}
		public void setCity(String value)
		{
			city= value;
		}
		
		public String getCounty()
		{
			return dropQuotationMarks(county);
		}
		public void setCounty(String value)
		{
			county = value;
		}
		
		public String getState()
		{
			return dropQuotationMarks(state);
		}
		public void setState(String value)
		{
			state = value;
		}
		
		public String getType()
		{
			return dropQuotationMarks(type);
		}
		public void setType(String value)
		{
			type = value;
		}
		
		public String getPreferred()
		{
			return dropQuotationMarks(preferred);
		}
		public void setPreferred(String value)
		{
			preferred= value;
		}
		
		public String getWorldregion()
		{
			return dropQuotationMarks(worldregion);
		}
		public void setWorldregion(String value)
		{
			worldregion = value;
		}
		
		public String getCountry()
		{
			return dropQuotationMarks(country);
		}
		public void setCountry(String value)
		{
			country = value;
		}
		
		public String getLocationText()
		{
			return dropQuotationMarks(locationText);
		}
		public void setLocationText(String value)
		{
			locationText = value;
		}
		
		public String getLoaction()
		{
			return dropQuotationMarks(loaction);
		}
		public void setLoaction(String value)
		{
			loaction = value;
		}
	}
	
	

}
