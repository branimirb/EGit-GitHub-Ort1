
package com.bbisercic.ort1.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.database.dao.enums.ArticleType;
import com.bbisercic.ort1.fragments.ExercicePageFragment;
import com.bbisercic.ort1.utilities.ViewPagerAnimation;

public class SecondExercicePreviewFragmentActivity extends FragmentActivity {

    public static final String SECOND_EXERCICE_PREVIEW_ACTION = "com.bbisercic.ort1.SECOND_EXERCICE_PREVIEW";

    public static final String EXERCICE_ID_EXTRA_KEY = "EXERCICE_ID_EXTRA_KEY";

    public static final String EXERCICE_TITLE_EXTRA_KEY = "EXERCICE_TITLE_EXTRA_KEY";

    public static final int NUMBER_OF_PAGES = 7;

    /**
     * Intent request code used for creating new note.
     */
    private static final int ACTIVITY_ATTACH_NOTE = 0;

    private long mExerciceId;

    private String mExerciceTitle;

    private List<String> mPagesList;

    private PagerAdapter mPagerAdapter;

    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);
        setContentView(R.layout.quiz_layout);

        if (aIcicle == null) {
            Bundle bundle = getIntent().getExtras();
            mExerciceId = bundle.getLong(EXERCICE_ID_EXTRA_KEY);
            mExerciceTitle = bundle.getString(EXERCICE_TITLE_EXTRA_KEY);
        } else {
            mExerciceId = aIcicle.getLong(EXERCICE_ID_EXTRA_KEY);
            mExerciceTitle = aIcicle.getString(EXERCICE_TITLE_EXTRA_KEY);
        }

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mPagesList = loadPages();

        initializePagerAdapter();

        getActionBar().setTitle(mExerciceTitle);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(EXERCICE_ID_EXTRA_KEY, mExerciceId);
        outState.putString(EXERCICE_TITLE_EXTRA_KEY, mExerciceTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.slide_exercice_action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem countItem = menu.findItem(R.id.action_notes_count);

        TextView tv = (TextView) countItem.getActionView().findViewById(R.id.count_text);

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

        invalidateOptionsMenu();

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
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

    private List<String> loadPages() {
        final List<String> pagesList = new ArrayList<String>(NUMBER_OF_PAGES);
        pagesList.add(getString(R.string.exerciceUri_2_slide_1));
        pagesList.add(getString(R.string.exerciceUri_2_slide_2));
        pagesList.add(getString(R.string.exerciceUri_2_slide_3));
        pagesList.add(getString(R.string.exerciceUri_2_slide_4));
        pagesList.add(getString(R.string.exerciceUri_2_slide_5));
        pagesList.add(getString(R.string.exerciceUri_2_slide_6));
        pagesList.add(getString(R.string.exerciceUri_2_slide_7));
        return pagesList;
    }

    /**
     * Initialize the fragments to be paged
     */
    private void initializePagerAdapter() {
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(true, new ViewPagerAnimation());
        mViewPager.setOffscreenPageLimit(NUMBER_OF_PAGES);
    }

    /**
     * The <code>PagerAdapter</code> serves the fragments when paging.
     */
    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int index) {
            return ExercicePageFragment.newInstance(index + 1);
        }

        @Override
        public int getCount() {
            return mPagesList.size();
        }
    }
}
