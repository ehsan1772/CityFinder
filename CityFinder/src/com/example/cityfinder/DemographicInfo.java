
package com.example.cityfinder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * A simple activity to display demographic information
 * @author Ehsan Barekati
 *
 */
public class DemographicInfo extends Activity{
	
	private TextView cityAndState;
	private TextView population;
	private TextView housing;
	private TextView income;
	private TextView county;
	private TextView landArea;
	private TextView waterArea;
	private TextView coordinate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.demo);
		
		String[] info = getIntent().getExtras().getStringArray("info");
		
		cityAndState = (TextView) findViewById(R.id.cityandstate);
		population = (TextView) findViewById(R.id.population);
		housing = (TextView) findViewById(R.id.housing);
		income = (TextView) findViewById(R.id.income);
		county = (TextView) findViewById(R.id.county);
		landArea = (TextView) findViewById(R.id.landarea);
		waterArea = (TextView) findViewById(R.id.waterarea);
		coordinate  = (TextView) findViewById(R.id.coordinate);
		
		cityAndState.setText(info[0] + ", " + info[1]);
		population.setText("Population : " + info[2]);
		housing.setText("Housing : " + info[3]);
		income.setText("Income : " + info[4]);
		
		county.setText("County : " + info[7]);
		landArea.setText("Land Area : " + info[8]);
		waterArea.setText("Water Area : " + info[9]);
		coordinate.setText(info[5] + ", " + info[6]);
		
		
	}
	
	

}
