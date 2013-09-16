
package com.bbisercic.ort1.database.dao.extractors;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.bbisercic.ort1.database.DatabaseConstants.NoteInfo;
import com.bbisercic.ort1.database.dao.beans.NoteBean;

/**
 * Class for extracting fetched database data from {@link Cursor} into {@link NoteBean}.
 */
public class NoteBeanCursorExtractor {

    /**
     * Packs data from a Cursor row into a {@link NoteBean}
     * 
     * @param cursor
     *            The surrounding {@link Context}.
     * @return The {@link NoteBean} that holds the data from the Cursor's row.
     */
    private static NoteBean extractNoteBeanFromCursorRow(Cursor cursor) {
        NoteBean noteBean = new NoteBean();
        extractFromCursorRowToNoteBean(cursor, noteBean);
        return noteBean;
    }

    private static void extractFromCursorRowToNoteBean(Cursor cursor, NoteBean noteBean) {

        long rowId = cursor.getLong(cursor.getColumnIndex(NoteInfo._ID));
        long articleId = cursor.getLong(cursor.getColumnIndex(NoteInfo.COLUMN_PARENT_ID));
        long timestamp = cursor.getLong(cursor.getColumnIndex(NoteInfo.COLUMN_TIMESTAMP));
        String title = cursor.getString(cursor.getColumnIndex(NoteInfo.COLUMN_TITLE));
        String body = cursor.getString(cursor.getColumnIndex(NoteInfo.COLUMN_BODY));
        String articleTitle = cursor.getString(cursor.getColumnIndex(NoteInfo.COLUMN_PARENT_TITLE));

        noteBean.setId(rowId);
        noteBean.setArticleId(articleId);
        noteBean.setTimestamp(timestamp);
        noteBean.setTitle(title);
        noteBean.setBody(body);
        noteBean.setArticleTitle(articleTitle);
    }

    public static NoteBean extractNoteInfoIntoBean(Cursor cursor) {
        NoteBean noteBean = null;
        if (cursor.moveToFirst()) {
            noteBean = extractNoteBeanFromCursorRow(cursor);
        }
        cursor.close();
        return noteBean;
    }

    /**
     * Packs data from a {@link Cursor} row into a list of {@link NoteBean}.
     * 
     * @param cursor
     *            he surrounding {@link Context}.
     * @return The list of {@link NoteBean} that holds the {@link Cursor} rows' data.
     */
    public static List<NoteBean> extractNoteInfoIntoList(Cursor cursor) {
        ArrayList<NoteBean> list = new ArrayList<NoteBean>();
        while (cursor.moveToNext()) {
            list.add(extractNoteBeanFromCursorRow(cursor));
        }
        cursor.close();
        return list;
    }

}
