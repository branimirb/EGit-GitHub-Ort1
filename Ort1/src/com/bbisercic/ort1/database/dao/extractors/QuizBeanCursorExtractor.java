
package com.bbisercic.ort1.database.dao.extractors;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.bbisercic.ort1.database.DatabaseConstants.QuizeInfo;
import com.bbisercic.ort1.database.dao.beans.QuizBean;

/**
 * Class for extracting fetched database data from {@link Cursor} into {@link QuizBean}.
 */
public class QuizBeanCursorExtractor {

    /**
     * Packs data from a Cursor row into a {@link QuizBean}
     * 
     * @param cursor
     *            The surrounding {@link Context}.
     * @return The {@link QuizBean} that holds the data from the Cursor's row.
     */
    private static QuizBean extractQuizBeanFromCursorRow(Cursor cursor) {
        QuizBean quizBean = new QuizBean();
        extractFromCursorRowToQuizBean(cursor, quizBean);
        return quizBean;
    }

    private static void extractFromCursorRowToQuizBean(Cursor cursor, QuizBean quizBean) {

        final long rowId = cursor.getLong(cursor.getColumnIndex(QuizeInfo._ID));
        final String question = cursor.getString(cursor.getColumnIndex(QuizeInfo.COLUMN_QUESTION));
        final String answer = cursor.getString(cursor.getColumnIndex(QuizeInfo.COLUMN_ANSWER));

        quizBean.setId(rowId);
        quizBean.setQuestion(question);
        quizBean.setAnswer(answer);
    }

    public static QuizBean extractQuizInfoIntoBean(Cursor cursor) {
        QuizBean quizBean = null;
        if (cursor.moveToFirst()) {
            quizBean = extractQuizBeanFromCursorRow(cursor);
        }
        cursor.close();
        return quizBean;
    }

    /**
     * Packs data from a {@link Cursor} row into a list of {@link QuizBean}.
     * 
     * @param cursor
     *            he surrounding {@link Context}.
     * @return The list of {@link QuizBean} that holds the {@link Cursor} rows' data.
     */
    public static List<QuizBean> extractQuizInfoIntoList(Cursor cursor) {
        ArrayList<QuizBean> list = new ArrayList<QuizBean>();
        while (cursor.moveToNext()) {
            list.add(extractQuizBeanFromCursorRow(cursor));
        }
        cursor.close();
        return list;
    }
    
}
