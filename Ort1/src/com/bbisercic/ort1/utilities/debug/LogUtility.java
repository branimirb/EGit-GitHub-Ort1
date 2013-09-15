package com.bbisercic.ort1.utilities.debug;

/**
 * Class that provides method for easier tracking of the Android device's log
 */
public class LogUtility extends AbstractLogUtility {
	
	private static final String APP_TAG = "Ort1";

    protected static String getGlobalTagPrefix() {
        return APP_TAG;
    }

    /**
     * Forms a final log tag based on the class
     * @param Class
     *      The caller class
     * @return
     *      The Tag String
     */
	public static String getTag(Class<?> clazz) {
        return getGlobalTagPrefix() + clazz.getSimpleName();
    }
    
}
