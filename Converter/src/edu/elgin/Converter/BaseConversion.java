package edu.elgin.Converter;

import java.math.BigInteger;
import android.util.Log;

/**
 * @author Sean Vogel 
 * Sep 28, 2011
 * 
 * Base Conversion class
 * modified from CIS-127 assignment
 */
public class BaseConversion {
	
	private static final String TAG = "Converter";//dbg
	
	/**
	 * calls correct BaseConversion func
	 * Sean Sep 22, 2011
	 */
	public String convert(int intOldSpnVal, int intNewSpnVal,String startValue ) {
		Log.d(TAG,"BaseConversion: convert(), oldbase= "
				+ intOldSpnVal + ", newbase= " + intNewSpnVal);//DBG
		
		String resultValue;
		if(intOldSpnVal == 10){
			resultValue = fromBaseTen(startValue, intNewSpnVal);
		}
		else if(intNewSpnVal == 10){
			BigInteger res = toBaseTen(startValue, intOldSpnVal);
			resultValue = res.toString();
		}
		else{
			resultValue = fromBaseTen(toBaseTen(startValue,intOldSpnVal).toString(), intNewSpnVal);
		}
		return resultValue;
	}

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
