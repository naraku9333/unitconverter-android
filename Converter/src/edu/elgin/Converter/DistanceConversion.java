/**
 * 
 */
package edu.elgin.Converter;

import android.util.Log;

/**
 * @author Robert Kahren 
 *
 */
public class DistanceConversion {

	private final String TAG = "conversion";
	
	
	public  double convert(int from, int to, double start){
		Log.d(TAG,"DistanceConversion: convert(), from= " + from + ", to= " + to);
		double result = 0.0;
			switch(from){
			case 0:
				result = start / 100000000;
				break;
			case 1:
				result = start / 10000000;
				break;
			case 2:
				result = start / 393700.8;
				break;
			case 3:
				result = start / 10000;
				break;
			case 4:
				result = start / 393.7008;
				break;
			case 5:
				result = start / 10;
				break;
			case 6:
				result = start / .3937008;
				break;
			case 7:
				result = start;
				break;
			case 8:
				result = start / .1;
				break;
			case 9:
				result = start / .0328084;
				break;
			case 10:
				result = start / .01;
				break;
			case 11:
				result = start / .01093613;
				break;
			case 12:
				result = start / .00001;
				break;
			case 13:
				result = start / .000006213712;
				break;
			}
			switch(to){
			case 0:
				result = result * 100000000;
				
				break;
			case 1:
				result = result * 10000000;
				
				break;
			case 2:
				result = result * 393700.8;
				
				
				break;
			case 3:
				result = result * 10000;
				
				
				break;
			case 4:
				result = result * 393.7008;
				
				
				break;
			case 5:
				result = result * 10;
				
				
				break;
			case 6:
				result = result * .3937008;
				
				
				break;
			
			case 8:
				result = result * .1;
				
				
				break;
			case 9:
				result = result * .0328084;
				
				
				break;
			case 10:
				result =result * .01;
				
				
				break;
			case 11:
				result = result * .01093613;
				
				
				break;
			case 12:
				result = result * .00001;
				
				
				break;
			case 13:
				result = result * .000006213712;
				
				
				break;
			default:
				break;
			
			}
			return result;
		}
}
		
