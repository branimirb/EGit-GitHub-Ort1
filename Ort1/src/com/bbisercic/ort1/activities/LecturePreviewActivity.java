
package com.bbisercic.ort1.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.database.dao.enums.ArticleType;

public class LecturePreviewActivity extends Activity {

    public static final String LECTURE_PREVIEW_ACTION = "com.bbisercic.ort1.LECTURE_PREVIEW";

    public static final String LECTURE_ID_EXTRA_KEY = "LECTURE_ID_EXTRA_KEY";

    public static final String LECTURE_TITLE_EXTRA_KEY = "LECTURE_TITLE_EXTRA_KEY";

    public static final String LECTURE_URI_EXTRA_KEY = "LECTURE_URI_EXTRA_KEY";

    /**
     * Intent request code used for creating new note.
     */
    private static final int ACTIVITY_ATTACH_NOTE = 0;

    private WebView mLectureWebView;

    private long mLectureId;

    private String mLectureTitle;

    private Uri mLectureUri;

    @Override
    public void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);
        setContentView(R.layout.lecture_preview_layout);

        mLectureWebView = (WebView) findViewById(R.id.lecture_web_view);

        if (aIcicle == null) {
            Bundle bundle = getIntent().getExtras();
            mLectureId = bundle.getLong(LECTURE_ID_EXTRA_KEY);
            mLectureTitle = bundle.getString(LECTURE_TITLE_EXTRA_KEY);
            mLectureUri = Uri.parse(bundle.getString(LECTURE_URI_EXTRA_KEY));
        } else {
            mLectureId = aIcicle.getLong(LECTURE_ID_EXTRA_KEY);
            mLectureTitle = aIcicle.getString(LECTURE_TITLE_EXTRA_KEY);
            mLectureUri = Uri.parse(aIcicle.getString(LECTURE_URI_EXTRA_KEY));
        }

        getActionBar().setTitle(mLectureTitle);

        initializeWebView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(LECTURE_ID_EXTRA_KEY, mLectureId);
        outState.putString(LECTURE_TITLE_EXTRA_KEY, mLectureTitle);
        outState.putString(LECTURE_URI_EXTRA_KEY, mLectureUri.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_action_menu, menu);

        menu.findItem(R.id.action_notes_delete).setVisible(false);
        final MenuItem countItem = menu.findItem(R.id.action_notes_count);

        final TextView tv = (TextView) countItem.getActionView().findViewById(R.id.count_text);

        tv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                listNotes();
            }
        });

        updateCounterInActionMenu(countItem, tv);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
        case android.R.id.home:
            return handleBackPressed();

        case R.id.action_notes_add:
            composeNote();
            return true;

        case R.id.action_notes_count:
            listNotes();
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {

        case KeyEvent.KEYCODE_BACK:
            return handleBackPressed();

        default:
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == ACTIVITY_ATTACH_NOTE) {
            invalidateOptionsMenu();
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void composeNote() {
        Intent intent = new Intent(CreateOrEditNoteActivity.CREATE_NOTE_ACTION);
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_ID_EXTRA_KEY, mLectureId);
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_TITLE_EXTRA_KEY, mLectureTitle);
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_TYPE_EXTRA_KEY, ArticleType.LECTURE.getValue());
        startActivityForResult(intent, ACTIVITY_ATTACH_NOTE);
    }

    private void listNotes() {
        Intent intent = new Intent(MainMenuActivity.OPEN_NOTES_ACTION);
        intent.putExtra(MainMenuActivity.ACTION_BAR_TITLE_EXTRA, getString(R.string.notes_tite));
        startActivity(intent);
    }

    private boolean handleBackPressed() {
        if (mLectureWebView.canGoBack()) {
            mLectureWebView.goBack();
            return true;

        } else {
            finish();
            return true;
        }
    }

    private void initializeWebView() {
        mLectureWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(mLectureWebView, url);
            }
        });

        mLectureWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        mLectureWebView.getSettings().setAppCachePath(getCacheDir().getPath());
        mLectureWebView.getSettings().setAppCacheEnabled(true);
        mLectureWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        mLectureWebView.loadUrl(mLectureUri.toString());
    }

    private void updateCounterInActionMenu(MenuItem item, TextView textView) {
        final DaoInterface dao = DaoFactory.getInstance();
        final List<NoteBean> notes = dao.getNotesByParentId(this, mLectureId);

        if (notes == null || notes.isEmpty()) {
            item.setVisible(false);
        } else {
            textView.setText("" + notes.size());
            item.setVisible(true);
            item.setEnabled(true);
            item.setCheckable(true);
        }
    }

}
