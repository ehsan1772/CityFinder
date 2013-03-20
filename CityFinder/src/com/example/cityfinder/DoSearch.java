
package com.example.cityfinder;

import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * This class runs all the queries in a background thread and updates the UI after completion
 * @author Ehsan Barekati
 *
 */
public class DoSearch extends AsyncTask<Void, ProgressBar,List<BriefResult>>{
	
	private SQLiteDatabase db;
	private String searchPhrase;
	private OnSearchListener listener;
	private List<BriefResult> intertable;
	private MySearchManager mySearchManager;
	
	public DoSearch(SQLiteDatabase db, String searchPhrase, OnSearchListener listener)
	{		
		this.searchPhrase = searchPhrase;
		this.db = db;
		this.listener = listener;
		mySearchManager = new MySearchManager(listener);
	}

	@Override
	protected List<BriefResult> doInBackground(Void... params) {

    	intertable = mySearchManager.searchByQuery(searchPhrase, db);
		return intertable;
	}
    	


	@Override
	protected void onPostExecute(List<BriefResult> result) {	
		listener.onSearchComplete(result);
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		searchPhrase = searchPhrase.toUpperCase();
		super.onPreExecute();
	}


	
}
