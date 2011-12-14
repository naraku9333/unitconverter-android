package edu.elgin.Converter;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.util.Log;

/**
 * 
 * @author Sean Vogel 
 * Dec 5, 2011
 */
public class CurrencyConversion{

	private static final String TAG = "Converter";//dbg
	
	private DataList datalist = null;
	
	/**
	 * pull data from feed
	 */
	public CurrencyConversion(Context con) {
		super();
		
		try {			
				/** Handling XML */
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser saxparser = spf.newSAXParser();
				XMLReader xmlreader = saxparser.getXMLReader();
				
				/** Send URL to parse XML Tags */
				URL sourceUrl = null;
				sourceUrl = new URL("http://themoneyconverter.com/rss-feed/USD/rss.xml");
								
				/** Create handler to handle XML Tags ( extends DefaultHandler ) */
				XMLHandler myHandler = new XMLHandler();
				xmlreader.setContentHandler(myHandler);
				xmlreader.parse(new InputSource(sourceUrl.openStream()));
				//xmlreader.parse(new InputSource(con.getResources().openRawResource(R.raw.rss)));
			} catch (Exception e) {
				Log.d(TAG, "XML Pasing Excpetion = " + e);
			}
//		finally{//if stream fails load from resource
//			if(datalist == null){
//				try{
//					/** Handling XML */
//					SAXParserFactory spf = SAXParserFactory.newInstance();
//					SAXParser saxparser = spf.newSAXParser();
//					XMLReader xmlreader = saxparser.getXMLReader();
//					
//					/** Create handler to handle XML Tags ( extends DefaultHandler ) */
//					XMLHandler myHandler = new XMLHandler();
//					xmlreader.setContentHandler(myHandler);
//					
//					//read from resource//dbg
//					InputSource is = new InputSource(con.getResources().openRawResource(R.raw.rss));//dbg
//					xmlreader.parse(new InputSource(con.getResources().openRawResource(R.raw.rss)));
//				}
//				catch(Exception nex){
//					Log.d(TAG, " Excpetion = " + nex);
//				}
//			}
//		}
		//get list of feed data
		datalist = XMLHandler.getDataList();			
	}

	/**
	 * 
	 * Sean Dec 5, 2011
	 * @param from spinner from index
	 * @param to spinner to index
	 * @param startValue
	 * @return
	 */
	public double convert(int from, int to, double startValue) {
		Log.d(TAG,"Currency convert()");//DBG
		double result = 0d;
		
		//USD is not in feed, but is in currency_array,
		//this is necessary to get correct data from datalist arrays
		if(from > 57)
			--from;
		else if(to > 57)
			--to;
		
		//call correct method
		if(from == 57)
			result = fromUSD(startValue, Double.valueOf(datalist.getRates().get(to+1)));
		else if(to == 57)
			result = toUSD(startValue, Double.valueOf(datalist.getRates().get(from+1)));
		else
			result = fromUSD(toUSD(startValue, Double.valueOf(datalist.getRates().get(from+1))),
					Double.valueOf(datalist.getRates().get(to+1)));
		
		return result;
	}
	
	/**
	 * 
	 * Sean Dec 5, 2011
	 * @param value value to convert
	 * @param rate exchange rate
	 * @return
	 */
	private double fromUSD(double value, double rate) {
		return (value * rate);
	}
	
	/**
	 * 
	 * Sean Dec 5, 2011
	 * @param value value to convert
	 * @param rate exchange rate
	 * @return
	 */
	private double toUSD(double value, double rate) {
		return (value / rate);
	}
}
