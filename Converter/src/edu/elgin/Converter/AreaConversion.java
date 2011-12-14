/**
 * Dec 12, 2011
 */
package edu.elgin.Converter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import android.content.Context;
import android.util.Log;

/**
 * @author Sean Vogel 
 * Dec 12, 2011
 * 
 * Area Conversion Class
 * 
 * ref:
 * http://en.wikipedia.org/wiki/Area#Conversions
 * http://www.ookingdom.com/metric/factors#area
 */
public class AreaConversion {
	private final String TAG = "Converter";//dbg
	private int r = 0;
	/**
	 * 
	 * Sean Dec 12, 2011
	 * @param oldSpn
	 * @param newSpn
	 * @param startValue
	 * @return
	 */
	public  String convert(int oldSpn, int newSpn, double startValue, Context con){
		Log.d(TAG,"AreaConversion: convert(), from= " + oldSpn + ", to= " + newSpn);//DBG
		BigDecimal result = BigDecimal.ZERO;
		
		//need the number of digits to roud
		r = Settings.getDigits(con);
		int c = 0;
		while(r>1){
			r/=10;
			++c;
		}
		r = c;
		if(oldSpn == 1){
			result = fromSqInch(newSpn, new BigDecimal(startValue));
		}
		else if(newSpn == 1){
			result = toSqInch(oldSpn, startValue);
		}
		else{
			result = fromSqInch(newSpn, toSqInch(oldSpn, startValue));
		}
		return result.round(new MathContext(r)).toString();
	}
	
	/**
	 * 
	 * Sean Dec 12, 2011
	 * @param from
	 * @param startValue
	 * @return
	 */
	private BigDecimal toSqInch(int from, double start){
		BigDecimal result = BigDecimal.ZERO;
		BigDecimal startValue = new BigDecimal(start);
		
		switch(from){
		case 0:
			result = startValue.multiply( new BigDecimal(6272640));
			break;
		case 1://sq in
			result = startValue;
			break;
		case 2:
			result = startValue.multiply(new BigDecimal(144));
			break;
		case 3:
			result = startValue.multiply(new BigDecimal(1296));
			break;
		case 4:
			result = startValue.multiply(new BigDecimal(4014489600d));
			break;
		case 5:
			result = startValue.divide(new BigDecimal(645.16),r,RoundingMode.HALF_UP);
			break;
		case 6:
			result = startValue.divide(new BigDecimal(6.4516),r,RoundingMode.HALF_UP); 
			break;
		case 7:
			result = startValue.divide(new BigDecimal(13.37803776));
			break;
		case 8:
			result = startValue.multiply(new BigDecimal(74749.377893817516030093788582639));
			break;
		case 9:
			result = startValue.divide(new BigDecimal(7.47493778938));// * 100 / 13.37803776;
			break;
		case 10:
			result = startValue.multiply(new BigDecimal(747.493778938));// * 10000 /13.37803776;
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
	private BigDecimal fromSqInch(int to, BigDecimal startValue){
		BigDecimal result = BigDecimal.ZERO;
		//BigDecimal startValue = new BigDecimal(start);
		
		switch(to){	
		case 0:
			result = startValue.divide(new BigDecimal(6272640),r,RoundingMode.HALF_UP);
			break;
		case 1://sq in
			result = startValue;
			break;
		case 2:
			result = startValue.divide(new BigDecimal(144));
			break;
		case 3:
			result = startValue.divide(new BigDecimal(1296),r,RoundingMode.HALF_UP);
			break;
		case 4:
			result = startValue.divide(new BigDecimal(4014489600d),r,RoundingMode.HALF_UP);
			break;
		case 5:
			result = startValue.multiply(new BigDecimal(645.16));
			break;
		case 6:
			result = startValue.multiply(new BigDecimal(6.4516));
			break;
		case 7:
			result = startValue.multiply(new BigDecimal(13.37803776));
			break;
		case 8:
			result = startValue.divide(new BigDecimal(74749.377893817516030093788582639),r,RoundingMode.HALF_UP);
			break;
		case 9:
			result = startValue.divide(new BigDecimal(7.47493778938),r,RoundingMode.HALF_UP);
			break;
		case 10:
			result = startValue.divide(new BigDecimal(747.493778938),r,RoundingMode.HALF_UP);
			break;
		}
		
		return result;
	}
}
