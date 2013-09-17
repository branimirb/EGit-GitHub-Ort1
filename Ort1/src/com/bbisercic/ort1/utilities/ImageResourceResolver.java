
package com.bbisercic.ort1.utilities;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.database.dao.enums.ArticleType;

public class ImageResourceResolver {

    public static int getMainMenuItemImage(int position) {
        switch (position) {
        case 0:
            return R.drawable.lectures_icon;
        case 1:
            return R.drawable.exercices_icon;
        case 2:
            return R.drawable.notes_icon;
        case 3:
            return R.drawable.quiz_icon;
        default:
            return R.drawable.ic_launcher;
        }
    }

    public static int getArticleImage(ArticleType articleType) {
        switch (articleType) {
        case LECTURE:
            return R.drawable.letter_l;
        case EXERCICE:
            return R.drawable.letter_e;
        default:
            return R.drawable.letter_l;
        }
    }
}
