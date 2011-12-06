package edu.elgin.Converter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author Sean Vogel 
 * Dec 4, 2011
 * 
 * Refference:
 * Heavily adapted fom
 * http://www.androidpeople.com/android-xml-parsing-tutorial-using-saxparser
 * 
 * XML handler methods are called as xml stream is parses for tags
 */
public class XMLHandler extends DefaultHandler {

	private final String TAG = "Converter";//dbg
	
	String currentValue = null;
	
	//hold stored data from stream
	private static DataList datalist = null;
	
	public static DataList getDataList() {
		return datalist;
	}

	public static void setDataList(DataList dataList) {
		XMLHandler.datalist = dataList;
	}

	/** Called when tag starts
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		//first tag in feed
		//instantiate list
		if (localName.equals("channel"))
		{
			/** Start */ 
			datalist = new DataList();
		} 
	}

	/** Called when tag closing 
	  */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		/** set value */ 
		if (localName.equalsIgnoreCase("title")){
			datalist.setTitle(currentValue);	
		}
		if(currentValue.equalsIgnoreCase("Exchange Rates For US Dollar") == false){
			if (localName.equalsIgnoreCase("description")){
				String s = new String(currentValue.substring(14, currentValue.indexOf(' ', 15)));
				datalist.setRates(s);
				datalist.setDescription(currentValue.substring(14 + s.length()));
			}
		}
		currentValue = "";
	}

	/** Called to get tag characters 
	 *  */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
			
			currentValue = currentValue + new String(ch, start, length);
	}

}

