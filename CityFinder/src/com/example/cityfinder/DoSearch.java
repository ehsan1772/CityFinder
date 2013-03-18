
package com.example.cityfinder;

import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ProgressBar;


public class DoSearch extends AsyncTask<Void, ProgressBar,List<ZipcodeRow>>{
	
	private SQLiteDatabase db;
	private String searchPhrase;
	private OnSearchCompleteListener listener;
	private List<ZipcodeRow> intertable;
	private MySearchManager mySearchManager;
	
	public DoSearch(SQLiteDatabase db, String searchPhrase, OnSearchCompleteListener listener)
	{		
		this.searchPhrase = searchPhrase;
		this.db = db;
		this.listener = listener;
		mySearchManager = new MySearchManager();
	}

	@Override
	protected List<ZipcodeRow> doInBackground(Void... params) {
    	if (mySearchManager.isInteger(searchPhrase)) 
    		intertable = mySearchManager.searchByZip(searchPhrase, db);
    	 else 
    		intertable = mySearchManager.searchByLocation(searchPhrase, db);
    	
		return intertable;
	}
    	


	@Override
	protected void onPostExecute(List<ZipcodeRow> result) {	
		listener.onSearchComplete(result);
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		searchPhrase = searchPhrase.toUpperCase();
		super.onPreExecute();
	}


	
}
