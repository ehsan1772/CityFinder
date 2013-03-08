package com.example.cityfinder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class demoinfo extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.demo);
		
		String[] info = getIntent().getExtras().getStringArray("info");
		
		TextView tv1 = (TextView) findViewById(R.id.textView1);
		TextView tv2 = (TextView) findViewById(R.id.textView2);
		TextView tv3 = (TextView) findViewById(R.id.textView3);
		TextView tv4 = (TextView) findViewById(R.id.textView4);
		
		tv1.setText(info[0] + ", " + info[1]);
		tv2.setText("Population : " + info[2]);
		tv3.setText("Housing : " + info[3]);
		tv4.setText("Income : " + info[4]);
		
		
	}
	
	

}
