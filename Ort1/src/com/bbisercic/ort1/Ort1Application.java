
package com.bbisercic.ort1;

import com.bbisercic.ort1.database.DatabaseFactory;
import com.bbisercic.ort1.utilities.database.DatabaseInitializer;
import com.bbisercic.ort1.utilities.debug.LogUtility;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class Ort1Application extends Application {

    private static final String TAG = LogUtility.getTag(Ort1Application.class);

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtility.i(TAG, "Created ORT1 Application!");

        SQLiteDatabase db = DatabaseFactory.getInstance(this);
        DatabaseInitializer.initializeNotesTable(this, db);
        DatabaseInitializer.initializeArticlesTable(this, db);
        DatabaseInitializer.initializeQuizTable(this, db);
    }

}
