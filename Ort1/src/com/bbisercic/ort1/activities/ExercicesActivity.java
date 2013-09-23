
package com.bbisercic.ort1.activities;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.activities.adapters.ExercicesListAdapter;
import com.bbisercic.ort1.activities.adapters.holders.ViewHolder;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.ArticleBean;
import com.bbisercic.ort1.database.dao.enums.ArticleType;
import com.bbisercic.ort1.utilities.IntentFactory;

public class ExercicesActivity extends ListActivity {

    private ExercicesListAdapter mExercicesListAdapter;

    private ListView mListView;

    private ArrayList<ArticleBean> mExercicesList;

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
        ArticleBean excercice = mExercicesList.get(holder.mPosition);
        startActivity(IntentFactory.createIntentForExercice(this, excercice));
    }

    private void initializeListAdapter() {
        DaoInterface dao = DaoFactory.getInstance();
        mExercicesList = (ArrayList<ArticleBean>) dao.getArticleByType(this, ArticleType.EXERCICE);
        mExercicesListAdapter = new ExercicesListAdapter(this, R.layout.list_item_layout, R.id.row_title,
                mExercicesList);
        mListView.setAdapter(mExercicesListAdapter);
    }
}
