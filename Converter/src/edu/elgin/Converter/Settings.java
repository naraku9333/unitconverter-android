/**
 * Oct 11, 2011
 */
package edu.elgin.Converter;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * @author Sean Vogel 
 * Oct 11, 2011
 * Updated by Robert Kahren II
 * Oct 26, 2011
 * added getVibrate function
 * @param <SharedPreference>
 */
public class Settings<SharedPreference> extends PreferenceActivity implements OnPreferenceClickListener{
	private final static String TAG = "Converter";//dbg
	
	private static final String OPT_VIBRATE = "vibrate";
	private static final boolean OPT_VIBATE_DEFAULT = true;
	private static final String OPT_SOUND = "sound";
	private static final boolean OPT_SOUND_DEFAULT = true;

	private static boolean unit_refresh = false;//refresh unit list screen
	private static boolean converter_refresh = false;//refresh converter screen
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		Log.d(TAG,"Settings: onCreate()");//DBG
		
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		
		//find preference and set on click listener
		Preference themePref = findPreference("change_theme");
		//Preference themePref2 = findPreference("color_dialog");
		themePref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			 
            public boolean onPreferenceClick(Preference preference) {        
            	//if clicked theme likely changed, set refreshs true 
            	unit_refresh = true;
            	converter_refresh = true;
                return false;
            }
		});}
		//themePref2.setOnPreferenceClickListener(new OnPreferenceClickListener(){
//
//				@Override
//				public boolean onPreferenceClick(Preference arg0) {
					// TODO Auto-generated method stub
					//ThemeDialog dialog = new ThemeDialog(Settings.this, );
					//dialog.show();
//					Intent i = new Intent(Settings.this,testclass.class);
//					startActivity(i);
//					return false;
//					
//	            }
//		});
//	}
	
	//getter setter methods
	public static boolean getVibrate(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_VIBRATE, OPT_VIBATE_DEFAULT);
	}
	public static int getVibrateInterval(Context context){
		return Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(context)
				.getString("vibrate_length", "0"));
	}
	public static boolean getSound(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_SOUND, OPT_SOUND_DEFAULT);
	}
	public static int getCustomTheme(Context context){
		return Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(context)
				.getString("change_theme", "0"));
	}
	public static void setCustomTheme(int theme){
		
	}
	public static int getDigits(Context context){
		return Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(context)
				.getString("digits", "100"));
	}
		
	//TODO find a cleaner more efficient way
	public static boolean getCRefresh(){
		return converter_refresh;
	}
	public static void setCRefresh(boolean val){
		converter_refresh = val;
	}
	public static boolean getURefresh(){
		return unit_refresh;
	}
	public static void setURefresh(boolean val){
		unit_refresh = val;
	}
	
	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		return false;
	}
}
