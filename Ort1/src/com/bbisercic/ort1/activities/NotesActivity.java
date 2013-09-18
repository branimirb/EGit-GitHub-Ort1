
package com.bbisercic.ort1.activities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.activities.adapters.NotesListAdapter;
import com.bbisercic.ort1.activities.adapters.holders.ViewHolder;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.NoteBean;

public class NotesActivity extends ListActivity implements OnItemLongClickListener {
        
    private NotesListAdapter mNotesListAdapter;

    private ListView mListView;
    
    private boolean mIsInSelectMode = false;
    
    private Set<Long> mSelectedNotesIds;
    
    private ArrayList<NoteBean> mNotesList;

    @Override
    protected void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);
        setContentView(R.layout.main_menu_layout);

        mListView = (ListView) findViewById(android.R.id.list);
        mListView.setOnItemLongClickListener(this);
        initializeListAdapter();
        
        mSelectedNotesIds = new HashSet<Long>();
    }

    private void initializeListAdapter() {
        DaoInterface dao = DaoFactory.getInstance();
        mNotesList = (ArrayList<NoteBean>) dao.getAllNotes(this);
        mNotesListAdapter = new NotesListAdapter(this, R.layout.list_item_layout, R.id.row_title, mNotesList);
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
        Intent intent = new Intent(CreateOrEditNoteActivity.EDIT_NOTE_ACTION);
        startActivity(intent);
        // TODO: startActivityForResult
    }
    
    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long id) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            return false;
        }
        
        final NoteBean noteBean = mNotesList.get(holder.mPosition);        
        final boolean isChecked = holder.mCheckBox.isChecked();
        
        if (!isChecked) {
            holder.mCheckBox.setChecked(true);
            holder.mCheckBox.setVisibility(View.VISIBLE);
            mIsInSelectMode = true;
            mSelectedNotesIds.add(noteBean.getId());
        } else {
            holder.mCheckBox.setChecked(false);
            holder.mCheckBox.setVisibility(View.GONE);
            mSelectedNotesIds.remove(noteBean.getId());
            if (mSelectedNotesIds.isEmpty()) {
                mIsInSelectMode = false;
            }
        }
        
        return true;
    }
    
    private void deleteNote() {
        DaoInterface dao = DaoFactory.getInstance();
        if (mIsInSelectMode) {
            dao.removeNotesById(this, mSelectedNotesIds);        
        } else {
            dao.removeAllNotes(this);
        }
        initializeListAdapter();
    }

    private void composeNote() {
        Intent intent = new Intent(CreateOrEditNoteActivity.CREATE_NOTE_ACTION);
        startActivity(intent);
        // TODO: startActivityForResult
    }

    
    
}
