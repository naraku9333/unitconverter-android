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

	private final String TAG = "Sudoku";
	
	/**
	 * 
	 * Sean Sep 22, 2011
	 * @param fromTemp	the index of item selected from spnFrom
	 * @param toTemp	the index of selected from spnTo
	 * @return		conversion result
	 * conversions formulas from:
	 * http://en.wikipedia.org/wiki/Conversion_of_units_of_temperature
	 */
	public  double tempConverter(int fromTemp, int toTemp, double startValue){
		Log.d(TAG,"ConverterActivity: tempConverter(), from= " + fromTemp + ", to= " + toTemp);//DBG
		
		double resultValue = 0d;
		
		switch(fromTemp){
		case 0://Celsius
			resultValue = celsiusToAll(toTemp, startValue);
			break;
		case 1://Fahrenheit
			resultValue = fahrenheitToAll(toTemp, startValue);
			break;
		case 2://Kelvin
			resultValue = kelvinToAll(toTemp, startValue);
			break;
		case 3://Newton
			resultValue = newtonToAll(toTemp, startValue);
			break;
		case 4://Delisle
			resultValue = delisleToAll(toTemp, startValue);
			break;
		case 5://Rankine
			resultValue = rankineToAll(toTemp, startValue);
			break;
		case 6://Reaumur
			resultValue = reaumurToAll(toTemp, startValue);
			break;
		case 7://Romer
			resultValue = romerToAll(toTemp, startValue);
			break;
		}
		
		//return result;
		return resultValue;
	}
	
/**
 * 
 * Sean Sep 29, 2011
 * start type specific functions
 * each function converts the named scale to the 7 others
 * @param toTemp int value of result temp scale
 * @param startValue
 * @return converted temp
 */
	private double celsiusToAll(int toTemp, double startValue){
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
			//TODO send dialog error
			break;
		}
		return resultValue;
	}

	private double fahrenheitToAll(int toTemp, double startValue){
		double resultValue = 0d;
		
		switch(toTemp){
		case 0://celsius
			resultValue = (startValue - 32) * 5/9;
			break;
		case 2://kelvin
			resultValue = (startValue + 459.67f) * 5/9;
			break;
		case 3://newton
			resultValue = (startValue - 32) * 11/60;
			break;
		case 4://delisle
			resultValue = (212 - startValue) * 5/6;
			break;
		case 5://rankine
			resultValue = startValue + 459.67f;
			break;
		case 6://reaumur
			resultValue = (startValue - 32) * 4/9;
			break;
		case 7://romer
			resultValue = (startValue - 32) * 7/24 + 7.5;
			break;
		default:
			//TODO send dialog error
		}
		return resultValue;
	}
	
	private double kelvinToAll(int toTemp, double startValue){
		
		double resultValue = 0d;
		
		switch(toTemp){
		case 0://celsius
			resultValue = (startValue - 273.15d);
			break;
		case 1://fahrenheit
			resultValue = (startValue * 9/5 - 459.67);
			break;
		case 3://newton
			resultValue = (startValue - 273.15) * 33/100;
			break;
		case 4://delisle
			resultValue = (373.15 - startValue) * 3/2;
			break;
		case 5://rankine
			resultValue = startValue * 9/5;
			break;
		case 6://reaumur
			resultValue = (startValue - 273.15) * 4/5;
			break;
		case 7://romer
			resultValue = (startValue - 273.15) * 21/40 + 7.5;
			break;
		default:
			//TODO send dialog error
		}
		
		return resultValue;
	}
	
	//tested  several values against wiki, looks accurate
	private double newtonToAll(int toTemp, double startValue) {
		double resultValue = 0d;
		
		switch(toTemp){
		case 0://celsius
			resultValue = startValue * 100/33;
			break;
		case 1://fahrenheit
			resultValue = startValue * 60/11 +32;
			break;
		case 2://kelvin
			resultValue = startValue *100/33 +273.15;
			break;
		case 4://delisle
			resultValue = (33 - startValue) * 50/11; 
			break;
		case 5://rankine
			resultValue = startValue *60/11 + 491.67;
			break;
		case 6://reaumur
			resultValue = startValue * 80/33;
			break;
		case 7://romer
			resultValue = startValue * 35/22 + 7.5;
			break;
		default:
			//TODO send dialog error
		}
			return resultValue;
	}
	
	private double delisleToAll(int toTemp, double startValue) {
		double resultValue = 0d;
		
		switch(toTemp){
		case 0://celsius
			resultValue = 100 - startValue * 2/3;
			break;
		case 1://fahrenheit
			resultValue = 212 - startValue * 6/5;
			break;
		case 2://kelvin
			resultValue = 373.15 - startValue * 2/3;
			break;
		case 3://newton
			resultValue = 33 - startValue * 11/50;
			break;
		case 5://rankine
			resultValue = 671.67 - startValue * 6/5;
			break;
		case 6://reaumur
			resultValue = 80 - startValue * 8/15;
			break;
		case 7://romer
			resultValue = 60 - startValue * 7/20;
			break;
		default:
			//TODO send dialog error
		}
		return resultValue;
	}
	
	private double rankineToAll(int toTemp, double startValue) {
		double resultValue = 0d;
		
		switch(toTemp){
		case 0://celsius
			resultValue = (startValue - 491.67) * 5/9;
			break;
		case 1://fahrenheit
			resultValue = startValue - 459.67;
			break;
		case 2://kelvin
			resultValue = startValue * 5/9;
			break;
		case 3://newton
			resultValue = (startValue - 491.67) * 11/60;
			break;
		case 4://delisle
			resultValue = (671.67 - startValue) * 5/6;
			break;
		case 6://reaumur
			resultValue = (startValue - 491.67) * 4/9;
			break;
		case 7://romer
			resultValue = (startValue -491.67) * 7/24 + 7.5;
			break;
		default:
			//TODO send dialog error
		}
		return resultValue;
	}
	
	private double reaumurToAll(int toTemp, double startValue) {
		double resultValue = 0d;
		
		switch(toTemp){
		case 0://celsius
			resultValue = startValue * 5/4;
			break;
		case 1://fahrenheit
			resultValue = startValue * 9/4 +32;
			break;
		case 2://kelvin
			resultValue = startValue * 5/4 + 273.15;
			break;
		case 3://newton
			resultValue = startValue * 33/80;
			break;
		case 4://delisle
			resultValue = (80 - startValue) * 15/8;
			break;
		case 5://rankine
			resultValue = startValue * 9/4 + 491.67;
			break;
		case 7://romer
			resultValue = startValue * 21/32 + 7.5;
			break;
		default:
			//TODO send dialog error
		}
		return resultValue;
	}
	
	private double romerToAll(int toTemp, double startValue) {
		double resultValue = 0d;
		
		switch(toTemp){
		case 0://celsius
			resultValue = (startValue - 7.5) * 40/21;
			break;
		case 1://fahrenheit
			resultValue = (startValue - 7.5) * 24/7 + 32;
			break;
		case 2://kelvin
			resultValue = (startValue - 7.5) * 40/21 + 273.15;
			break;
		case 3://newton
			resultValue = (startValue - 7.5) * 22/35;
			break;
		case 4://delisle
			resultValue = (60 -startValue) * 20/7;
			break;
		case 5://rankine
			resultValue = (startValue - 7.5) * 24/7 + 491.67;
			break;
		case 6://reaumur
			resultValue = (startValue - 7.5) * 32/21;
			break;
		default:
			//TODO send dialog error
		}
		return resultValue;
	}
}
