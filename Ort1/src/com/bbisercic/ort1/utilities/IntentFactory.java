
package com.bbisercic.ort1.utilities;

import android.content.Context;
import android.content.Intent;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.activities.FirstExercicePreviewActivity;
import com.bbisercic.ort1.activities.LecturePreviewActivity;
import com.bbisercic.ort1.activities.SecondExercicePreviewFragmentActivity;
import com.bbisercic.ort1.database.dao.beans.ArticleBean;

public final class IntentFactory {

    /**
     * Create custom Intent for the exercises preview.
     * 
     * @param context
     *            The surrounding context.
     * @param article
     *            The {@link ArticleBean} used for creating.
     * @return The {@link Intent} for launching specific type of exercises preview
     */
    public static Intent createIntentForExercice(Context context, ArticleBean article) {
        final Intent intent = new Intent();

        if (context.getString(R.string.exerciceTitle_1).equals(article.getTitle())) {
            intent.setAction(FirstExercicePreviewActivity.FIRST_EXERCICE_PREVIEW_ACTION);
            intent.putExtra(FirstExercicePreviewActivity.EXERCICE_ID_EXTRA_KEY, article.getId());
            intent.putExtra(FirstExercicePreviewActivity.EXERCICE_TITLE_EXTRA_KEY, article.getTitle());
            intent.putExtra(FirstExercicePreviewActivity.EXERCICE_URI_EXTRA_KEY, article.getUri().toString());

        } else if (context.getString(R.string.exerciceTitle_2).equals(article.getTitle())) {
            intent.setAction(SecondExercicePreviewFragmentActivity.SECOND_EXERCICE_PREVIEW_ACTION);
            intent.putExtra(SecondExercicePreviewFragmentActivity.EXERCICE_ID_EXTRA_KEY, article.getId());
            intent.putExtra(SecondExercicePreviewFragmentActivity.EXERCICE_TITLE_EXTRA_KEY,
                    article.getTitle());
        } else if (context.getString(R.string.lecureTitle_1).equals(article.getTitle())) {
            intent.setAction(LecturePreviewActivity.LECTURE_PREVIEW_ACTION);
            intent.putExtra(LecturePreviewActivity.LECTURE_ID_EXTRA_KEY, article.getId());
            intent.putExtra(LecturePreviewActivity.LECTURE_TITLE_EXTRA_KEY, article.getTitle());
            intent.putExtra(LecturePreviewActivity.LECTURE_URI_EXTRA_KEY, article.getUri().toString());
        }

        return intent;
    }
}
