
package com.bbisercic.ort1.activities;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.activities.adapters.LecturesListAdapter;
import com.bbisercic.ort1.activities.adapters.holders.ViewHolder;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.ArticleBean;
import com.bbisercic.ort1.database.dao.enums.ArticleType;

public class LecturesActivity extends ListActivity {

    private LecturesListAdapter mLecturesListAdapter;

    private ListView mListView;

    private ArrayList<ArticleBean> mLecturesList;

    @Override
    protected void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);
        setContentView(R.layout.lectures_layout);

        mListView = (ListView) findViewById(android.R.id.list);

        initializeListAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        final ArticleBean lecture = mLecturesList.get(holder.mPosition);

        Intent intent = new Intent(LecturePreviewActivity.LECTURE_PREVIEW_ACTION);
        intent.putExtra(LecturePreviewActivity.LECTURE_ID_EXTRA_KEY, lecture.getId());
        intent.putExtra(LecturePreviewActivity.LECTURE_TITLE_EXTRA_KEY, lecture.getTitle());
        intent.putExtra(LecturePreviewActivity.LECTURE_URI_EXTRA_KEY, lecture.getUri().toString());
        startActivity(intent);
    }

    private void initializeListAdapter() {
        final DaoInterface dao = DaoFactory.getInstance();
        mLecturesList = (ArrayList<ArticleBean>) dao.getArticleByType(this, ArticleType.LECTURE);
        mLecturesListAdapter = new LecturesListAdapter(this, R.layout.list_item_layout, R.id.row_title,
                mLecturesList);
        mListView.setAdapter(mLecturesListAdapter);
    }

}
