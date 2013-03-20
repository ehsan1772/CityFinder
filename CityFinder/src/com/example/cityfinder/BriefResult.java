package com.example.cityfinder;

/**
 * This class represents the objects that are shown as the result of the search
 * @author Ehsan Barekati
 *
 */
public class BriefResult {
	
	private String zip;
	private String city;
	private String state;
	private int rowPosition;
	
	public void setZip(String zip){
		this.zip = zip;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public void setRowPosition(int position){
		this.rowPosition = position;
	}
	
	
	public String getZip(){
		return zip;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getState(){
		return state;
	}
	
	public int getRowPosition(){
		return rowPosition;
	}

}
