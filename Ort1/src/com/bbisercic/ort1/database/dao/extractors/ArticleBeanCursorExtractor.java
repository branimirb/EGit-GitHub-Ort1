package com.bbisercic.ort1.database.dao.extractors;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.bbisercic.ort1.database.DatabaseConstants.ArticleInfo;
import com.bbisercic.ort1.database.dao.beans.ArticleBean;
import com.bbisercic.ort1.database.dao.enums.ArticleType;

/**
 *  Class for extracting fetched database data from {@link Cursor} into {@link ArticleBean}.
 */
public class ArticleBeanCursorExtractor {
    
    /**
     * Packs data from a Cursor row into a {@link ArticleBean}
     * 
     * @param cursor
     *            The surrounding {@link Context}.
     * @return The {@link ArticleBean} that holds the data from the Cursor's row.
     */
    private static ArticleBean extractArticleBeanFromCursorRow(Cursor cursor) {
        ArticleBean articleBean = new ArticleBean();
        extractFromCursorRowToArticleBean(cursor, articleBean);
        return articleBean;
    }

    private static void extractFromCursorRowToArticleBean(Cursor cursor, ArticleBean articleBean) {

        final long rowId = cursor.getLong(cursor.getColumnIndex(ArticleInfo._ID));
        final String title = cursor.getString(cursor.getColumnIndex(ArticleInfo.COLUMN_TITLE));
        final String uriString = cursor.getString(cursor.getColumnIndex(ArticleInfo.COLUMN_URI));
        final int type = cursor.getInt(cursor.getColumnIndex(ArticleInfo.COLUMN_TYPE));

        articleBean.setId(rowId);
        articleBean.setTitle(title);
        articleBean.setUri(Uri.parse(uriString));
        articleBean.setArticleType(ArticleType.getTypeForValue(type));
    }

    public static ArticleBean extractArticleInfoIntoBean(Cursor cursor) {
        ArticleBean articleBean = null;
        if (cursor.moveToFirst()) {
            articleBean = extractArticleBeanFromCursorRow(cursor);
        }
        cursor.close();
        return articleBean;
    }

    /**
     * Packs data from a {@link Cursor} row into a list of {@link ArticleBean}.
     * 
     * @param cursor
     *            he surrounding {@link Context}.
     * @return The list of {@link ArticleBean} that holds the {@link Cursor} rows' data.
     */
    public static List<ArticleBean> extractArticleInfoIntoList(Cursor cursor) {
        ArrayList<ArticleBean> list = new ArrayList<ArticleBean>();
        while (cursor.moveToNext()) {
            list.add(extractArticleBeanFromCursorRow(cursor));
        }
        cursor.close();
        return list;
    }
}
