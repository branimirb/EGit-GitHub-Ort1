
package com.bbisercic.ort1.utilities.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.ArticleBean;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.database.dao.beans.QuizBean;
import com.bbisercic.ort1.utilities.debug.LogUtility;

public class DatabaseInitializer {

    public static final String TAG = LogUtility.getTag(DatabaseInitializer.class);

    public static void initializeQuizTable(Context context, SQLiteDatabase db) {
        LogUtility.d(TAG, "Called initializeQuizTable()");
        DaoInterface dao = DaoFactory.getInstance();
        dao.createQuizQuestion(context, new QuizBean("Sta znaci ORT?", "Osnove racunarske tehnike."));
        dao.createQuizQuestion(context, new QuizBean("Ko predaje ORT1?",
                "Predavanja drzi profesor Jovan Djordjevic."));
        dao.createQuizQuestion(context, new QuizBean("Ko drzi vezbe iz ORT1?",
                "Vezbe drzi docent Zaharije Radivojevic."));
    }

    public static void initializeArticlesTable(Context context, SQLiteDatabase db) {
        LogUtility.d(TAG, "Called initializeArticlesTable()");
        DaoInterface dao = DaoFactory.getInstance();
        dao.createArticle(context, new ArticleBean());
    }
    
    public static void initializeNotesTable(Context context, SQLiteDatabase db) {
        LogUtility.d(TAG, "Called initializeNotesTable()");
        DaoInterface dao = DaoFactory.getInstance();
        dao.createNote(context, new NoteBean());
    }

}
