package com.thea.raining.utils;

import android.util.Log;

/**
 * @author Thea (theazhu0321@gmail.com)
 */
public class LogUtils {
	private final static boolean DEBUG = true;

	private LogUtils() {
	}
	
	public static void d(String tag,String msg){
		if (DEBUG)
			Log.d(tag, msg);
	}

	public static void e(String tag,String msg){
		if (DEBUG)
			Log.e(tag, msg);
	}

	public static void i(String tag,String msg){
		if (DEBUG)
			Log.i(tag, msg);
	}

	public static void w(String tag,String msg){
		if (DEBUG)
			Log.w(tag, msg);
	}

	public static void v(String tag,String msg){
		if (DEBUG)
			Log.v(tag, msg);
	}
}
