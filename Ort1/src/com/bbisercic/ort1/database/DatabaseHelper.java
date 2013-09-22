
package com.bbisercic.ort1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bbisercic.ort1.utilities.debug.LogUtility;

/**
 * A subclass of {@link SQLiteOpenHelper} that defines the behavior of the application's database related to
 * its creation and upgrading.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = LogUtility.getTag(DatabaseHelper.class);

    public DatabaseHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseConstants.CREATE_TABLE_NOTES);
        db.execSQL(DatabaseConstants.CREATE_TABLE_ARTICLES);
        db.execSQL(DatabaseConstants.CREATE_TABLE_QUIZ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtility.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        db.execSQL(DatabaseConstants.DROP_TABLE_NOTES);
        db.execSQL(DatabaseConstants.DROP_TABLE_ARTICLES);
        db.execSQL(DatabaseConstants.DROP_TABLE_QUIZ);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
