package edu.elgin.Converter;

import java.math.BigDecimal;
import java.math.MathContext;

import android.content.Context;

/**
 * 
 * @author Sean Vogel 
 * Dec 12, 2011
 * 
 * ref:
 * http://www.calculatorsoup.com/calculators/conversions/density.php
 * http://www.onlineconversion.com/density_all.htm
 */
public class DensityConversion {

	//private static final String TAG = "Converter";//dbg
	private int r = 0;
	
	public  String convert(int oldSpn, int newSpn, double startValue, Context con){
		BigDecimal result = BigDecimal.ZERO;
		
		//need the number of digits to roud
		r = Settings.getDigits(con);
		int c = 0;
		while(r>1){
			r/=10;
			++c;
		}
		r = c;
		
		if(oldSpn == 0){
			result = fromGramsPerCM3(newSpn, new BigDecimal(startValue));
		}
		else if(newSpn == 0){
			result = toGramsPerCM3(oldSpn, new BigDecimal(startValue));
		}
		else{
			result = fromGramsPerCM3(newSpn, toGramsPerCM3(oldSpn, new BigDecimal(startValue)));
		}
		return result.round(new MathContext(r)).toPlainString();
	}
	
	/**
	 * 
	 * Sean Dec 13, 2011
	 * @param from
	 * @param startValue
	 * @return
	 */
	private BigDecimal toGramsPerCM3(int from, BigDecimal startValue){
		
		double multiplicand = 0d;
		
		switch(from){
		case 0:// g/cm3
			multiplicand = 1d;
			break;
		case 1:
			multiplicand = 0.000172;
			break;
		case 2:
			multiplicand = 0.172;
			break;
		case 3:
			multiplicand = 1000;
			break;
		case 4:
			multiplicand = 0.001;
			break;
		case 5:
			multiplicand = 1d;
			break;
		case 6:
			multiplicand = 1.7299940371;
			break;
		case 7:
			multiplicand = 0.0010011539566;
			break;
		case 8:
			multiplicand = 0.000037079776172;
			break;
		case 9:
			multiplicand = 0.0062360232914;
			break;
		case 10:
			multiplicand = 0.0074891516757;
			break;
		case 11:
			multiplicand = 27.679904593;
			break;
		case 12:
			multiplicand = 0.016018463306;
			break;
		case 13:
			multiplicand = 0.00059327641875;
			break;
		case 14:
			multiplicand = 0.099776372663;
			break;
		case 15:
			multiplicand = 0.11982642681;
			break;		
		}
		
		return startValue.multiply(new BigDecimal(multiplicand));
	}
	
	/**
	 * 
	 * Sean Dec 13, 2011
	 * @param to
	 * @param startValue
	 * @return
	 */
	private BigDecimal fromGramsPerCM3(int to, BigDecimal startValue){
		
		double multiplicand = 0d;
		
		switch(to){
		case 0:// g/cm3
			multiplicand = 1d;
			break;
		case 1:
			multiplicand = 1000000;
			break;
		case 2:
			multiplicand = 1000;
			break;
		case 3:
			multiplicand = 0.001;
			break;
		case 4:
			multiplicand = 1000;
			break;
		case 5:
			multiplicand = 1d;
			break;
		case 6:
			multiplicand = 0.57803667444;
			break;
		case 7:
			multiplicand = 998.84737348;
			break;
		case 8:
			multiplicand = 26968.879083;
			break;
		case 9:
			multiplicand = 160.35860568;
			break;
		case 10:
			multiplicand = 133.5264718;
			break;
		case 11:
			multiplicand = 0.0361272292153;
			break;
		case 12:
			multiplicand = 62.427960841;
			break;
		case 13:
			multiplicand = 1685.5549427;
			break;
		case 14:
			multiplicand = 10.022412855;
			break;
		case 15:
			multiplicand = 8.3454044873;
			break;			
		}
		
		return startValue.multiply(new BigDecimal(multiplicand));
	}	
}