
package com.bbisercic.ort1.database;

import android.provider.BaseColumns;

/**
 * All the important constants related to the application's database in one place.
 */
public final class DatabaseConstants {

    // This class can not be instantiated.
    private DatabaseConstants() {
    }

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    public static final String TABLE_NOTES = "Notes";

    public static final String TABLE_ARTICLES = "Article";

    public static final String TABLE_QUIZ = "Quiz";

    /**
     * The name of the application's database file.
     */
    public static final String DATABASE_NAME = "ort1.db";

    /**
     * The current version of the database. If the user updates the application and this constant is changed
     * (in relation to the version he/she previously had)
     * {@link DatabaseHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)} will be called.
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * Defines all column names used in the {@link DatabaseConstants#TABLE_ARTICLES}. Column _ID is implicitly
     * provided trough the {@link BaseColumns} interface so it should not be defined.
     */
    public static final class ArticleInfo implements BaseColumns {

        // This class cannot be instantiated
        private ArticleInfo() {
        }

        /**
         * Table column used for defining article's title.
         * <p>
         * Content type: TEXT
         * </p>
         */
        public static final String COLUMN_TITLE = "title";

        /**
         * Table column used for defining URI of article provided trough assets file.
         * <p>
         * Content type: TEXT
         * </p>
         */
        public static final String COLUMN_URI = "uri";

        /**
         * Table column used for defining type of article.
         * <p>
         * Content type: INTEGER
         * </p>
         */
        public static final String COLUMN_TYPE = "type";
    }

    /**
     * Defines all column names used in the {@link DatabaseConstants#TABLE_QUIZ}. Column _ID is implicitly
     * provided trough the {@link BaseColumns} interface so it should not be defined.
     */
    public static final class QuizeInfo implements BaseColumns {

        // This class cannot be instantiated
        private QuizeInfo() {
        }

        /**
         * Table column used for defining question.
         * <p>
         * Content type: TEXT
         * </p>
         */
        public static final String COLUMN_QUESTION = "question";

        /**
         * Table column used for defining answer.
         * <p>
         * Content type: TEXT
         * </p>
         */
        public static final String COLUMN_ANSWER = "answer";
    }

    /**
     * Defines all column names used in the {@link DatabaseConstants#TABLE_NOTES}. Column _ID is implicitly
     * provided trough the {@link BaseColumns} interface so it should not be defined.
     */
    public static final class NoteInfo implements BaseColumns {

        // This class cannot be instantiated
        private NoteInfo() {
        }

        /**
         * Table column used for defining note's title.
         * <p>
         * Content type: TEXT
         * </p>
         */
        public static final String COLUMN_TITLE = "title";

        /**
         * Table column used for defining note's body.
         * <p>
         * Content type: TEXT
         * </p>
         */
        public static final String COLUMN_BODY = "body";

        /**
         * Table column used for defining note's timestamp.
         * <p>
         * Content type: INTEGER
         * </p>
         */
        public static final String COLUMN_TIMESTAMP = "timestamp";

        /**
         * Table column used for defining title of belonging article.
         * <p>
         * Content type: TEXT
         * </p>
         */
        public static final String COLUMN_PARENT_TITLE = "parent_title";

        /**
         * Table column used for defining id of the belonging article.
         * <p>
         * Content type: INTEGER
         * </p>
         */
        public static final String COLUMN_PARENT_ID = "parent_id";

        /**
         * Table column used for defining type of article.
         * <p>
         * Content type: INTEGER
         * </p>
         */
        public static final String COLUMN_PARENT_TYPE = "type";
    }
    
    /**
     * The database projection used for accessing articles table.
     */
    public static final String[] TABLE_ARTICLES_PROJECTION = 
            new String[] {
                    ArticleInfo._ID,
                    ArticleInfo.COLUMN_TITLE,
                    ArticleInfo.COLUMN_URI,
                    ArticleInfo.COLUMN_TYPE};
    
    /**
     * Query used for creating database table {@link DatabaseConstants#TABLE_ARTICLES}.
     */
    public static final String CREATE_TABLE_ARTICLES = 
            "CREATE TABLE " + TABLE_ARTICLES + " (" + 
                    ArticleInfo._ID + " INTEGER PRIMARY KEY, " + 
                    ArticleInfo.COLUMN_TITLE + " TEXT NOT NULL, " + 
                    ArticleInfo.COLUMN_URI + " TEXT NOT NULL, " +
                    ArticleInfo.COLUMN_TYPE + " INTEGER NOT NULL );";   
    
    /**
     * Query used for dropping database table {@link DatabaseConstants#TABLE_ARTICLES}.
     */
    public static final String DROP_TABLE_ARTICLES = DROP_TABLE + TABLE_ARTICLES + ";";
        
    /**
     * The database projection used for accessing quiz table.
     */
    public static final String[] TABLE_QUIZ_PROJECTION = 
            new String[] { 
                    QuizeInfo._ID,
                    QuizeInfo.COLUMN_QUESTION,
                    QuizeInfo.COLUMN_ANSWER};
    
    /**
     * Query used for creating database table {@link DatabaseConstants#TABLE_QUIZ}.
     */
    public static final String CREATE_TABLE_QUIZ = 
            "CREATE TABLE " + TABLE_QUIZ + " (" + 
                    QuizeInfo._ID + " INTEGER PRIMARY KEY, " + 
                    QuizeInfo.COLUMN_QUESTION + " TEXT NOT NULL, " + 
                    QuizeInfo.COLUMN_ANSWER + " TEXT NOT NULL );";   
    
    /**
     * Query used for dropping database table {@link DatabaseConstants#TABLE_QUIZ}.
     */
    public static final String DROP_TABLE_QUIZ = DROP_TABLE + TABLE_QUIZ + ";";

    /**
     * The database projection used for accessing notes table.
     */
    public static final String[] TABLE_NOTES_PROJECTION = 
            new String[] { 
                    NoteInfo._ID,
                    NoteInfo.COLUMN_TITLE,
                    NoteInfo.COLUMN_BODY,
                    NoteInfo.COLUMN_TIMESTAMP,
                    NoteInfo.COLUMN_PARENT_ID,
                    NoteInfo.COLUMN_PARENT_TITLE,
                    NoteInfo.COLUMN_PARENT_TYPE};

    /**
     * Query used for creating database table {@link DatabaseConstants#TABLE_NOTES}.
     */
    public static final String CREATE_TABLE_NOTES = 
            "CREATE TABLE " + TABLE_NOTES + " (" + 
                    NoteInfo._ID + " INTEGER PRIMARY KEY, " + 
                    NoteInfo.COLUMN_TITLE + " TEXT NOT NULL, " + 
                    NoteInfo.COLUMN_BODY + " TEXT NOT NULL, " + 
                    NoteInfo.COLUMN_TIMESTAMP + " INTEGER NOT NULL, " + 
                    NoteInfo.COLUMN_PARENT_ID + " INTEGER NOT NULL, " + 
                    NoteInfo.COLUMN_PARENT_TITLE + " TEXT NOT NULL, " +
                    NoteInfo.COLUMN_PARENT_TYPE + " INTEGER NOT NULL);";

    /**
     * Query used for dropping database table {@link DatabaseConstants#TABLE_NOTES}.
     */
    public static final String DROP_TABLE_NOTES = DROP_TABLE + TABLE_NOTES + ";";

}