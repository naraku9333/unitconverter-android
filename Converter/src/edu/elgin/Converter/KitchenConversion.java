/**
 * 
 */
package edu.elgin.Converter;

import android.util.Log;

/**
 * @author Robert Kahren 
 *
 */
public class KitchenConversion {

	private final String TAG = "Converter";
	
	
	public  double convert(int from, int to, double start){
		Log.d(TAG,"KitchenConversion: convert(), from= " + from + ", to= " + to);
		
		double result = 0.0;
		switch(from){
		case 0://Teaspoons
			result = start * 1/6;
			break;
		case 1://Tablespoons
			result = start * 1/2;
			break;
		case 2://Fluid ounces
			result = start;
			break;
		case 3: //Cups
			result = start * 8;
			break;
		case 4: //Pints
			result = start * 16;
			break;
		case 5://Quarts
			result = start * 32;
			break;
		case 6://Gallons
			result = start * 128;
			break;
		
		}
		//convert to desired output
		switch(to){
		case 0://Teaspoons
			result = result * 6;
			
			break;
		case 1://Tablespoons
			result = result * 2;
			
			break;
		
		case 3://Cups
			result = result / 8;
			
			break;
		case 4://Pints
			result = result / 16;
			
			break;
		case 5: //Quarts
			result = result / 32;
			
			break;
		case 6://Gallons
			result = result / 128;
			
			break;
		default:
			break;
		
		}
		return result;
	
		
	}
}

