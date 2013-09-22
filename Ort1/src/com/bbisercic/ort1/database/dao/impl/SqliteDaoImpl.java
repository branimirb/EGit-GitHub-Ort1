
package com.bbisercic.ort1.database.dao.impl;

import java.util.List;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.bbisercic.ort1.database.DatabaseAdapter;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.ArticleBean;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.database.dao.beans.QuizBean;
import com.bbisercic.ort1.database.dao.enums.ArticleType;
import com.bbisercic.ort1.database.dao.extractors.ArticleBeanCursorExtractor;
import com.bbisercic.ort1.database.dao.extractors.NoteBeanCursorExtractor;
import com.bbisercic.ort1.database.dao.extractors.QuizBeanCursorExtractor;

/**
 * The specific implementation of the Data Access Object interface by using SQLite database.
 */
public class SqliteDaoImpl implements DaoInterface {

    @Override
    public long createNote(Context context, NoteBean noteBean) {
        return DatabaseAdapter.insertNote(context, noteBean.getTitle(), noteBean.getBody(),
                noteBean.getArticleId(), noteBean.getArticleTitle(), noteBean.getTimestamp(),
                noteBean.getArticleType());
    }

    @Override
    public boolean removeNote(Context context, long noteId) {
        return DatabaseAdapter.deleteNoteById(context, noteId);
    }
    
    @Override
    public boolean removeNotesById(Context context, Set<Long> ids) {
        return DatabaseAdapter.deleteNotesById(context, ids);
    }
    
    @Override
    public boolean removeAllNotes(Context context) {
        return DatabaseAdapter.deleteAllNotes(context);
    }

    @Override
    public boolean removeNotesByParentId(Context context, long parentId) {
        return DatabaseAdapter.deleteNotesByParentId(context, parentId);
    }

    @Override
    public List<NoteBean> getAllNotes(Context context) {
        Cursor cursor = DatabaseAdapter.getAllNotes(context);
        return NoteBeanCursorExtractor.extractNoteInfoIntoList(cursor);
    }

    @Override
    public NoteBean getNoteById(Context context, long noteId) throws SQLException {
        Cursor cursor = DatabaseAdapter.getNoteById(context, noteId);
        return NoteBeanCursorExtractor.extractNoteInfoIntoBean(cursor);
    }

    @Override
    public List<NoteBean> getNotesByParentId(Context context, long parentId) throws SQLException {
        Cursor cursor = DatabaseAdapter.getNotesByParentId(context, parentId);
        return NoteBeanCursorExtractor.extractNoteInfoIntoList(cursor);
    }

    @Override
    public boolean updateNote(Context context, NoteBean noteBean) {
        return DatabaseAdapter.updateNote(context, noteBean.getId(), noteBean.getTitle(), noteBean.getBody(),
                noteBean.getArticleId(), noteBean.getArticleTitle(), noteBean.getTimestamp());
    }

    @Override
    public long createQuizQuestion(Context context, QuizBean quizBean) {
        return DatabaseAdapter.insertQuizQuestion(context, quizBean.getQuestion(), quizBean.getAnswer());
    }

    @Override
    public List<QuizBean> getAllQuizQuestions(Context context) {
        Cursor cursor = DatabaseAdapter.getAllQuizQuestions(context);
        return QuizBeanCursorExtractor.extractQuizInfoIntoList(cursor);
    }

    @Override
    public long createArticle(Context context, ArticleBean articleBean) {
        return DatabaseAdapter.insertArticle(context, articleBean.getTitle(), articleBean.getUri(),
                articleBean.getArticleType());
    }

    @Override
    public List<ArticleBean> getAllArticles(Context context) {
        Cursor cursor = DatabaseAdapter.getAllArticles(context);
        return ArticleBeanCursorExtractor.extractArticleInfoIntoList(cursor);
    }

    @Override
    public List<ArticleBean> getArticleByType(Context context, ArticleType articleType) throws SQLException {
        Cursor cursor = DatabaseAdapter.getArticlesByType(context, articleType);
        return ArticleBeanCursorExtractor.extractArticleInfoIntoList(cursor);
    }

    @Override
    public ArticleBean getArticleById(Context context, long articleId) throws SQLException {
        Cursor cursor = DatabaseAdapter.getArticleById(context, articleId);
        return ArticleBeanCursorExtractor.extractArticleInfoIntoBean(cursor);
    }

}
