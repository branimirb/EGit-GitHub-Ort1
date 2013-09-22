
package com.bbisercic.ort1.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import com.bbisercic.ort1.database.DatabaseConstants.ArticleInfo;
import com.bbisercic.ort1.database.DatabaseConstants.NoteInfo;
import com.bbisercic.ort1.database.DatabaseConstants.QuizeInfo;
import com.bbisercic.ort1.database.dao.enums.ArticleType;

/**
 * The low-level class for handling the database queries - INSERTs, UPDATEs, DELETEs and SELECTs.
 */
public class DatabaseAdapter {

    // This class can not be instantiated.
    private DatabaseAdapter() {
    }

    /**
     * Symbolic representation for the SQL operation error.
     */
    public static final int SQL_ERROR = -1;

    public static long insertQuizQuestion(Context context, String question, String answer) {

        ContentValues values = new ContentValues();
        values.put(QuizeInfo.COLUMN_QUESTION, question);
        values.put(QuizeInfo.COLUMN_ANSWER, answer);

        return DatabaseFactory.getInstance(context).insert(DatabaseConstants.TABLE_QUIZ, null, values);
    }

    public static long insertArticle(Context context, String title, Uri uri, ArticleType articleType) {

        ContentValues values = new ContentValues();
        values.put(ArticleInfo.COLUMN_TITLE, title);
        values.put(ArticleInfo.COLUMN_URI, uri.toString());
        values.put(ArticleInfo.COLUMN_TYPE, articleType.getValue());

        return DatabaseFactory.getInstance(context).insert(DatabaseConstants.TABLE_ARTICLES, null, values);
    }

    public static long insertNote(Context context, String title, String body, long articleId,
            String articleTitle, long timestamp, ArticleType articleType) {

        ContentValues values = new ContentValues();
        values.put(NoteInfo.COLUMN_TITLE, title);
        values.put(NoteInfo.COLUMN_BODY, body);
        values.put(NoteInfo.COLUMN_PARENT_ID, articleId);
        values.put(NoteInfo.COLUMN_PARENT_TITLE, articleTitle);
        values.put(NoteInfo.COLUMN_TIMESTAMP, timestamp);
        values.put(NoteInfo.COLUMN_PARENT_TYPE, articleType.getValue());

        return DatabaseFactory.getInstance(context).insert(DatabaseConstants.TABLE_NOTES, null, values);
    }

    public static boolean deleteNoteById(Context context, long rowId) {
        final String table = DatabaseConstants.TABLE_NOTES;
        final String whereClause = NoteInfo._ID + " =?";
        final String[] whereArgs = { "" + rowId };

        return DatabaseFactory.getInstance(context).delete(table, whereClause, whereArgs) > 0;
    }

    public static boolean deleteNotesByParentId(Context context, long parentId) {
        final String table = DatabaseConstants.TABLE_NOTES;
        final String whereClause = NoteInfo.COLUMN_PARENT_ID + " =?";
        final String[] whereArgs = { "" + parentId };

        return DatabaseFactory.getInstance(context).delete(table, whereClause, whereArgs) > 0;
    }

    public static boolean deleteNotesById(Context context, Set<Long> ids) {
        final String table = DatabaseConstants.TABLE_NOTES;
        StringBuilder sb = new StringBuilder(NoteInfo._ID);
        sb.append(" in (");
        String[] whereArgs = new String[ids.size()];
        List<Long> list = new ArrayList<Long>(ids);
        for (int i = 0; i < list.size(); i++) {
            long id = list.get(i);
            whereArgs[i] = "" + id;
            sb.append(" ?");
            if (i < ids.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(");");
        return DatabaseFactory.getInstance(context).delete(table, sb.toString(), whereArgs) > 0;
    }

    public static boolean deleteAllNotes(Context context) {
        return DatabaseFactory.getInstance(context).delete(DatabaseConstants.TABLE_NOTES, null, null) > 0;
    }

    public static Cursor getAllNotes(Context context) {
        final String table = DatabaseConstants.TABLE_NOTES;
        final String[] projection = DatabaseConstants.TABLE_NOTES_PROJECTION;

        return DatabaseFactory.getInstance(context).query(table, projection, null, null, null, null, null);
    }

    public static Cursor getNoteById(Context context, long rowId) throws SQLException {

        final String table = DatabaseConstants.TABLE_NOTES;
        final String[] projection = DatabaseConstants.TABLE_NOTES_PROJECTION;
        final String whereClause = NoteInfo._ID + " =?";
        final String[] whereArgs = { "" + rowId };

        Cursor cursor = DatabaseFactory.getInstance(context).query(true, table, projection, whereClause,
                whereArgs, null, null, null, null);

        return cursor;
    }

    public static Cursor getNotesByParentId(Context context, long parentId) throws SQLException {

        final String table = DatabaseConstants.TABLE_NOTES;
        final String[] projection = DatabaseConstants.TABLE_NOTES_PROJECTION;
        final String whereClause = NoteInfo.COLUMN_PARENT_ID + " =?";
        final String[] whereArgs = { "" + parentId };

        Cursor cursor = DatabaseFactory.getInstance(context).query(true, table, projection, whereClause,
                whereArgs, null, null, null, null);

        return cursor;
    }

    public static boolean updateNote(Context context, long rowId, String title, String body, long articleId,
            String articleTitle, long timestamp) {

        ContentValues values = new ContentValues();
        values.put(NoteInfo.COLUMN_TITLE, title);
        values.put(NoteInfo.COLUMN_BODY, body);
        values.put(NoteInfo.COLUMN_PARENT_ID, articleId);
        values.put(NoteInfo.COLUMN_PARENT_TITLE, articleTitle);
        values.put(NoteInfo.COLUMN_TIMESTAMP, timestamp);

        final String table = DatabaseConstants.TABLE_NOTES;
        final String whereClause = NoteInfo._ID + " =?";
        final String[] whereArgs = { "" + rowId };

        return DatabaseFactory.getInstance(context).update(table, values, whereClause, whereArgs) > 0;
    }

    public static Cursor getAllArticles(Context context) {
        final String table = DatabaseConstants.TABLE_ARTICLES;
        final String[] projection = DatabaseConstants.TABLE_ARTICLES_PROJECTION;

        return DatabaseFactory.getInstance(context).query(table, projection, null, null, null, null, null);
    }

    public static Cursor getArticleById(Context context, long rowId) throws SQLException {

        final String table = DatabaseConstants.TABLE_ARTICLES;
        final String[] projection = DatabaseConstants.TABLE_ARTICLES_PROJECTION;
        final String whereClause = ArticleInfo._ID + " =?";
        final String[] whereArgs = { "" + rowId };

        Cursor cursor = DatabaseFactory.getInstance(context).query(true, table, projection, whereClause,
                whereArgs, null, null, null, null);

        return cursor;
    }

    public static Cursor getArticlesByType(Context context, ArticleType articleType) throws SQLException {

        final String table = DatabaseConstants.TABLE_ARTICLES;
        final String[] projection = DatabaseConstants.TABLE_ARTICLES_PROJECTION;
        final String whereClause = ArticleInfo.COLUMN_TYPE + " =?";
        final String[] whereArgs = { "" + articleType.getValue() };

        Cursor cursor = DatabaseFactory.getInstance(context).query(true, table, projection, whereClause,
                whereArgs, null, null, null, null);

        return cursor;
    }

    public static Cursor getAllQuizQuestions(Context context) {
        final String table = DatabaseConstants.TABLE_QUIZ;
        final String[] projection = DatabaseConstants.TABLE_QUIZ_PROJECTION;

        return DatabaseFactory.getInstance(context).query(table, projection, null, null, null, null, null);
    }

}
