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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnSearchCompleteListener, MyListViewOwner, OnClickListener {


	private MyListView lv;
	private boolean searchinprogress;
	private ProgressBar pbar;
	private ProgressBar pbar2;
	//AlertDialog.Builder builder;
	//DialogInterface.OnClickListener dialogClickListener;
	//int deleteposition;
	private TextView tv;
	private EditText et;
	private DBHelper dbhelper;
	
	
	private List<ZipcodeRow> table;
	private List<ZipcodeRow> toshow;
	private ZipViewAdapter zva;
	private String searchphrase;
	private DoSearch dosearch;

	protected void onPreSearch() {	
		searchinprogress = true;	
		pbar2.setVisibility(View.VISIBLE);
		tv.setText("Searching the database ...");
		tv.bringToFront();

	}


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageButton button = (ImageButton) findViewById(R.id.button1);
        pbar = (ProgressBar) findViewById(R.id.progressBar1);
        pbar2 = (ProgressBar) findViewById(R.id.progressBar2);
        tv = (TextView) findViewById(R.id.textView1);
        et = (EditText)findViewById(R.id.text);
        lv = (MyListView) findViewById(R.id.listView1);
        
        tv.setVisibility(View.INVISIBLE);
        pbar.setVisibility(View.INVISIBLE);
        pbar2.setVisibility(View.INVISIBLE);
        
        searchinprogress=true;
    	dbhelper = new DBHelper(this, "zipsample.db", null, 1);
    	
    	lv.setTheOwner(this);     
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
    

		public void onSearchComplete(List<ZipcodeRow> data) {
			// TODO Auto-generated method stub
			toshow = data;
			searchinprogress = false;
	        zva = new ZipViewAdapter(getBaseContext(), R.layout.zipcodeview, toshow);
	        lv.setAdapter(zva);
	        tv.setText("");
			pbar2.setVisibility(View.INVISIBLE);
		}


		public Object getClickedItem(int position) {
   		 ZipcodeRow map = toshow.get(position);
			return map;
		}


		public void onClick(View v) {
			if (!et.getText().toString().equals(""))
			{
				Status stat = null;
				stat = dosearch.getStatus();
				
				if (stat == null || dosearch.getStatus() == Status.FINISHED)
				{
				tv.setVisibility(View.VISIBLE);
				searchphrase = et.getText().toString();
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





}

