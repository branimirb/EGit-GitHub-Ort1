
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
import com.bbisercic.ort1.activities.SecondExercicePreviewFragmentActivity;

public class ExercicePageFragment extends Fragment {

    private static final String POSITION_EXTRA_KEY = "POSITION_EXTRA_KEY";

    private String mPageAssetsPath;

    private WebView mPageWebView;

    private int mPosition;

    /**
     * Static factory method that takes an int parameter, initializes the fragment's arguments, and returns
     * the new fragment to the client.
     */
    public static ExercicePageFragment newInstance(int position) {
        ExercicePageFragment fragment = new ExercicePageFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION_EXTRA_KEY, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exercice_page_fragment_layout, container, false);
        extractArguments(getArguments());
        initializeWebView(view);
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        MenuItem countItem = menu.findItem(R.id.action_current_page);

        TextView tv = (TextView) countItem.getActionView().findViewById(R.id.count_text);
        tv.setText("" + mPosition + "/" + SecondExercicePreviewFragmentActivity.NUMBER_OF_PAGES);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            return handleBackPressed();
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void extractArguments(Bundle savedInstanceState) {
        mPosition = savedInstanceState.getInt(POSITION_EXTRA_KEY);
        mPageAssetsPath = getPagePathFromAssets(mPosition);
    }

    private void initializeWebView(View view) {

        mPageWebView = (WebView) view.findViewById(R.id.exercice_page_WebView);
        mPageWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(mPageWebView, url);
            }
        });

        mPageWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        mPageWebView.getSettings().setAppCachePath(getActivity().getCacheDir().getPath());
        mPageWebView.getSettings().setAppCacheEnabled(true);
        mPageWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mPageWebView.loadUrl(mPageAssetsPath);
    }

    private boolean handleBackPressed() {
        if (mPageWebView.canGoBack()) {
            mPageWebView.goBack();
            return true;

        } else {
            getActivity().finish();
            return true;
        }
    }

    private String getPagePathFromAssets(int position) {
        switch (position) {
        case 1:
            return getString(R.string.exerciceUri_2_slide_1);
        case 2:
            return getString(R.string.exerciceUri_2_slide_2);
        case 3:
            return getString(R.string.exerciceUri_2_slide_3);
        case 4:
            return getString(R.string.exerciceUri_2_slide_4);
        case 5:
            return getString(R.string.exerciceUri_2_slide_5);
        case 6:
            return getString(R.string.exerciceUri_2_slide_6);
        case 7:
            return getString(R.string.exerciceUri_2_slide_7);
        default:
            return getString(R.string.default_file_path);
        }
    }

}
