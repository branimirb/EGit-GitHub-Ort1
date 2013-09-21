
package com.bbisercic.ort1.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.QuizBean;
import com.bbisercic.ort1.fragments.QuizQuestionFragment;
import com.bbisercic.ort1.utilities.ViewPagerAnimation;

/**
 * The <code>ViewPagerFragmentActivity</code> class is the fragment activity hosting the ViewPager
 */
public class QuizFragmentActivity extends FragmentActivity {

    private static final int NUMBER_OF_QUESTIONS = 5;

    private List<QuizBean> mQuizQuestionsList;

    private int mCurrentPage = 0;

    private PagerAdapter mPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);

        setContentView(R.layout.quiz_layout);
        
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mQuizQuestionsList = loadQuestions();

        initializePagerAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.action_notes_delete).setVisible(false);
        menu.findItem(R.id.action_notes_add).setVisible(false);

        MenuItem countItem = menu.findItem(R.id.action_notes_count);

        TextView tv = (TextView) countItem.getActionView().findViewById(R.id.count_text);

        if (mQuizQuestionsList == null || mQuizQuestionsList.isEmpty()) {
            countItem.setVisible(false);
        } else {
            tv.setText("" + mCurrentPage + "/" + mQuizQuestionsList.size());
        }

        countItem.setEnabled(false);

        invalidateOptionsMenu();

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private List<QuizBean> loadQuestions() {
        DaoInterface dao = DaoFactory.getInstance();
        final List<QuizBean> quizQuestions = new ArrayList<QuizBean>(NUMBER_OF_QUESTIONS);
        final List<QuizBean> allQuestions = dao.getAllQuizQuestions(this);

        Random random = new Random();
        int count = 0;
        while (count < NUMBER_OF_QUESTIONS) {
            int index = random.nextInt(allQuestions.size());

            QuizBean bean = allQuestions.get(index);
            if (quizQuestions.contains(bean)) {
                continue;
            }

            quizQuestions.add(bean);
            count++;
        }

        return quizQuestions;
    }

    /**
     * Initialize the fragments to be paged
     */
    private void initializePagerAdapter() {
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());        
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(true, new ViewPagerAnimation());
    }

    /**
     * The <code>PagerAdapter</code> serves the fragments when paging.
     */
    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            mCurrentPage = position;
            invalidateOptionsMenu();
            return QuizQuestionFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return mQuizQuestionsList.size();
        }
    }

}
