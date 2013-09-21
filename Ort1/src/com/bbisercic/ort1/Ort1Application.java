
package com.bbisercic.ort1;

import android.app.Application;

import com.bbisercic.ort1.utilities.database.DatabaseInitializer;
import com.bbisercic.ort1.utilities.debug.LogUtility;

public class Ort1Application extends Application {

    private static final String TAG = LogUtility.getTag(Ort1Application.class);

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtility.i(TAG, "Created ORT1 Application!");

        DatabaseInitializer.initializeArticlesTable(this);
        DatabaseInitializer.initializeQuizTable(this);
    }

}
