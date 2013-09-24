
package com.bbisercic.ort1.activities;

import java.util.List;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.database.dao.enums.ArticleType;

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

public class FirstExercicePreviewActivity extends Activity {

    public static final String FIRST_EXERCICE_PREVIEW_ACTION = "com.bbisercic.ort1.FIRST_EXERCICE_PREVIEW";

    public static final String EXERCICE_ID_EXTRA_KEY = "EXERCICE_ID_EXTRA_KEY";

    public static final String EXERCICE_TITLE_EXTRA_KEY = "EXERCICE_TITLE_EXTRA_KEY";

    public static final String EXERCICE_URI_EXTRA_KEY = "EXERCICE_URI_EXTRA_KEY";

    /**
     * Intent request code used for creating new note.
     */
    private static final int ACTIVITY_ATTACH_NOTE = 0;

    private WebView mExerciceWebView;

    private long mExerciceId;

    private String mExerciceTitle;

    private Uri mExerciceUri;

    @Override
    public void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);
        setContentView(R.layout.first_exercice_preview_layout);

        mExerciceWebView = (WebView) findViewById(R.id.exercice_web_view);

        if (aIcicle == null) {
            Bundle bundle = getIntent().getExtras();
            mExerciceId = bundle.getLong(EXERCICE_ID_EXTRA_KEY);
            mExerciceTitle = bundle.getString(EXERCICE_TITLE_EXTRA_KEY);
            mExerciceUri = Uri.parse(bundle.getString(EXERCICE_URI_EXTRA_KEY));
        } else {
            mExerciceId = aIcicle.getLong(EXERCICE_ID_EXTRA_KEY);
            mExerciceTitle = aIcicle.getString(EXERCICE_TITLE_EXTRA_KEY);
            mExerciceUri = Uri.parse(aIcicle.getString(EXERCICE_URI_EXTRA_KEY));
        }

        getActionBar().setTitle(mExerciceTitle);

        InitializeWebView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(EXERCICE_ID_EXTRA_KEY, mExerciceId);
        outState.putString(EXERCICE_TITLE_EXTRA_KEY, mExerciceTitle);
        outState.putString(EXERCICE_URI_EXTRA_KEY, mExerciceUri.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_action_menu, menu);
        menu.findItem(R.id.action_notes_delete).setVisible(false);
        updateCounterActionBar(menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void updateCounterActionBar(Menu menu) {
        final MenuItem countItem = menu.findItem(R.id.action_notes_count);

        final TextView tv = (TextView) countItem.getActionView().findViewById(R.id.count_text);

        tv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                listNotes();
            }
        });

        DaoInterface dao = DaoFactory.getInstance();
        final List<NoteBean> notes = dao.getNotesByParentId(this, mExerciceId);

        if (notes == null || notes.isEmpty()) {
            countItem.setVisible(false);
        } else {
            tv.setText("" + notes.size());
            countItem.setVisible(true);
            countItem.setEnabled(true);
            countItem.setCheckable(true);
        }
    }

    // @Override
    // public boolean onPrepareOptionsMenu(Menu menu) {
    //
    // menu.findItem(R.id.action_notes_delete).setVisible(false);
    //
    // MenuItem countItem = menu.findItem(R.id.action_notes_count);
    //
    // TextView tv = (TextView) countItem.getActionView().findViewById(R.id.count_text);
    //
    // tv.setOnClickListener(new OnClickListener() {
    //
    // @Override
    // public void onClick(View v) {
    // listNotes();
    // }
    // });
    //
    // DaoInterface dao = DaoFactory.getInstance();
    // final List<NoteBean> notes = dao.getNotesByParentId(this, mExerciceId);
    //
    // if (notes == null || notes.isEmpty()) {
    // countItem.setVisible(false);
    // } else {
    // tv.setText("" + notes.size());
    // countItem.setVisible(true);
    // countItem.setEnabled(true);
    // countItem.setCheckable(true);
    // }
    //
    // invalidateOptionsMenu();
    //
    // return super.onPrepareOptionsMenu(menu);
    // }

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
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_ID_EXTRA_KEY, mExerciceId);
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_TITLE_EXTRA_KEY, mExerciceTitle);
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_TYPE_EXTRA_KEY, ArticleType.EXERCICE.getValue());
        startActivityForResult(intent, ACTIVITY_ATTACH_NOTE);
    }

    private void listNotes() {
        Intent intent = new Intent(MainMenuActivity.OPEN_NOTES_ACTION);
        intent.putExtra(MainMenuActivity.ACTION_BAR_TITLE_EXTRA, getString(R.string.notes_tite));
        startActivity(intent);
    }

    private boolean handleBackPressed() {
        if (mExerciceWebView.canGoBack()) {
            mExerciceWebView.goBack();
            return true;

        } else {
            finish();
            return true;
        }
    }

    private void InitializeWebView() {
        mExerciceWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(mExerciceWebView, url);
            }
        });

        mExerciceWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        mExerciceWebView.getSettings().setAppCachePath(getCacheDir().getPath());
        mExerciceWebView.getSettings().setAppCacheEnabled(true);
        mExerciceWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        mExerciceWebView.loadUrl(mExerciceUri.toString());
    }
}
