/**
 * Oct 11, 2011
 */
package edu.elgin.Converter;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.preference.PreferenceManager;

/**
 * @author Sean Vogel 
 * Oct 11, 2011
 * Updated by Robert Kahren II
 * Oct 26, 2011
 * added getVibrate function
 */
public class Settings extends PreferenceActivity{
	private final static String TAG = "Converter";
	private static final String OPT_VIBRATE = "vibrate";
	private static final boolean OPT_VIBATE_DEFAULT = true;
	private static final String OPT_SOUND = "sound";
	private static final boolean OPT_SOUND_DEFAULT = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		Log.d(TAG,"Settings: onCreate()");//DBG
		
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
	public static boolean getVibrate(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_VIBRATE, OPT_VIBATE_DEFAULT);
	}
	public static int getVibrateInterval(Context context){
		return Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(context).getString("vibrate_length", "0"));
	}
	public static boolean getSound(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_SOUND, OPT_SOUND_DEFAULT);
	}
}
