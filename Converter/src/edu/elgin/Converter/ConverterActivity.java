package edu.elgin.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
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
 * 3: http://www.kaloer.com/android-preferences
 * 4: http://stackoverflow.com/questions/1397361/how-do-i-restart-an-android-activity
 * 
 * TODO change input type of temp, need to allow neg values
 * TODO	fix grey theme
 * TODO	add more themes
 * TODO	change theme list preference to a dialog with custom color buttons
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
	private Object obj;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		 Log.d(TAG,"ConverterActivity: onCreate()");//DBG
		 
		 //get the theme to set
		 switch(Settings.getCustomTheme(getBaseContext())){
		 case 0:
			 setTheme(R.style.grey);
			 break;
		 case 1:
			 setTheme(android.R.style.Theme_Black);
			 break;
		 case 2:
			 setTheme(R.style.blue);
			 break;
		 case 3:
			 setTheme(R.style.red);
			 break;
		 case 4:
			 setTheme(R.style.plum);
			 break;
		 case 5:
			 setTheme(android.R.style.Theme_Light);
			 break;
		 }
		 
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.converter);
		 
		 //get listview selected item index from intent
		 //for selected conversion
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
					 R.array.base_array,R.layout.my_spinner_item);
			  
			 adapter.setDropDownViewResource(R.layout.my_spinner_dropdown_item);
			 
			 startValue.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
			 
			 obj = new BaseConversion();
			 break;
			 
		 case 1:
			 setTitle("Temperature Converter");
			  adapter = ArrayAdapter.createFromResource(this, 
					 R.array.temp_array, R.layout.my_spinner_item);
			  
			 adapter.setDropDownViewResource(R.layout.my_spinner_dropdown_item);
			 
			 startValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
			 
			 obj = new TempConversion();
			 break;
			 
		 case 2:
			 setTitle("Kitchen Volume Converter");
			 adapter = ArrayAdapter.createFromResource(this, 
					 R.array.kvol_array, R.layout.my_spinner_item);
			 
			 adapter.setDropDownViewResource(R.layout.my_spinner_dropdown_item);
			 
			 startValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
			 
			 obj = new KitchenConversion();
			 break;
			
		 case 3:
			 setTitle("Distance Converter");
			 adapter = ArrayAdapter.createFromResource(this, 
					 R.array.distance_array, R.layout.my_spinner_item);
			 
			 adapter.setDropDownViewResource(R.layout.my_spinner_dropdown_item);
			 
			 startValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
			 
			 obj = new DistanceConversion();
			 break;
			 
		 case 4:
			 setTitle("Currency Converter");
			 adapter = ArrayAdapter.createFromResource(this, 
					 R.array.currency_array, R.layout.my_spinner_item);
			 
			 adapter.setDropDownViewResource(R.layout.my_spinner_dropdown_item);
			 
			 startValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
			 
			 obj = new CurrencyConversion(this);
			 break;
		 default:
			 setTitle("Converter");
			  adapter = ArrayAdapter.createFromResource(this, 
					 0, R.layout.my_spinner_item);
			 adapter.setDropDownViewResource(R.layout.my_spinner_dropdown_item);
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
	 * Menu code referenced from book "Hello Android"
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
			
		if(view.getId() == R.id.btnConvert){
			double start = 0d, 
	                result = 0d;
			//resource to fill arrays
			Resources res = getResources();
			
			// Get instance of Vibrator from current Context
			Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			
			//used for displaying correct num of decimals
			double r = Settings.getDigits(getBaseContext());
			
			switch(unit){
			case 0://Base
				if(validateAlphaNum(startValue.getText().toString()) == true){

					String[] baseArray= res.getStringArray(R.array.base_array);
					
					//cast object and call specific converter
					resultValue.setText(((BaseConversion) obj)
							.convert(intOldSpnVal, intNewSpnVal, startValue.getText()
									.toString()) + baseArray[intNewSpnVal-2]);
				}
				else{//show message if invalid char
					Toast.makeText(getApplicationContext(),
							"Enter only numbers 0 - 9,\n and/or letters A -F", Toast.LENGTH_SHORT).show();
				}
				if(Settings.getVibrate(getBaseContext())){
					v.vibrate(Settings.getVibrateInterval(getBaseContext()));
				}
				if(Settings.getSound(getBaseContext())){
					SoundPlayer.play(this, R.raw.answer);
				}
				break;
			case 1://Temp
				//Get value from editbox
				start = Double.valueOf(startValue.getText().toString());
				
				String[] tempArray= res.getStringArray(R.array.temp_array);
				
				result = ((TempConversion) obj).convert(intOldSpnVal, intNewSpnVal, start);
				
				resultValue.setText(String.valueOf(Math.round(result * r)/r) + tempArray[intNewSpnVal]);
				
				if(Settings.getVibrate(getBaseContext())){
					v.vibrate(Settings.getVibrateInterval(getBaseContext()));
				}
				if(Settings.getSound(getBaseContext())){
					SoundPlayer.play(this, R.raw.answer);
				}
				break;
			
			case 2://Kitchen
				start = Double.valueOf(startValue.getText().toString());
				
				String[] kitchenArray= res.getStringArray(R.array.kvol_array);
				
				result = ((KitchenConversion) obj).convert(intOldSpnVal, intNewSpnVal, start);
				
				result = Math.round(result *r)/r;
				
				resultValue.setText(String.valueOf(result) + kitchenArray[intNewSpnVal]);
				
				if(Settings.getVibrate(getBaseContext())){
					v.vibrate(Settings.getVibrateInterval(getBaseContext()));
				}
				if(Settings.getSound(getBaseContext())){
					SoundPlayer.play(this, R.raw.answer);
				}
				break;
			case 3://Distance
				start = Double.valueOf(startValue.getText().toString());
				
				String[] distanceArray = res.getStringArray(R.array.distance_array);
				
				result = ((DistanceConversion) obj).convert(intOldSpnVal, intNewSpnVal, start);
				
				result = Math.round(result *r)/r;
				
				resultValue.setText(String.valueOf(result) + distanceArray[intNewSpnVal]);
							
				if(Settings.getVibrate(getBaseContext())){
					v.vibrate(Settings.getVibrateInterval(getBaseContext()));
				}
				if(Settings.getSound(getBaseContext())){
					SoundPlayer.play(this, R.raw.answer);
				}
				break;
			case 4://Currency
				String[] currencyArray = res.getStringArray(R.array.currency_array);
				DataList datalist = XMLHandler.getDataList();
				//cast object and call specific converter
				//round value and set text
				result = Math.round(
						((CurrencyConversion) obj).convert(intOldSpnVal, intNewSpnVal,
						Double.valueOf(startValue.getText().toString())) * r)/r;
				//work around for USD
				int index = (intNewSpnVal > 57)?intNewSpnVal:intNewSpnVal+1;
				resultValue.setText(String.valueOf(result)
						 + " " + datalist.getDescription().get(index));
			
				if(Settings.getVibrate(getBaseContext())){
					v.vibrate(Settings.getVibrateInterval(getBaseContext()));
				}
				if(Settings.getSound(getBaseContext())){
					SoundPlayer.play(this, R.raw.answer);
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
				//spinner index + 2 is the base
				if(parent.getId() == R.id.spnFrom)
					intOldSpnVal = pos + 2;
			
				else if(parent.getId() == R.id.spnTo)
					intNewSpnVal = pos + 2;
				
				break;
			case 1://Temperature Converter
			case 2://Kitchen Volume Converter
			case 3://Distance Converter
			case 4://Currency Converter
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
	private boolean validateAlphaNum(String str)
    {
        String regex = "^[a-f_A-F0-9]*$";
       
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches())
        	return true;
        else
        	return false;
     }

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		//check if this activity should be "refreshed"
		//stop and start activity if true
		if(Settings.getCRefresh()){
			Settings.setCRefresh(false);
			Intent intent = getIntent();
	    	finish();
	    	startActivity(intent);
		}
		super.onResume();
	}
}

