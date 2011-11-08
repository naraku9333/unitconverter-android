/**
 * 
 */
package edu.elgin.Converter;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * @author Robert Kahren II
 * 11/08/2011
 *
 */
public class SoundPlayer {
	private static MediaPlayer mediaPlayer = null;
	
	public static void play(Context context, int resource){
		stop(context);
		mediaPlayer = MediaPlayer.create(context, resource);
		mediaPlayer.setLooping(false);
		mediaPlayer.start();
	}
	public static void stop(Context context){
		if(mediaPlayer != null){
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
			
		}
	}
}
