
package com.bbisercic.ort1.database.dao.enums;

public enum ArticleType {

    UNASSIGNED(0), LECTURE(1), EXERCICE(2);

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

    public static ArticleType getTypeForValue(int value) {
        switch (value) {
        case 0:
            return UNASSIGNED;
        case 1:
            return LECTURE;
        case 2:
            return EXERCICE;
        default:
            return UNASSIGNED;
        }
    }

}
