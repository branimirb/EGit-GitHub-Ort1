
package com.bbisercic.ort1.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bbisercic.ort1.R;

public class AboutActivity extends Activity {

    public static final String ABOUT_APPLICATION = "com.bbisercic.ort1.ABOUT_APPLICATION";

    private WebView mAboutWebView;

    @Override
    public void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);
        setContentView(R.layout.lecture_preview_layout);

        mAboutWebView = (WebView) findViewById(R.id.lecture_web_view);

        getActionBar().setTitle(getString(R.string.about_tite));

        initializeWebView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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

    private void initializeWebView() {
        mAboutWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(mAboutWebView, url);
            }
        });

        mAboutWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        mAboutWebView.getSettings().setAppCachePath(getCacheDir().getPath());
        mAboutWebView.getSettings().setAppCacheEnabled(true);
        mAboutWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        mAboutWebView.loadUrl(getString(R.string.about_application_file_path));
    }

}
