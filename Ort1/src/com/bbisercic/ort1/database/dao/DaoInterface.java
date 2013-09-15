
package com.bbisercic.ort1.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.bbisercic.ort1.database.dao.beans.ArticleBean;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.database.dao.beans.QuizBean;

/**
 * The Data Access Object interface for interacting with the persistent storage.
 */
public interface DaoInterface {

    /**
     * Create a new note using the provided {@link NoteBean}. If the note is successfully created return the
     * new rowId for that note, otherwise return a -1 to indicate failure.
     * 
     * @param context
     *            The surrounding {@link Context}
     * @param noteBean
     *            The bean used for creating new note.
     * @return rowId or -1 if failed
     */
    public abstract long createNote(Context context, NoteBean noteBean);

    /**
     * Delete the note with the given rowId
     * 
     * @param context
     *            The surrounding {@link Context}
     * @param rowId
     *            Id of note to delete
     * @return true if deleted, false otherwise
     */
    public abstract boolean removeNote(Context context, long noteId);

    /**
     * Delete all notes from the database.
     * 
     * @param context
     *            The surrounding {@link Context}
     * @return true if deleted, false otherwise
     */
    public abstract boolean removeAllNotes(Context context);

    /**
     * Delete all notes with the given parentId
     * 
     * @param context
     *            The surrounding {@link Context}
     * @param parentId
     *            The parent article id.
     * @return true if deleted, false otherwise
     */
    public abstract boolean removeAllNotesByParentId(Context context, long parentId);

    /**
     * Return a {@link Cursor} over the list of all notes in the database
     * 
     * @param context
     *            The surrounding {@link Context}
     * @return {@link Cursor} over all notes
     */
    public abstract Cursor getAllNotes(Context context);

    /**
     * Return a {@link Cursor} positioned at the note that matches the given rowId
     * 
     * @param context
     *            The surrounding {@link Context}
     * @param rowId
     *            id of note to retrieve
     * @return {@link Cursor} positioned to matching note, if found
     * @throws SQLException
     *             if note could not be found/retrieved
     */
    public abstract Cursor getNoteById(Context context, long noteId) throws SQLException;

    /**
     * Return a {@link Cursor} positioned at the note that matches the given parentId
     * 
     * @param context
     *            The surrounding {@link Context}
     * @param parentId
     *            id of note's parent article
     * @return {@link Cursor} positioned to matching note, if found
     * @throws SQLException
     *             if note could not be found/retrieved
     */
    public abstract Cursor getNoteByParentId(Context context, long parentId) throws SQLException;

    /**
     * Update the note using the provided {@link NoteBean}. The note to be updated is specified using the
     * rowId, and it is altered to use the title and body values passed in
     * 
     * @param context
     *            The surrounding {@link Context}
     * @param noteBean
     *            The {@link NoteBean} used for updating note.
     * @return true if the note was successfully updated, false otherwise
     */
    public abstract boolean updateNote(Context context, NoteBean noteBean);

    /**
     * Create a new quiz question using the provided {@link QuizBean}. If the quiz question is successfully
     * created return the new rowId for that quiz question, otherwise return a -1 to indicate failure.
     * 
     * @param context
     *            The surrounding {@link Context}
     * @param quizBean
     *            The bean used for creating new quiz question.
     * @return rowId or -1 if failed
     */
    public abstract long createQuizQuestion(Context context, QuizBean quizBean);

    /**
     * Return a {@link Cursor} over the list of all quiz questions in the database
     * 
     * @param context
     *            The surrounding {@link Context}
     * @return {@link Cursor} over all quiz questions
     */
    public abstract Cursor getAllQuizQuestions(Context context);

    /**
     * Create a new article using the provided {@link ArticleBean}. If the article is successfully created
     * return the new rowId for that article, otherwise return a -1 to indicate failure.
     * 
     * @param context
     *            The surrounding {@link Context}
     * @param articleBean
     *            The bean used for creating new note.
     * @return rowId or -1 if failed
     */
    public abstract long createArticle(Context context, ArticleBean articleBean);

    /**
     * Return a {@link Cursor} over the list of all articles in the database
     * 
     * @param context
     *            The surrounding {@link Context}
     * @return {@link Cursor} over all articles
     */
    public abstract Cursor getAllArticles(Context context);

    /**
     * Return a {@link Cursor} positioned at the articles that matches the given rowId
     * 
     * @param context
     *            The surrounding {@link Context}
     * @param articleId
     *            id of article to retrieve
     * @return {@link Cursor} positioned to matching note, if found
     * @throws SQLException
     *             if note could not be found/retrieved
     */
    public abstract Cursor getArticleById(Context context, long articleId) throws SQLException;

}
