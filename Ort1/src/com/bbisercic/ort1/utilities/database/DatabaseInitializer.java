
package com.bbisercic.ort1.utilities.database;

import android.content.Context;
import android.net.Uri;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.ArticleBean;
import com.bbisercic.ort1.database.dao.beans.QuizBean;
import com.bbisercic.ort1.database.dao.enums.ArticleType;
import com.bbisercic.ort1.utilities.debug.LogUtility;

public class DatabaseInitializer {

    public static final String TAG = LogUtility.getTag(DatabaseInitializer.class);

    public static void initializeQuizTable(Context context) {
        LogUtility.d(TAG, "Called initializeQuizTable()");
        DaoInterface dao = DaoFactory.getInstance();

        if (dao.getAllQuizQuestions(context).isEmpty()) {

            dao.createQuizQuestion(context, new QuizBean("Sta znaci ORT?", "Osnove racunarske tehnike."));
            dao.createQuizQuestion(context, new QuizBean("Ko predaje ORT1?",
                    "Predavanja drzi profesor Jovan Djordjevic."));
            dao.createQuizQuestion(context, new QuizBean("Ko drzi vezbe iz ORT1?",
                    "Vezbe drzi docent Zaharije Radivojevic."));

        }
    }

    public static void initializeArticlesTable(Context context) {
        LogUtility.d(TAG, "Called initializeArticlesTable()");
        DaoInterface dao = DaoFactory.getInstance();
        if (dao.getArticleByType(context, ArticleType.LECTURE).isEmpty()) {
            populateLectures(context);
        }
        if (dao.getArticleByType(context, ArticleType.EXERCICE).isEmpty()) {
            populateExercices(context);
        }
    }

    private static void populateLectures(Context context) {
        DaoInterface dao = DaoFactory.getInstance();

        final ArticleBean lecture1 = new ArticleBean(context.getString(R.string.lecureTitle_1),
                Uri.parse(context.getString(R.string.lecureUri_1)), ArticleType.LECTURE);
        dao.createArticle(context, lecture1);

        final ArticleBean lecture2 = new ArticleBean(context.getString(R.string.lecureTitle_2),
                Uri.parse(context.getString(R.string.lecureUri_2)), ArticleType.LECTURE);
        dao.createArticle(context, lecture2);
    }

    private static void populateExercices(Context context) {
        DaoInterface dao = DaoFactory.getInstance();
    }

}
