
package com.bbisercic.ort1.utilities.database;

import android.database.sqlite.SQLiteDatabase;

import com.bbisercic.ort1.utilities.debug.LogUtility;

public class DatabaseInitializer {

    public static final String TAG = LogUtility.getTag(DatabaseInitializer.class);

    public static void initializeQuizTable(SQLiteDatabase db) {
        LogUtility.d(TAG, "Called initializeQuizTable()");
        // TODO: insert data
    }

    public static void initializeArticlesTable(SQLiteDatabase db) {
        LogUtility.d(TAG, "Called initializeArticlesTable()");
        // TODO: insert data
    }

}
