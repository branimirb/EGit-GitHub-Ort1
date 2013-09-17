package com.bbisercic.ort1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;



/**
 * Singleton factory class used for generating singleton instance to {@link DatabaseHelper}, so that
 * there is no need to worry about leak.
 */
public class DatabaseFactory {

    /**
     * The {@link DatabaseHelper} object that is in charge of all the database-related work.
     */
    private static DatabaseHelper sDatabaseHelper = null;
    
    private DatabaseFactory() {
    }

    /**
     * Retrieves the application's {@link SQLiteDatabase} object that is in charge of all the
     * database-related work. Do not call from the UI Thread, database upgrade takes a lot of time.
     * 
     * @param context
     *            The surrounding {@link Context}.
     * @return The mentioned {@link SQLiteDatabase} object.
     */
    public static synchronized SQLiteDatabase getInstance(Context context) {
        if (sDatabaseHelper == null) {
            sDatabaseHelper = new DatabaseHelper(context);
        }
        return sDatabaseHelper.getWritableDatabase();
    }

}