package com.example.cityfinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

public class MainActivity extends Activity implements OnSearchCompleteListener {


	ListView lv;
	boolean searchinprogress;
	ProgressBar pbar;
	ProgressBar pbar2;
	AlertDialog.Builder builder;
	DialogInterface.OnClickListener dialogClickListener;
	int deleteposition;
	TextView tv;
	EditText et;
	DBHelper dbhelper;
	
	
	List<ZipcodeRow> table;
	List<ZipcodeRow> toshow;
	ZipViewAdapter zva;
	String searchphrase;
	DoSearch dosearch;

	protected void onPreSearch() {
		// TODO Auto-generated method stub
		
		searchinprogress = true;	
		pbar2.setVisibility(View.VISIBLE);
		tv.setText("Searching the database ...");
		tv.bringToFront();

	}


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        searchinprogress=true;
    //    dosearch = null;
        ImageButton button = (ImageButton) findViewById(R.id.button1);
        pbar = (ProgressBar) findViewById(R.id.progressBar1);
        pbar2 = (ProgressBar) findViewById(R.id.progressBar2);
        tv = (TextView) findViewById(R.id.textView1);
        et = (EditText)findViewById(R.id.text);
        tv.setVisibility(View.INVISIBLE);
        pbar.setVisibility(View.INVISIBLE);
        pbar2.setVisibility(View.INVISIBLE);
        
        
//        if (savedInstanceState != null)
//        try{
//        searchphrase = savedInstanceState.getString("sphrase");
//
//        
//        Log.d("sf is:", searchphrase);
//        }
//        catch(Throwable e){
//        	Log.e("error", "didn't get catch");
//        }
        

       

        
    	dbhelper = new DBHelper(this, "zipsample.db", null, 1);
    	
    	
//    	Log.d("TEST", "test");
//        try {
//        	 
//        	dbhelper.createDataBase();
// 
// 	} catch (IOException ioe) {
// 
// 		throw new Error("Unable to create database");
// 
// 	}
// 
// 	try {
// 
// 		dbhelper.openDataBase();
// 
// 	}catch(SQLException sqle){
// 
// 		throw sqle;
// 
// 	}

		
       

        lv = (ListView) findViewById(R.id.listView1);
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	   public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
        		 ZipcodeRow map = toshow.get(position);
        		 
        		   
   				Intent intent = new Intent(getApplicationContext(), Citymap.class);
   				intent.putExtra("info", new String[]{map.locationData.getCity(), map.locationData.getState(), map.zipCodeData.getPopulation(), map.zipCodeData.getHousing(), map.zipCodeData.getIncome(), map.zipCodeData.getLatitude(), map.zipCodeData.getLongitude()});

   				startActivity(intent);
        		   
        	   } 
        	
		});
        

     
        
        
        OnClickListener l;
    
        l = new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub

		        	
				if (!et.getText().toString().equals(""))
				{
					Status stat = null;
					try{
						stat = dosearch.getStatus();
					}
					catch(Throwable e)
					{
						
					}
					
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
		};
		button.setOnClickListener(l);
		

    }



	public boolean isOnline() {
		
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
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





}

