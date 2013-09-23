
package com.bbisercic.ort1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.activities.QuizFragmentActivity;
import com.bbisercic.ort1.database.dao.beans.QuizBean;

public class QuizQuestionFragment extends Fragment {

    private static final String QUESTION_EXTRA_KEY = "QUESTION_EXTRA_KEY";

    private static final String ANSWER_ASSETS_PATH_EXTRA_KEY = "ANSWER_ASSETS_PATH_EXTRA_KEY";

    private static final String POSITION_EXTRA_KEY = "POSITION_EXTRA_KEY";

    private String mQuestion;

    private String mAnswerAssetsPath;

    private TextView mQuestionTextView;

    private WebView mAnswerWebView;

    private boolean mIsAnswerShown = false;

    private int mPosition;

    /**
     * Static factory method that takes an int parameter, initializes the fragment's arguments, and returns
     * the new fragment to the client.
     */
    public static QuizQuestionFragment newInstance(QuizBean bean, int position) {
        QuizQuestionFragment fragment = new QuizQuestionFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION_EXTRA_KEY, bean.getQuestion());
        args.putString(ANSWER_ASSETS_PATH_EXTRA_KEY, bean.getAnswer());
        args.putInt(POSITION_EXTRA_KEY, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mIsAnswerShown = false;
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment_layout, container, false);
        extractArguments(getArguments());
        initializeView(view);
        initializeWebView();
        return view;
    }

    private void extractArguments(Bundle savedInstanceState) {
        mQuestion = savedInstanceState.getString(QUESTION_EXTRA_KEY);
        mAnswerAssetsPath = savedInstanceState.getString(ANSWER_ASSETS_PATH_EXTRA_KEY);
        mPosition = savedInstanceState.getInt(POSITION_EXTRA_KEY);
    }

    private void initializeView(View view) {
        mQuestionTextView = (TextView) view.findViewById(R.id.questionText);
        mAnswerWebView = (WebView) view.findViewById(R.id.answer_WebView);

        mQuestionTextView.setText(mQuestion);
        mAnswerWebView.setVisibility(View.GONE);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        final int iconRes = mIsAnswerShown ? android.R.drawable.ic_menu_close_clear_cancel
                : android.R.drawable.ic_menu_view;

        MenuItem showItem = menu.findItem(R.id.action_show_hide);
        showItem.setIcon(iconRes);

        MenuItem countItem = menu.findItem(R.id.action_notes_count);

        TextView tv = (TextView) countItem.getActionView().findViewById(R.id.count_text);
        tv.setText("" + mPosition + "/" + QuizFragmentActivity.NUMBER_OF_QUESTIONS);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            getActivity().finish();
            return true;
        case R.id.action_show_hide:
            toggleAnswer();
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void toggleAnswer() {
        mIsAnswerShown = !mIsAnswerShown;
        final int visibility = mIsAnswerShown ? View.VISIBLE : View.GONE;
        mAnswerWebView.setVisibility(visibility);
        getActivity().invalidateOptionsMenu();
    }

    private void initializeWebView() {
        mAnswerWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(mAnswerWebView, url);
            }
        });

        mAnswerWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        mAnswerWebView.getSettings().setAppCachePath(getActivity().getCacheDir().getPath());
        mAnswerWebView.getSettings().setAppCacheEnabled(true);
        mAnswerWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mAnswerWebView.loadUrl(mAnswerAssetsPath);
    }

}
