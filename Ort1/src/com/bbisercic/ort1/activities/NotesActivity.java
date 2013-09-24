
package com.bbisercic.ort1.activities;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.activities.adapters.NotesListAdapter;
import com.bbisercic.ort1.activities.adapters.holders.ViewHolder;
import com.bbisercic.ort1.database.DatabaseConstants;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.database.dao.enums.ArticleType;
import com.bbisercic.ort1.utilities.SelectedNotesSingleton;

public class NotesActivity extends ListActivity implements OnItemLongClickListener {

    /**
     * Intent request code used for creating new note.
     */
    private static final int ACTIVITY_CREATE = 0;

    /**
     * Intent request code used for editing existing note.
     */
    private static final int ACTIVITY_EDIT = 1;

    private NotesListAdapter mNotesListAdapter;

    private ListView mListView;

    private ArrayList<NoteBean> mNotesList = new ArrayList<NoteBean>();

    @Override
    protected void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);
        setContentView(R.layout.notes_layout);

        mListView = (ListView) findViewById(android.R.id.list);
        mListView.setOnItemLongClickListener(this);

        SelectedNotesSingleton.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeListAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_action_menu, menu);
        final MenuItem deleteItem = menu.findItem(R.id.action_notes_delete);
        deleteItem.setEnabled(!mNotesList.isEmpty());

        updateCounterInActionBar(menu);

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
        final ViewHolder holder = (ViewHolder) view.getTag();
        editNote(holder.mPosition);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long id) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            return false;
        }

        final boolean isChecked = holder.mCheckBox.isChecked();
        final SelectedNotesSingleton selector = SelectedNotesSingleton.getInstance();

        if (!isChecked) {
            holder.mCheckBox.setChecked(true);
            holder.mCheckBox.setVisibility(View.VISIBLE);
            selector.setIsInSelectMode(true);
            selector.getSelectedListPositions().add((long) holder.mPosition);
        } else {
            holder.mCheckBox.setChecked(false);
            holder.mCheckBox.setVisibility(View.GONE);
            selector.getSelectedListPositions().remove((long) holder.mPosition);
            if (selector.getSelectedListPositions().isEmpty()) {
                selector.setIsInSelectMode(false);
            }
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == ACTIVITY_CREATE || requestCode == ACTIVITY_EDIT) {
            if (resultCode == RESULT_OK) {
                initializeListAdapter();
                invalidateOptionsMenu();
            }
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void initializeListAdapter() {
        DaoInterface dao = DaoFactory.getInstance();
        mNotesList = (ArrayList<NoteBean>) dao.getAllNotes(this);
        mNotesListAdapter = new NotesListAdapter(this, R.layout.list_item_layout, R.id.row_title, mNotesList);
        mListView.setAdapter(mNotesListAdapter);
    }

    private void deleteNote() {
        getDeleteDialog(this).show();
    }

    private void composeNote() {
        SelectedNotesSingleton.getInstance().clearSelected();
        Intent intent = new Intent(CreateOrEditNoteActivity.CREATE_NOTE_ACTION);
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_ID_EXTRA_KEY, 0);
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_TITLE_EXTRA_KEY, "n/a");
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_TYPE_EXTRA_KEY, ArticleType.UNASSIGNED.getValue());
        startActivityForResult(intent, ACTIVITY_CREATE);
    }

    private void updateCounterInActionBar(Menu menu) {
        final MenuItem countItem = menu.findItem(R.id.action_notes_count);
        final TextView tv = (TextView) countItem.getActionView().findViewById(R.id.count_text);
        countItem.setEnabled(false);

        if (mNotesList != null) {
            tv.setText("" + mNotesList.size());
            countItem.setVisible(!mNotesList.isEmpty());
        } else {
            countItem.setVisible(false);
        }
    }

    /**
     * Extracted method used for starting with intent of editing existing note.
     */
    private void editNote(final long selectedItemId) {
        SelectedNotesSingleton.getInstance().clearSelected();
        final NoteBean bean = mNotesList.get((int) selectedItemId);
        Intent intent = new Intent(CreateOrEditNoteActivity.EDIT_NOTE_ACTION);
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_ID_EXTRA_KEY, bean.getArticleId());
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_TITLE_EXTRA_KEY, bean.getArticleTitle());
        intent.putExtra(CreateOrEditNoteActivity.ARTICLE_TYPE_EXTRA_KEY, bean.getArticleType().getValue());
        intent.putExtra(DatabaseConstants.NoteInfo._ID, bean.getId());
        startActivityForResult(intent, ACTIVITY_EDIT);
    }

    private AlertDialog getDeleteDialog(final Context context) {
        final SelectedNotesSingleton selector = SelectedNotesSingleton.getInstance();
        final boolean isInSelectMode = selector.isIsInSelectMode();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final int titleId = !isInSelectMode ? R.string.delete_all_notes_title
                : R.string.delete_selected_notes_title;
        final int textId = !isInSelectMode ? R.string.delete_all_notes_text
                : R.string.delete_selected_notes_text;
        builder.setTitle(titleId);
        builder.setMessage(textId);
        builder.setCancelable(true);

        builder.setPositiveButton(android.R.string.ok, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                DaoInterface dao = DaoFactory.getInstance();
                if (isInSelectMode) {
                    dao.removeNotesById(context, selector.getSelectedListPositions());
                } else {
                    dao.removeAllNotes(context);
                }
                selector.clearSelected();
                initializeListAdapter();

                invalidateOptionsMenu();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

}
