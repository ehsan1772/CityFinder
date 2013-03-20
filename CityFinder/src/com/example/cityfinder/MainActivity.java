
package com.example.cityfinder;

import java.util.ArrayList;
import java.util.List;



import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnSearchListener, MyListViewOwner, OnClickListener {


	private MyListView listView;
//	private ProgressBar pbar;
	private ProgressBar pbar2;
	private TextView textView;
	private ImageButton button;
	private EditText editText;
	
//	private boolean searchinprogress;
	private DBHelper dbhelper;
	private List<ZipcodeRow> table;
	private List<BriefResult> toshow;
	private ZipViewAdapter zipViewAdapter;
	private String searchphrase;
	private DoSearch dosearch;
	private Cursor cursor;

	protected void onPreSearch() {	
//		searchinprogress = true;	
		pbar2.setVisibility(View.VISIBLE);
		textView.setText("Searching the database ...");
		textView.bringToFront();

	}


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button = (ImageButton) findViewById(R.id.button1);
 //       pbar = (ProgressBar) findViewById(R.id.progressBar1);
        pbar2 = (ProgressBar) findViewById(R.id.progressBar2);
        textView = (TextView) findViewById(R.id.textView1);
        editText = (EditText)findViewById(R.id.text);
        listView = (MyListView) findViewById(R.id.listView1);
        
        textView.setVisibility(View.INVISIBLE);
//        pbar.setVisibility(View.INVISIBLE);
        pbar2.setVisibility(View.INVISIBLE);
        
 //       searchinprogress=true;
    	dbhelper = new DBHelper(this, "zipsample.db", null, 1);
    	
    	listView.setTheOwner(this);     
		button.setOnClickListener(this);
		
    }  


    public List<ZipcodeRow> dbquery(String serach)
    {
    	List<ZipcodeRow> result = new ArrayList<ZipcodeRow>();
    	for(ZipcodeRow zcr : table)
    	{
    		if (zcr.locationData.getCity().contains(serach))
    			result.add(zcr);
    		else if(zcr.zipCodeData.getZipcode().contains(serach))
    			result.add(zcr);
    	}

		return result;
    }
    

		public void onSearchComplete(List<BriefResult> data) {
			// TODO Auto-generated method stub
			toshow = data;
	//		searchinprogress = false;
			Log.d("Task : ", "Before Adapter");
	        zipViewAdapter = new ZipViewAdapter(getBaseContext(), R.layout.zipcodeview, toshow);
	        listView.setAdapter(zipViewAdapter);
	    	Log.d("Task : ", "After Adapter");
	        textView.setText("");
			pbar2.setVisibility(View.INVISIBLE);
		}


		public Object getClickedItem(int position) {
			BriefResult map = toshow.get(position);
			return map;
		}


		public void onClick(View v) {
			if (!editText.getText().toString().equals(""))
			{
				Status stat = null;
				if (dosearch!=null)
				stat = dosearch.getStatus();
				
				if (stat == null || dosearch.getStatus() == Status.FINISHED)
				{
				textView.setVisibility(View.VISIBLE);
				searchphrase = editText.getText().toString();
	        	dosearch = new DoSearch(dbhelper.getReadableDatabase(), searchphrase, MainActivity.this);
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


		public void setCursor(Cursor cursor) {
			this.cursor = cursor;
		}


		public Cursor getCursor() {
			return cursor;
		}





}

