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
		
		//TODO FIX PRECISSION IN RETURN VALUES
		//it is messing up the return values 
		//this is UGLY ill break this up later
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
			switch(toTemp){
			case 0://celsius
				break;
			case 1://fahrenheit
				break;
			case 2://kelvin
				break;
			case 3://newton
				break;
			case 4://delisle
				break;
			case 5://rankine
				break;
			case 6://reaumur
				break;
			case 7://romer
				break;
			default:
				//send dialog error
			}
			break;
		case 4://Delisle
			switch(toTemp){
			case 0://celsius
				break;
			case 1://fahrenheit
				break;
			case 2://kelvin
				break;
			case 3://newton
				break;
			case 4://delisle
				break;
			case 5://rankine
				break;
			case 6://reaumur
				break;
			case 7://romer
				break;
			default:
				//send dialog error
			}
			break;
		case 5://Rankine
			switch(toTemp){
			case 0://celsius
				break;
			case 1://fahrenheit
				break;
			case 2://kelvin
				break;
			case 3://newton
				break;
			case 4://delisle
				break;
			case 5://rankine
				break;
			case 6://reaumur
				break;
			case 7://romer
				break;
			default:
				//send dialog error
			}
			break;
		case 6://Reaumur
			switch(toTemp){
			case 0://celsius
				break;
			case 1://fahrenheit
				break;
			case 2://kelvin
				break;
			case 3://newton
				break;
			case 4://delisle
				break;
			case 5://rankine
				break;
			case 6://reaumur
				break;
			case 7://romer
				break;
			default:
				//send dialog error
			}
			break;
		case 7://Romer
			switch(toTemp){
			case 0://celsius
				break;
			case 1://fahrenheit
				break;
			case 2://kelvin
				break;
			case 3://newton
				break;
			case 4://delisle
				break;
			case 5://rankine
				break;
			case 6://reaumur
				break;
			case 7://romer
				break;
			default:
				//send dialog error
			}
			break;
		}
		
		//return result;
		return resultValue;
	}

/**
 * 
 * Sean Sep 29, 2011
 * start type specific functions
 * @param toTemp
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
			resultValue = (startValue - 273.15);
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
}
