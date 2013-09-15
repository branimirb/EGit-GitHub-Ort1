
package com.bbisercic.ort1.database.dao.beans;

import com.bbisercic.ort1.database.dao.enums.ArticleType;

import android.net.Uri;

public class ArticleBean {

    private long mId;

    private String mTitle;

    private Uri mUri;

    private ArticleType mArticleType;

    public ArticleBean(long mId, String mTitle, Uri mUri, ArticleType mArticleType) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mUri = mUri;
        this.mArticleType = mArticleType;
    }

    public ArticleBean(String mTitle, Uri mUri, ArticleType mArticleType) {
        this(0, mTitle, mUri, mArticleType);
    }

    public ArticleBean() {
        this(0, "n/a", Uri.parse("file:///android_asset/default.html"), ArticleType.LECTURE);
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri mUri) {
        this.mUri = mUri;
    }

    public ArticleType getArticleType() {
        return mArticleType;
    }

    public void setArticleType(ArticleType mArticleType) {
        this.mArticleType = mArticleType;
    }

    @Override
    public String toString() {
        return "ArticleBean [mId=" + mId + ", mTitle=" + mTitle + ", mUri=" + mUri + ", mArticleType="
                + mArticleType + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mArticleType == null) ? 0 : mArticleType.hashCode());
        result = prime * result + (int) (mId ^ (mId >>> 32));
        result = prime * result + ((mTitle == null) ? 0 : mTitle.hashCode());
        result = prime * result + ((mUri == null) ? 0 : mUri.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArticleBean other = (ArticleBean) obj;
        if (mArticleType != other.mArticleType)
            return false;
        if (mId != other.mId)
            return false;
        if (mTitle == null) {
            if (other.mTitle != null)
                return false;
        } else if (!mTitle.equals(other.mTitle))
            return false;
        if (mUri == null) {
            if (other.mUri != null)
                return false;
        } else if (!mUri.equals(other.mUri))
            return false;
        return true;
    }

}
