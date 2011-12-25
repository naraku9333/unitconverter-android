package edu.elgin.Converter;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.content.Context;
import android.util.Log;

/**
 * Currency conversion
 * Downloads a rate file with respect to USD
 * and populates datalist of the rates using XMLHandler class
 * @author Sean Vogel
 * Dec 5, 2011
 * updated Dec 22, 2011
 * ref:
 * http://stackoverflow.com/questions/576513/android-download-binary-file-problems
 */
public class CurrencyConversion{

	private static final String TAG = "Converter";//dbg
	
	private DataList datalist = null;
	private final long TWELVE_HRS = 43200000;
	
	/**
	 * pull data from feed
	 */
	public CurrencyConversion(Context con) {
		super();
		String FILENAME = "rss.xml";
		Date today = new Date();

		//if file doesnt exist or file is older than 24hrs
		//download and save new rates file
		if(!(con.getFileStreamPath(FILENAME).exists()) ||
				((today.getTime() - con.getFileStreamPath(FILENAME).lastModified()) > TWELVE_HRS)){
			try {
				//open streams
				FileOutputStream fos = con.openFileOutput(FILENAME, Context.MODE_PRIVATE);				
				InputStream is = 
						new URL("http://themoneyconverter.com/rss-feed/USD/rss.xml").openStream();
				
				//read data from input stream to array 
				//and write to file stream 
				byte[] data = new byte[1024];
				int len = 0;				
				while ((len = is.read(data)) != -1) {
					fos.write(data, 0, len);
				}
				Log.d(TAG, "bytes read = "+ len);//dbg
				
				fos.close();
				is.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 			
		}						
		
		try {			
			// Handling XML 
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxparser = spf.newSAXParser();
			XMLReader xmlreader = saxparser.getXMLReader();
								
			// Create handler to handle XML Tags ( extends DefaultHandler )
			XMLHandler myHandler = new XMLHandler();
			xmlreader.setContentHandler(myHandler);
			xmlreader.parse(new InputSource( con.openFileInput(FILENAME)));
		} catch (Exception e) {
			Log.d(TAG, "XML Pasing Excpetion = " + e);
		}

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
		if(from > 57){
			--from;
		}
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
