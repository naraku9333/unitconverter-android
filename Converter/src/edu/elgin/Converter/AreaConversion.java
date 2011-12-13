/**
 * Dec 12, 2011
 */
package edu.elgin.Converter;

import android.util.Log;

/**
 * @author Sean Vogel 
 * Dec 12, 2011
 * 
 * Area Conversion Class
 * 
 * ref:
 */
public class AreaConversion {
	private final String TAG = "Converter";//dbg
	
	/**
	 * 
	 * Sean Dec 12, 2011
	 * @param oldSpn
	 * @param newSpn
	 * @param startValue
	 * @return
	 */
	public  double convert(int oldSpn, int newSpn, double startValue){
		Log.d(TAG,"AreaConversion: convert(), from= " + oldSpn + ", to= " + newSpn);//DBG
		double result = 0d;
		if(oldSpn == 1){
			result = fromSqInch(newSpn, startValue);
		}
		else if(newSpn == 1){
			result = toSqInch(oldSpn, startValue);
		}
		else{
			result = fromSqInch(newSpn, toSqInch(oldSpn, startValue));
		}
		return result;
	}
	
	/**
	 * 
	 * Sean Dec 12, 2011
	 * @param from
	 * @param startValue
	 * @return
	 */
	private double toSqInch(int from, double startValue){
		double result = 0d;
		
		switch(from){
		case 0:
			result = (startValue * 6272640);
			break;
		case 1://sq in
			result = startValue;
			break;
		case 2:
			result = startValue * 144;
			break;
		case 3:
			result = startValue * 1296;
			break;
		case 4:
			result = startValue * 4014489600d;
			break;
		case 5:
			result = startValue/645.16;
			break;
		case 6:
			result = startValue / 6.4516; 
			break;
		case 7:
			result = startValue / 13.37803776;
			break;
		case 8:
			result = startValue * 74749.377893817516030093788582639;
			break;
		case 9:
			result = startValue * 100 / 13.37803776;
			break;
		case 10:
			result = startValue * 10000 /13.37803776;
			break;
		}
		return result;
	}
	
	/**
	 * 
	 * Sean Dec 12, 2011
	 * @param to
	 * @param startValue
	 * @return
	 */
	private double fromSqInch(int to, double startValue){
		double result = 0d;
		
		switch(to){	
		case 0:
			result = startValue / 6272640;
			break;
		case 1://sq in
			result = startValue;
			break;
		case 2:
			result = startValue / 144;
			break;
		case 3:
			result = startValue / 1296;
			break;
		case 4:
			result = startValue / 4014489600d;
			break;
		case 5:
			result = startValue * 645.16;
			break;
		case 6:
			result = startValue * 6.4516;
			break;
		case 7:
			result = startValue * 13.37803776;
			break;
		case 8:
			result = startValue / 74749.377893817516030093788582639;
			break;
		case 9:
			result = startValue / (100 / 13.37803776);
			break;
		case 10:
			result = startValue / (10000 / 13.37803776);
			break;
		}
		
		return result;
	}
}
