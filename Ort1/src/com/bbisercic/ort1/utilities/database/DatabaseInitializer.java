
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

            dao.createQuizQuestion(
                    context,
                    new QuizBean(context.getString(R.string.question_1), context.getString(R.string.answer_1)));
            dao.createQuizQuestion(
                    context,
                    new QuizBean(context.getString(R.string.question_2), context.getString(R.string.answer_2)));
            dao.createQuizQuestion(
                    context,
                    new QuizBean(context.getString(R.string.question_3), context.getString(R.string.answer_3)));
            dao.createQuizQuestion(
                    context,
                    new QuizBean(context.getString(R.string.question_4), context.getString(R.string.answer_4)));
            dao.createQuizQuestion(
                    context,
                    new QuizBean(context.getString(R.string.question_5), context.getString(R.string.answer_5)));
            dao.createQuizQuestion(
                    context,
                    new QuizBean(context.getString(R.string.question_6), context.getString(R.string.answer_6)));
            dao.createQuizQuestion(
                    context,
                    new QuizBean(context.getString(R.string.question_7), context.getString(R.string.answer_7)));
            dao.createQuizQuestion(
                    context,
                    new QuizBean(context.getString(R.string.question_8), context.getString(R.string.answer_8)));
            dao.createQuizQuestion(
                    context,
                    new QuizBean(context.getString(R.string.question_9), context.getString(R.string.answer_9)));
            dao.createQuizQuestion(
                    context,
                    new QuizBean(context.getString(R.string.question_10), context
                            .getString(R.string.answer_10)));

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
