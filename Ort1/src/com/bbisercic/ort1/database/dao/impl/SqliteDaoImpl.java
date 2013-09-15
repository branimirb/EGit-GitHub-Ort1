package com.bbisercic.ort1.database.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.ArticleBean;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.database.dao.beans.QuizBean;

/**
 * The specific implementation of the Data Access Object interface by using SQLite database.
 */
public class SqliteDaoImpl implements DaoInterface {

    @Override
    public long createNote(Context context, NoteBean noteBean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean removeNote(Context context, long noteId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAllNotes(Context context) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAllNotesByParentId(Context context, long parentId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Cursor getAllNotes(Context context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cursor getNoteById(Context context, long noteId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cursor getNoteByParentId(Context context, long parentId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateNote(Context context, NoteBean noteBean) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long createQuizQuestion(Context context, QuizBean quizBean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Cursor getAllQuizQuestions(Context context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long createArticle(Context context, ArticleBean articleBean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Cursor getAllArticles(Context context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cursor getArticleById(Context context, long articleId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
