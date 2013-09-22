
package com.bbisercic.ort1.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    public static final String ACTION_BAR_TITLE_EXTRA = "ACTION_BAR_TITLE_EXTRA";

    public static final String OPEN_NOTES_ACTION = "com.bbisercic.ort1.OPEN_NOTES_ACTION";

    public static final String OPEN_QUIZ_ACTION = "com.bbisercic.ort1.OPEN_QUIZ_ACTION";

    public static final String OPEN_LECTURES_ACTION = "com.bbisercic.ort1.OPEN_LECTURES_ACTION";

    public static final String OPEN_EXERCICES_ACTION = "com.bbisercic.ort1.OPEN_EXERCICES_ACTION";

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
        case R.id.action_about:
            showAboutInfo();
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        this.startActivity(getIntentForMenuItem(holder));
    }

    private Intent getIntentForMenuItem(ViewHolder holder) {
        final String title = holder.mTitle.getText().toString();
        
        Intent intent = new Intent();
        switch (holder.mPosition) {
        case 0:
            intent.setAction(OPEN_LECTURES_ACTION);
            break;
        case 1:
            intent.setAction(OPEN_EXERCICES_ACTION);
            break;
        case 2:
            intent.setAction(OPEN_NOTES_ACTION);
            break;
        case 3:
            intent.setAction(OPEN_QUIZ_ACTION);
            break;
        default:
            intent.setAction(OPEN_NOTES_ACTION);
            break;
        }

        intent.putExtra(ACTION_BAR_TITLE_EXTRA, title);
        return intent;
    }

    private void initializeListAdapter() {
        final String[] itemsArray = getResources().getStringArray(R.array.main_menu_items);
        final List<String> items = new ArrayList<String>(Arrays.asList(itemsArray));
        mMainMenuListAdapter = new MainMenuListAdapter(this, R.layout.list_item_layout, items);
        mListView.setAdapter(mMainMenuListAdapter);
    }
    
    private void showAboutInfo() {
        
    }

}
