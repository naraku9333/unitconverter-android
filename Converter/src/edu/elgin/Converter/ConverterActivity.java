package edu.elgin.Converter;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.res.*;

/**
 * @author Sean Vogel 
 * Sep 19, 2011
 * 
 * Conversion screen activity
 * 
 * Sets UI and data for converter screen
 */
public class ConverterActivity extends Activity implements OnClickListener{
	
	private static final String TAG = "Converter";//DBG
	
	private EditText startValue, resultValue;
	private Button convertButton;
	private Spinner oldVal, newVal;
	private int intOldSpnVal, intNewSpnVal;
	
	//instead of a seperate object per class
	//i'll this as all classes inherit Object AKAIK
	private Object b;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		 Log.d(TAG,"ConverterActivity: onCreate()");//DBG
		 
		 setContentView(R.layout.converter);
		 
		 //get views
		 startValue = (EditText)findViewById(R.id.edtStartValue);
		 resultValue = (EditText)findViewById(R.id.edtResult);
		 
		 convertButton = (Button)findViewById(R.id.btnConvert);
		 convertButton.setOnClickListener(this);
		 
		 newVal = (Spinner) findViewById(R.id.spnTo);
		 oldVal = (Spinner) findViewById(R.id.spnFrom);
		 
		 ArrayAdapter<CharSequence> adapter;
		 
		 //fill ArrayAdapter with data from correct array
		 //and instantiate object
		 switch(UnitList.unit){
		 case 0:
			 setTitle("Base Converter");
			  adapter = ArrayAdapter.createFromResource(this, 
					 R.array.base_array,android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 b = new BaseConversion();
			 break;
			 
		 case 1:
			 setTitle("Temperature Converter");
			  adapter = ArrayAdapter.createFromResource(this, 
					 R.array.temp_array, android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 b = new TempConversion();
			 break;
			 
		 case 2:
			 setTitle("Kitchen Volume Converter");
			 adapter = ArrayAdapter.createFromResource(this, 
					 R.array.kvol_array, android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 b = new KitchenConversion();
			 break;
			
		 case 3:
			 setTitle("distance Converter");
			 adapter = ArrayAdapter.createFromResource(this, 
					 R.array.distance_array, android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 b = new DistanceConversion();
			 break;
			 
		 default:
			 setTitle("Converter");
			  adapter = ArrayAdapter.createFromResource(this, 
					 R.array.empty_array, android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 break;
		 }
		 
		 //attach adapters and listener
		 oldVal.setAdapter(adapter);
		 oldVal.setOnItemSelectedListener(new MyItemSelectedListener());
		 
		 newVal.setAdapter(adapter);
		 newVal.setOnItemSelectedListener(new MyItemSelectedListener());
	}
	
	
	/**
	 *  ConverterActivity onClick
	 */
	@Override
	public void onClick(View view) {
		
		if(view.getId() == R.id.btnConvert){
			double start = Float.valueOf(startValue.getText().toString()), 
	                result = 0d;
			Resources res = getResources();
			switch(UnitList.unit){
			case 0://base
				resultValue.setText(((BaseConversion) b)
						.convert(intOldSpnVal, intNewSpnVal, startValue.getText().toString()));
				break;
			case 1://temp
				//get value from editbox
				

				result = ((TempConversion) b).convert(intOldSpnVal, intNewSpnVal, start);
				
				//format the double to 2 decimals
				DecimalFormat dec = new DecimalFormat("##.00");
				
				resultValue.setText(String.valueOf(Double.valueOf(dec.format(result))));
				break;
			
			case 2:
				String[] kitchenArray= res.getStringArray(R.array.kvol_array);
				result = ((KitchenConversion) b).convert(intOldSpnVal, intNewSpnVal, start);
				result = Math.round(result *100.0)/100.0;
				resultValue.setText(String.valueOf(result) + " " + kitchenArray[intNewSpnVal]);
				break;
			case 3:
				String[] distanceArray = res.getStringArray(R.array.distance_array);
				result = ((DistanceConversion) b).convert(intOldSpnVal, intNewSpnVal, start);
				result = Math.round(result *100.0)/100.0;
				resultValue.setText(String.valueOf(result)+ " " + distanceArray[intNewSpnVal]);
				break;
			default:
				//TODO send error
				break;
			
			}
		}
	}

	/**
	 * ConverterActivity.java
	 * @author Sean Vogel 
	 * Sep 20, 2011
	 * 
	 * custom listener needed for spinner data
	 */
	class MyItemSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int pos, long id) {
			Log.d(TAG,"ConverterActivity: onItemSelected()");//DBG
			
			//set the correct variable depending on which ListItem clicked
			switch(UnitList.unit){
			case 0://Base Converter
				
				if(parent.getId() == R.id.spnFrom)
					intOldSpnVal = pos + 2;
			
				else if(parent.getId() == R.id.spnTo)
					intNewSpnVal = pos + 2;
				
				break;
			case 1://Temperature Converter
			case 2://Kitchen Volume Converter
			case 3://Distance Converter
				if(parent.getId() == R.id.spnFrom)
					intOldSpnVal = pos;
			
				else if(parent.getId() == R.id.spnTo)
					intNewSpnVal = pos;
				break;
			default:
				//TODO send error
				break;
			}
		}
		

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub	
		}
	}
	
	
	
}

