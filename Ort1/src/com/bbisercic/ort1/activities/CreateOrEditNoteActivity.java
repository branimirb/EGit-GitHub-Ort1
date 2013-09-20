
package com.bbisercic.ort1.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.database.DatabaseConstants;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.database.dao.enums.ArticleType;

/**
 * Front end class used for displaying user interface needed for creating and editing note.
 */
public class CreateOrEditNoteActivity extends Activity {

    public static final String CREATE_NOTE_ACTION = "com.bbisercic.ort1.CREATE_NOTE_ACTION";

    public static final String EDIT_NOTE_ACTION = "com.bbisercic.ort1.EDIT_NOTE_ACTION";

    public static final String ARTICLE_ID_EXTRA_KEY = "ARTICLE_ID_EXTRA_KEY";

    public static final String ARTICLE_TITLE_EXTRA_KEY = "ARTICLE_TITLE_EXTRA_KEY";

    public static final String ARTICLE_TYPE_EXTRA_KEY = "ARTICLE_TYPE_EXTRA_KEY";

    /**
     * Reference on the UI {@link EditText} component used for inputing note's title.
     */
    private EditText mTitleEditText;

    /**
     * Reference on the UI {@link EditText} component used for inputing note's body.
     */
    private EditText mBodyEditText;

    /**
     * Reference on the UI {@link Button} component used for saving note.
     */
    private Button mConfirmButton;

    private ArticleType mArticleType;

    private long mArticleId;

    private String mArticleTitle;

    private static NoteBean sNoteBean = new NoteBean();

    @Override
    protected void onCreate(Bundle aIcicle) {
        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);

        setContentView(R.layout.create_or_edit_note_layout);

        if (CREATE_NOTE_ACTION.equals(getIntent().getAction())) {
            getActionBar().setTitle(R.string.create_note_title);
        } else if (EDIT_NOTE_ACTION.equals(getIntent().getAction())) {
            getActionBar().setTitle(R.string.edit_note_title);
        }

        initializeViews();
        initializeListeners();

        int articleType;

        if (aIcicle == null) {
            sNoteBean.setId(getIntent().getExtras().getLong(DatabaseConstants.NoteInfo._ID));
            mArticleId = getIntent().getLongExtra(ARTICLE_ID_EXTRA_KEY, 0);
            mArticleTitle = getIntent().getExtras().getString(ARTICLE_TITLE_EXTRA_KEY, "n/a");
            articleType = getIntent().getIntExtra(ARTICLE_TYPE_EXTRA_KEY, ArticleType.UNASSIGNED.getValue());
            mArticleType = ArticleType.getTypeForValue(articleType);
        } else {
            sNoteBean.setId((Long) aIcicle.getSerializable(DatabaseConstants.NoteInfo._ID));
            mArticleId = aIcicle.getLong(ARTICLE_ID_EXTRA_KEY, 0);
            mArticleTitle = aIcicle.getString(ARTICLE_TITLE_EXTRA_KEY, "n/a");
            articleType = aIcicle.getInt(ARTICLE_TYPE_EXTRA_KEY, ArticleType.UNASSIGNED.getValue());
            mArticleType = ArticleType.getTypeForValue(articleType);
        }

        extractAndPopulateNoteFields();
    }

    @Override
    protected void onSaveInstanceState(Bundle saveStateBundle) {
        super.onSaveInstanceState(saveStateBundle);
        saveStateBundle.putSerializable(DatabaseConstants.NoteInfo._ID, sNoteBean.getId());
        saveStateBundle.putLong(ARTICLE_ID_EXTRA_KEY, mArticleId);
        saveStateBundle.putString(ARTICLE_TITLE_EXTRA_KEY, mArticleTitle);
        saveStateBundle.putInt(ARTICLE_TYPE_EXTRA_KEY, mArticleType.getValue());
        saveNoteState();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNoteState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        extractAndPopulateNoteFields();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            setResult(RESULT_OK);
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Performs initialization of all UI components.
     */
    private void initializeViews() {
        this.mTitleEditText = (EditText) findViewById(R.id.title);
        this.mBodyEditText = (EditText) findViewById(R.id.body);
        this.mConfirmButton = (Button) findViewById(R.id.confirm);
    }

    /**
     * Performs initialization of all listeners attached to UI components.
     */
    private void initializeListeners() {
        mConfirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    /**
     * Performs fetching note's fields from database, and setting corresponding UI elements.
     */
    private void extractAndPopulateNoteFields() {
        if (sNoteBean.getId() != 0) {
            final DaoInterface dao = DaoFactory.getInstance();
            NoteBean noteBean = dao.getNoteById(this, sNoteBean.getId());

            if (noteBean != null) {
                sNoteBean = noteBean;
            }
            
            mTitleEditText.setText(sNoteBean.getTitle());
            mBodyEditText.setText(sNoteBean.getBody());
        }
    }

    /**
     * Saves the current UI state into database and thus creating new note or updating existing one.
     */
    private void saveNoteState() {
        final DaoInterface dao = DaoFactory.getInstance();
        final String title = mTitleEditText.getText().toString().trim();
        final String acceptableTitle = !title.isEmpty() ? title : getString(R.string.empty_title);
        final String body = mBodyEditText.getText().toString();

        sNoteBean.setTimestamp(System.currentTimeMillis());
        sNoteBean.setTitle(acceptableTitle);
        sNoteBean.setBody(body);
        sNoteBean.setArticleId(mArticleId);
        sNoteBean.setArticleTitle(mArticleTitle);
        sNoteBean.setArticleType(mArticleType);

        if (sNoteBean.getId() == 0) {
            long id = dao.createNote(this, sNoteBean);
            if (id > 0) {
                sNoteBean.setId(id);
            }
        } else {
            dao.updateNote(this, sNoteBean);
        }
    }

}
