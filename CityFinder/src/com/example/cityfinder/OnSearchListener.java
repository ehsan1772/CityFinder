
package com.example.cityfinder;

import java.util.List;

import android.database.Cursor;

public interface OnSearchListener {
	
	void onSearchComplete(List<BriefResult> data);
	
	void setCursor(Cursor cursor);

}
