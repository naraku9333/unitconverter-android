package edu.elgin.Converter;

import java.util.ArrayList;
/**
 * 
 * @author Sean Vogel 
 * Dec 4, 2011
 * 
 * Refference:
 * Heavily adapted fom
 * http://www.androidpeople.com/android-xml-parsing-tutorial-using-saxparser
 * 
 * DataList - holds specific elements data in ArrayLists
 */
public class DataList {
	
	/** Variables */
	private ArrayList<String> title = new ArrayList<String>();
	private ArrayList<String> description = new ArrayList<String>();
	private ArrayList<String> rates = new ArrayList<String>();
	
	
	public ArrayList<String> getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title.add(title);
	}

	public ArrayList<String> getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description.add(description);
	}

	public ArrayList<String> getRates() {
		return rates;
	}

	public void setRates(String rates) {
		this.rates.add(rates);
	}
}
