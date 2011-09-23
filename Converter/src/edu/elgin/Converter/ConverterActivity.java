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
		 
		 //fill ArrayAdapter with data from base_array
		 switch(UnitList.unit){
		 case 0:
			 setTitle("Base Converter");
			  adapter = ArrayAdapter.createFromResource(this, 
					 R.array.base_array, android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(R.layout.list_item);
			 break;
			 
		 case 1:
			 setTitle("Temperature Converter");
			  adapter = ArrayAdapter.createFromResource(this, 
					 R.array.temp_array, android.R.layout.simple_spinner_item);
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
		
		switch(view.getId()){
		case R.id.btnConvert:
			baseConvert();
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
		
		if(intOldVal == 10){
			String s = fromBaseTen(startValue.getText().toString(), intNewVal);
			resultValue.setText(s);
		}
		else if(intNewVal == 10){
			BigInteger res = toBaseTen(startValue.getText().toString(), intOldVal);
			resultValue.setText(res.toString());
		}
		else{
			resultValue.setText(fromBaseTen(toBaseTen(startValue.getText()
					.toString(),intOldVal).toString(), intNewVal));
		}
	}
	
	//TODO:move conversions to separate class(s)
	/**
	 * 
	 * Sean Sep 21, 2011
	 * @param num
	 * @param oldBase
	 * @return BigInteger result
	 * 
	 * converts from any base(2 - 36) to base 10
	 */
	private BigInteger toBaseTen(String num, int oldBase){
		Log.d(TAG,"ConverterActivity: toBaseTen(), num= " + num + ", oldbase=" + oldBase);//DBG
		
		BigInteger result = BigInteger.ZERO;
		
		num = num.toUpperCase();
		int len = num.length();
		
		//using BigIntegers to work with EXTREMELY large numbers
		BigInteger bigOldBase = new BigInteger(Integer.toString(oldBase));
		BigInteger temp, index;
		
		String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i = 0; i < len; i++)
		{
			index = new BigInteger(Integer.toString(digits.indexOf(num.charAt((len-1)-i))));
			temp = BigInteger.ZERO;
			temp = index.multiply(bigOldBase.pow(i));
			result = result.add(temp);
		}
		
		return result;
	}
	
	/**
	 * 
	 * Sean Sep 20, 2011
	 * @param orig
	 * @param newBase
	 * @return String result
	 * 
	 * converts to any base(2 - 36) from base 10 
	 */
	private String fromBaseTen(String orig, int newBase){
		Log.d(TAG,"ConverterActivity: fromBaseTen(), orig= " + orig + ", newBase= "+newBase);//DBG
		
		BigInteger num = new BigInteger(orig);

		//place holders for bases over 10
		String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		
		String temp = new String("");
		
		BigInteger rem; 
		BigInteger nb = new BigInteger(Integer.toString(newBase));
		
		//convert and store in temp backwards
		do{
			if(num.compareTo(nb) >= 0)
			{
				rem = num.mod(nb);
				//char d = digits.charAt(rem.intValue());
				temp= temp.concat(Character.toString(digits.charAt(rem.intValue())));
				num = num.divide(nb); 
			}
			else
			{
				temp = temp.concat(Character.toString(digits.charAt(num.intValue())));
				num = BigInteger.ZERO;
			}
		}while(num.compareTo(BigInteger.ZERO) > 0);
		
		//StringBuffer has a reverse, why not use it
		return new StringBuffer(temp).reverse().toString();
	}
}
