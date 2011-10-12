/**
 * Oct 11, 2011
 */
package edu.elgin.Converter;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

/**
 * @author Sean Vogel 
 * Oct 11, 2011
 */
public class Settings extends PreferenceActivity{
	private final String TAG = "Converter";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		Log.d(TAG,"Settings: onCreate()");//DBG
		
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
}
