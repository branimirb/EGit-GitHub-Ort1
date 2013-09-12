package com.bbisercic.ort1.database;

import android.provider.BaseColumns;

/**
 * All the important constants related to the application's database in one
 * place.
 */
public final class DatabaseConstants {

	// This class can not be instantiated.
	private DatabaseConstants() {
	}

	private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

	public static final String TABLE_NOTES = "notes";

	/**
	 * The name of the application's database file.
	 */
	public static final String DATABASE_NAME = "notepad.db";

	/**
	 * The current version of the database. If the user updates the application
	 * and this constant is changed (in relation to the version he/she
	 * previously had)
	 * {@link DatabaseHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)}
	 * will be called.
	 */
	public static final int DATABASE_VERSION = 1;

	/**
	 * Defines all column names used in the
	 * {@link DatabaseConstants#TABLE_NOTES}. Column _ID is implicitly provided
	 * trough the {@link BaseColumns} interface so it should not be defined.
	 */
	public static final class NotesInfo implements BaseColumns {

		// This class cannot be instantiated
		private NotesInfo() {
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
	}

	/**
	 * The database projection used for accessing notes table.
	 */
	public static final String[] NOTES_TABLE_PROJECTION = new String[] {
			NotesInfo._ID, NotesInfo.COLUMN_TITLE, NotesInfo.COLUMN_BODY };

	/**
	 * Query used for creating database table
	 * {@link DatabaseConstants#TABLE_NOTES}.
	 */
	public static final String CREATE_TABLE_NOTES = "CREATE TABLE "
			+ TABLE_NOTES + " (" + NotesInfo._ID + " INTEGER PRIMARY KEY, "
			+ NotesInfo.COLUMN_TITLE + " TEXT NOT NULL, "
			+ NotesInfo.COLUMN_BODY + " TEXT NOT NULL" + ")";

	/**
	 * Query used for dropping database table
	 * {@link DatabaseConstants#TABLE_NOTES}.
	 */
	public static final String DROP_TABLE_NOTES = DROP_TABLE + TABLE_NOTES
			+ ";";

}