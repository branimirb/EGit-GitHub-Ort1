
package com.bbisercic.ort1.activities;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.activities.adapters.NotesListAdapter;
import com.bbisercic.ort1.activities.adapters.holders.ViewHolder;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.NoteBean;

public class NotesActivity extends ListActivity {

    private NotesListAdapter mNotesListAdapter;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);
        setContentView(R.layout.main_menu_layout);

        mListView = (ListView) findViewById(android.R.id.list);

        initializeListAdapter();
    }

    private void initializeListAdapter() {
        DaoInterface dao = DaoFactory.getInstance();
        ArrayList<NoteBean> items = (ArrayList<NoteBean>) dao.getAllNotes(this);
        mNotesListAdapter = new NotesListAdapter(this, R.layout.list_item_layout, R.id.row_title, items);
        mListView.setAdapter(mNotesListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;

        case R.id.action_notes_add:
            composeNote();
            return true;

        case R.id.action_notes_delete:
            deleteNote();
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        
    }

    private void deleteNote() {
        // TODO Auto-generated method stub

    }

    private void composeNote() {
        // TODO Auto-generated method stub

    }
}
