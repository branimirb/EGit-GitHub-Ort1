
package com.bbisercic.ort1.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.activities.adapters.MainMenuListAdapter;
import com.bbisercic.ort1.activities.adapters.holders.ViewHolder;
import com.bbisercic.ort1.utilities.debug.LogUtility;

/**
 * Class used for displaying main menu {@link ListActivity}.
 */
public class MainMenuActivity extends ListActivity {

    private static final String TAG = LogUtility.getTag(MainMenuActivity.class);

    private MainMenuListAdapter mMainMenuListAdapter;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle aIcicle) {
        LogUtility.d(TAG, "Called OnCreate()");
        getActionBar().setTitle(R.string.main_menu_title);
        super.onCreate(aIcicle);
        setContentView(R.layout.main_menu_layout);

        mListView = (ListView) findViewById(android.R.id.list);

        initializeListAdapter();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        
        String title = holder.mTitle.getText().toString();
        Intent intent = new Intent(this, NotesListActivity.class);
        intent.putExtra("TITLE", title);
        this.startActivity(intent);
    }
    
    private void initializeListAdapter() {
        final String[] itemsArray = getResources().getStringArray(R.array.main_menu_items);
        final List<String> items = new ArrayList<String>(Arrays.asList(itemsArray));
        mMainMenuListAdapter = new MainMenuListAdapter(this, R.layout.list_item_layout, items);
        mListView.setAdapter(mMainMenuListAdapter);
    }

}
