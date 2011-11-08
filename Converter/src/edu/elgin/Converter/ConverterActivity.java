package edu.elgin.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author Sean Vogel 
 * Sep 19, 2011
 * 
 * Conversion screen activity
 * 
 * Sets UI and data for converter screen
 * 
 * References:
 * 1: http://coderzheaven.com/2011/07/regex-validation-for-only-removing-invalid-characters-in-android/
 * 2: http://stackoverflow.com/questions/2586301/set-inputtype-for-an-edittext
 * 
 */
public class ConverterActivity extends Activity implements OnClickListener{
	
	private static final String TAG = "Converter";//DBG
	
	
	private EditText startValue, resultValue;
	private Button convertButton;
	private Spinner oldVal, newVal;
	private int intOldSpnVal, intNewSpnVal;
	private int unit = 0;
	
	
	
	//instead of a seperate object per class
	//i'll use this since all classes inherit Object AKAIK
	private Object b;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		 Log.d(TAG,"ConverterActivity: onCreate()");//DBG
		 
		 
		 setContentView(R.layout.converter);
		 
		 //get listview item index from intent
		 Bundle extras = getIntent().getExtras();
		 if(extras != null)
			 unit = extras.getInt("unit");
		 
		 //get views by there id's
		 startValue = (EditText)findViewById(R.id.edtStartValue);
		 resultValue = (EditText)findViewById(R.id.edtResult);
		 
		 convertButton = (Button)findViewById(R.id.btnConvert);
		 convertButton.setOnClickListener(this);
		 
		 newVal = (Spinner) findViewById(R.id.spnTo);
		 oldVal = (Spinner) findViewById(R.id.spnFrom);
		 
		 //fill ArrayAdapter with data from correct array
		 ArrayAdapter<CharSequence> adapter;
		 
		 /**
		  * For each case, set window title, add array data to spinner,
		  * set input type (restrict to numbers for all but base)
		  * and instantiate object to specific conversion class
		  */
		 switch(unit){
		 case 0:
			 setTitle("Base Converter");
			  adapter = ArrayAdapter.createFromResource(this, 
					 R.array.base_array,android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 startValue.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
			 b = new BaseConversion();
			 break;
			 
		 case 1:
			 setTitle("Temperature Converter");
			  adapter = ArrayAdapter.createFromResource(this, 
					 R.array.temp_array, android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 startValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
			 b = new TempConversion();
			 break;
			 
		 case 2:
			 setTitle("Kitchen Volume Converter");
			 adapter = ArrayAdapter.createFromResource(this, 
					 R.array.kvol_array, android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 startValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
			 b = new KitchenConversion();
			 break;
			
		 case 3:
			 setTitle("distance Converter");
			 adapter = ArrayAdapter.createFromResource(this, 
					 R.array.distance_array, android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 startValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
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
	 * Create menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuInflater mInflater = getMenuInflater();
		mInflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	/**
	 * menu item selected
	 */
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		
		case R.id.settings:
			startActivity(new Intent(this, Settings.class));
			return true;
			
		case R.id.about_box:
			startActivity(new Intent(this, About.class));
			return true;
		}
		return false;
	}
	
	/**
	 *  ConverterActivity onClick
	 *  
	 *  input is validated by only allowing numerals for all conversions
	 *  except base, where validation is done with a regular expression check
	 */
	@Override
	public void onClick(View view) {
		//Retire shared preferences and set time for vibrate
		int vibrate_time = 1000;
		
		if(view.getId() == R.id.btnConvert){
			double start = 0d, 
	                result = 0d;
			Resources res = getResources();
			// Get instance of Vibrator from current Context
			Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			
			
			switch(unit){
			case 0://Base
				if(validate(startValue.getText().toString()) == true){

					String[] baseArray= res.getStringArray(R.array.base_array);
					resultValue.setText(((BaseConversion) b)
							.convert(intOldSpnVal, intNewSpnVal, startValue.getText().toString()) + baseArray[intNewSpnVal-2]);
				}
				else{
					Toast.makeText(getApplicationContext(),
							"Enter only numbers 0 - 9,\n and/or letters A -F", Toast.LENGTH_SHORT).show();
				}
				if(Settings.getVibrate(getBaseContext())){
					v.vibrate(Settings.getVibrateInterval(getBaseContext()));
				}
				break;
			case 1://Temp
				//Get value from editbox
				start = Double.valueOf(startValue.getText().toString());
				String[] tempArray= res.getStringArray(R.array.temp_array);
				result = ((TempConversion) b).convert(intOldSpnVal, intNewSpnVal, start);
				resultValue.setText(String.valueOf(Math.round(result * 100.00)/100.00) + tempArray[intNewSpnVal]);
				if(Settings.getVibrate(getBaseContext())){
					v.vibrate(Settings.getVibrateInterval(getBaseContext()));
				}
				break;
			
			case 2://Kitchen
				start = Double.valueOf(startValue.getText().toString());
				String[] kitchenArray= res.getStringArray(R.array.kvol_array);
				result = ((KitchenConversion) b).convert(intOldSpnVal, intNewSpnVal, start);
				result = Math.round(result *100.0)/100.0;
				resultValue.setText(String.valueOf(result) + kitchenArray[intNewSpnVal]);
				if(Settings.getVibrate(getBaseContext())){
					v.vibrate(Settings.getVibrateInterval(getBaseContext()));
				if(Settings.getSound(getBaseContext())){
					SoundPlayer.play(this, R.raw.answer);
				}
				}
				break;
			case 3://Distance
				start = Double.valueOf(startValue.getText().toString());
				String[] distanceArray = res.getStringArray(R.array.distance_array);
				result = ((DistanceConversion) b).convert(intOldSpnVal, intNewSpnVal, start);
				result = Math.round(result *100.0)/100.0;
				resultValue.setText(String.valueOf(result) + distanceArray[intNewSpnVal]);
				if(Settings.getVibrate(getBaseContext())){
					v.vibrate(Settings.getVibrateInterval(getBaseContext()));
				}
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
			switch(unit){
			case 0://Base Converter
				//add 2 to pos so spinner val is base
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
	
	//validate input for base conversion
	//allow only numerals and chars a - f
	//see reference 1
	private boolean validate(String str)
    {
        String regex = "^[a-f_A-F0-9]*$";
       
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches())
        	return true;
        else
        	return false;
     }
}

