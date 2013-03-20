
package com.example.cityfinder;

import java.util.List;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 
 * This class is the main activity of the app. 
 * It sets up the UI and implements the required interfaces to communicate with other classes 
 * @author Ehsan Barekati
 *
 */
public class MainActivity extends Activity implements OnSearchListener, MyListViewOwner, OnClickListener {


	private MyListView listView;
	private ProgressBar progressBar;
	private ImageButton button;
	private EditText editText;
	private MyDatabaseHelper myDatabaseHelper;
	private List<ZipcodeRow> table;
	private List<BriefResult> toshow;
	private ZipViewAdapter zipViewAdapter;
	private String searchphrase;
	private DoSearch dosearch;
	private Cursor cursor;

	/**
	 * Sets up the UI for the search mode
	 */
	protected void onPreSearch() {	
		progressBar.setVisibility(View.VISIBLE);
		button.setVisibility(View.INVISIBLE);
	}


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button = (ImageButton) findViewById(R.id.button1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        editText = (EditText)findViewById(R.id.text);
        listView = (MyListView) findViewById(R.id.listView1);
        
        progressBar.setVisibility(View.INVISIBLE);
        
    	myDatabaseHelper = new MyDatabaseHelper(this, "zipsample.db", null, 1);
    	
    	listView.setTheOwner(this);     
		button.setOnClickListener(this);
		
    }  

   /**
    * This is a listener method that gets invoked by the DoSearch instance upon the completion of the search
    */
		public void onSearchComplete(List<BriefResult> data) {
			toshow = data;
	        zipViewAdapter = new ZipViewAdapter(getBaseContext(), R.layout.zipcodeview, toshow);
	        listView.setAdapter(zipViewAdapter);
			progressBar.setVisibility(View.INVISIBLE);
			button.setVisibility(View.VISIBLE);
		}

/**
 * returns the objects that the clicked view on the ListView represents
 */
		public Object getClickedItem(int position) {
			BriefResult clickedObject = toshow.get(position);
			return clickedObject;
		}

/**
 * The onClick listener that checks if everything is ready to initiate the search and then initiates it 
 */
		public void onClick(View v) {
			if (!editText.getText().toString().equals(""))
			{
				Status stat = null;
				if (dosearch!=null)
				stat = dosearch.getStatus();
				
				if (stat == null || dosearch.getStatus() == Status.FINISHED)
				{
				searchphrase = editText.getText().toString();
	        	dosearch = new DoSearch(myDatabaseHelper.getReadableDatabase(), searchphrase, MainActivity.this);
	        	onPreSearch();
	        	dosearch.execute(new Void[]{});
				}
				else{
					Toast toast = Toast.makeText(getBaseContext(), "The current querry is not finished", Toast.LENGTH_LONG);
					toast.show();
				}
	        
			}
			else{
				Toast toast = Toast.makeText(getBaseContext(), "Please enter a search phrase", Toast.LENGTH_LONG);
				toast.show();
			}
			
		}

/**
 * It sets the reference variable to the Cursor instance that was required during the search
 */
		public void setCursor(Cursor cursor) {
			this.cursor = cursor;
		}

/**
 * return the Cursor instance
 */
		public Cursor getCursor() {
			return cursor;
		}


}

