
package com.bbisercic.ort1.database.dao.beans;

import com.bbisercic.ort1.database.dao.enums.ArticleType;

public class NoteBean {

    private long mId;

    private String mTitle;

    private String mBody;

    private long mTimestamp;

    private String mArticleTitle;

    private long mArticleId;

    private ArticleType mArticleType;

    public NoteBean(long mId, String mTitle, String mBody, long mTimestamp, String mArticleTitle,
            long mArticleId, ArticleType articleType) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mBody = mBody;
        this.mTimestamp = mTimestamp;
        this.mArticleTitle = mArticleTitle;
        this.mArticleId = mArticleId;
        this.mArticleType = articleType;
    }

    public NoteBean(String mTitle, String mBody, long mTimestamp, String mArticleTitle, long mArticleId,
            ArticleType articleType) {
        this(0, mTitle, mBody, mTimestamp, mArticleTitle, mArticleId, articleType);
    }

    public NoteBean() {
        this(0, "n/a", "n/a", System.currentTimeMillis(), "n/a", 0, ArticleType.UNASSIGNED);
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

    public String getBody() {
        return mBody;
    }

    public void setBody(String mBody) {
        this.mBody = mBody;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(long mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public String getArticleTitle() {
        return mArticleTitle;
    }

    public void setArticleTitle(String mArticleTitle) {
        this.mArticleTitle = mArticleTitle;
    }

    public long getArticleId() {
        return mArticleId;
    }

    public void setArticleId(long mArticleId) {
        this.mArticleId = mArticleId;
    }

    public ArticleType getArticleType() {
        return mArticleType;
    }

    public void setArticleType(ArticleType mArticleType) {
        this.mArticleType = mArticleType;
    }

    @Override
    public String toString() {
        return "NoteBean [mId=" + mId + ", mTitle=" + mTitle + ", mBody=" + mBody + ", mTimestamp="
                + mTimestamp + ", mArticleTitle=" + mArticleTitle + ", mArticleId=" + mArticleId
                + ", mArticleType=" + mArticleType + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (mArticleId ^ (mArticleId >>> 32));
        result = prime * result + ((mArticleTitle == null) ? 0 : mArticleTitle.hashCode());
        result = prime * result + ((mArticleType == null) ? 0 : mArticleType.hashCode());
        result = prime * result + ((mBody == null) ? 0 : mBody.hashCode());
        result = prime * result + (int) (mId ^ (mId >>> 32));
        result = prime * result + (int) (mTimestamp ^ (mTimestamp >>> 32));
        result = prime * result + ((mTitle == null) ? 0 : mTitle.hashCode());
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
        NoteBean other = (NoteBean) obj;
        if (mArticleId != other.mArticleId)
            return false;
        if (mArticleTitle == null) {
            if (other.mArticleTitle != null)
                return false;
        } else if (!mArticleTitle.equals(other.mArticleTitle))
            return false;
        if (mArticleType != other.mArticleType)
            return false;
        if (mBody == null) {
            if (other.mBody != null)
                return false;
        } else if (!mBody.equals(other.mBody))
            return false;
        if (mId != other.mId)
            return false;
        if (mTimestamp != other.mTimestamp)
            return false;
        if (mTitle == null) {
            if (other.mTitle != null)
                return false;
        } else if (!mTitle.equals(other.mTitle))
            return false;
        return true;
    }

}
