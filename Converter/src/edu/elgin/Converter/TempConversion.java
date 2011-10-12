/**
 * Sep 28, 2011
 */
package edu.elgin.Converter;

import android.util.Log;

/**
 * @author Sean Vogel 
 * Sep 28, 2011
 * 
 * Temperature Conversion Class
 * 
 * formulas and test data from:
 * http://en.wikipedia.org/wiki/Conversion_of_units_of_temperature
 */
public class TempConversion {

	private final String TAG = "Converter";
	
	/**
	 * 
	 * Sean Sep 22, 2011
	 * @param fromTemp	the index of item selected from spnFrom
	 * @param toTemp	the index of selected from spnTo
	 * @return		conversion result
	 * 
	 */
	public  double convert(int fromTemp, int toTemp, double startValue){
		Log.d(TAG,"TempConversion: convert(), from= " + fromTemp + ", to= " + toTemp);//DBG
		
		double resultValue = 0d;
		
		if(fromTemp == 0){
			resultValue = fromCelsius(toTemp, startValue);
		}
		else if(toTemp == 0){
			resultValue = toCelsius(fromTemp, startValue);
		}
		else{
			resultValue = fromCelsius(toTemp, toCelsius(fromTemp, startValue));
		}
		
		//return result;
		return resultValue;
	}
	
	/**
	 * Sean Sep 30, 2011
	 * private functions to convert to/from Celsius
	 */
	private double toCelsius(int fromTemp, double startValue){
		
		double resultValue = 0d;
		
		switch(fromTemp){
		
		case 1://Fahrenheit
			resultValue = (startValue - 32) * 5/9;
			break;
		case 2://Kelvin
			resultValue = (startValue - 273.15d);
			break;
		case 3://Newton
			resultValue = startValue * 100/33;
			break;
		case 4://Delisle
			resultValue = 100 - startValue * 2/3;
			break;
		case 5://Rankine
			resultValue = (startValue - 491.67) * 5/9;
			break;
		case 6://Reaumur
			resultValue = startValue * 5/4;
			break;
		case 7://Romer
			resultValue = (startValue - 7.5) * 40/21;
			break;
		default:
			//TODO send error
			break;
		}
		
		return resultValue;
	}
	
	private double fromCelsius(int toTemp, double startValue){
		
		double resultValue = 0d;
		
		switch(toTemp){
				
		case 1://fahrenheit
			resultValue = startValue * 9/5 + 32;
			break;
		case 2://Kelvin
			resultValue = startValue + 273.15f;
			break;
		case 3://newton
			resultValue = startValue * 33/100;
			break;
		case 4://delisle
			resultValue = (100 - startValue) * 3/2;
			break;
		case 5://rankine
			resultValue = (startValue + 273.15f) * 9/5;
			break;
		case 6://reaumur
			resultValue = startValue * 4/5;
			break;
		case 7://romer
			resultValue = startValue * 21/40 + 7.5f;
		default:
			//TODO send error
			resultValue = startValue;
			break;
		}
		
		return resultValue;
	}
}
