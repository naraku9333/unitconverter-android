package edu.elgin.Converter;

import java.math.BigInteger;
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
import edu.elgin.Converter.BaseConversion;
import edu.elgin.Converter.TempConversion;

/**
 * @author Sean Vogel 
 * Sep 19, 2011
 * 
 * Conversion screen activity
 */
public class ConverterActivity extends Activity implements OnClickListener{
	
	private static final String TAG = "Converter";//DBG
	
	private EditText startValue, resultValue;
	private Button convertButton;
	private Spinner oldVal, newVal;
	private int intOldVal, intNewVal;
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
		 switch(UnitList.unit){
		 case 0:
			 setTitle("Base Converter");
			  adapter = ArrayAdapter.createFromResource(this, 
					 R.array.base_array, android.R.layout.simple_spinner_item);
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
	 *  
	 */
	@Override
	public void onClick(View view) {
		
		if(view.getId() == R.id.btnConvert){
			switch(UnitList.unit){
			case 0:
				baseConvert();
				break;
			case 1:
				//get value from editbox
				double start = Float.valueOf(startValue.getText().toString()), 
                result = 0d;

				result = ((TempConversion) b).tempConverter(intOldVal, intNewVal, start);
				resultValue.setText(String.valueOf(result));
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
					intOldVal = Integer.parseInt(parent.getItemAtPosition(pos).toString());
			
				else if(parent.getId() == R.id.spnTo)
					intNewVal = Integer.parseInt(parent.getItemAtPosition(pos).toString());
				
				break;
			case 1://Temperature Converter
				if(parent.getId() == R.id.spnFrom)
					intOldVal = pos;//Integer.parseInt(parent.getItemAtPosition(pos).toString());
			
				else if(parent.getId() == R.id.spnTo)
					intNewVal = pos;//Integer.parseInt(parent.getItemAtPosition(pos).toString());
				break;
			default:
				break;
			}
		}
		

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	/**
	 * 
	 * Sean Sep 22, 2011
	 */
	private void baseConvert() {
		Log.d(TAG,"ConverterActivity: baseConvert(), oldbase= "
				+ intOldVal + ", newbase= " + intNewVal);//DBG
		
		/*BaseConversion b = new BaseConversion();*/
		
		if(intOldVal == 10){
			String s = ((BaseConversion) b).fromBaseTen(startValue.getText().toString(), intNewVal);
			resultValue.setText(s);
		}
		else if(intNewVal == 10){
			BigInteger res = ((BaseConversion) b).toBaseTen(startValue.getText().toString(), intOldVal);
			resultValue.setText(res.toString());
		}
		else{
			resultValue.setText(((BaseConversion) b).fromBaseTen(((BaseConversion) b).toBaseTen(startValue.getText()
					.toString(),intOldVal).toString(), intNewVal));
		}
	}
	
	
}
