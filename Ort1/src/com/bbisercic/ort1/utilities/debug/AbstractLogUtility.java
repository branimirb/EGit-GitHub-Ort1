package com.bbisercic.ort1.utilities.debug;

import android.util.Log;

/**
 * Abstract class that provides method for easier tracking of the Android
 * device's log
 */
public abstract class AbstractLogUtility {

	private static final boolean VERBOSE = true;

	private static final boolean DEBUG = true;

	private static final boolean INFO = true;

	private static final boolean WARNING = true;

	private static final boolean ERROR = true;

	public static void v(String tag, String msg) {
		if (VERBOSE) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (INFO) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (WARNING) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (ERROR) {
			Log.e(tag, msg);
		}
	}

}
