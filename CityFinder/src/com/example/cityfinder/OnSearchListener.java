
package com.example.cityfinder;

import java.util.List;

import android.database.Cursor;

/**
 * The main activity implements this interface to communicate with the search events
 * @author Ehsan Barekati
 *
 */
public interface OnSearchListener {
	
	void onSearchComplete(List<BriefResult> data);
	void setCursor(Cursor cursor);

}
