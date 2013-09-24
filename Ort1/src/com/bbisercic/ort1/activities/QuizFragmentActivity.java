
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

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.QuizBean;
import com.bbisercic.ort1.fragments.QuizQuestionFragment;
import com.bbisercic.ort1.utilities.ViewPagerAnimation;

public class QuizFragmentActivity extends FragmentActivity {

    public static final int NUMBER_OF_QUESTIONS = 5;

    private List<QuizBean> mQuizQuestionsList;

    private PagerAdapter mPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);

        setContentView(R.layout.view_pager_layout);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mQuizQuestionsList = loadQuestions();

        initializePagerAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quiz_questions_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
        mViewPager.setOffscreenPageLimit(NUMBER_OF_QUESTIONS);
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
            return QuizQuestionFragment.newInstance(mQuizQuestionsList.get(index), index + 1);
        }

        @Override
        public int getCount() {
            return mQuizQuestionsList.size();
        }
    }

}
