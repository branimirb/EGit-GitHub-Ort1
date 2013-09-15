
package com.bbisercic.ort1.database.dao.enums;

public enum ArticleType {

    LECTURE(1), EXERCICE(2);

    private int mArticleType;

    private ArticleType(int articleType) {
        this.mArticleType = articleType;
    }

    /**
     * Retrieves constant that represents one of supported articles.
     * 
     * @return The {@link Integer} representation of the specified article type.
     */
    public int getValue() {
        return mArticleType;
    }

}
