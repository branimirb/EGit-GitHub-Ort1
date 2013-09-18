
package com.bbisercic.ort1.activities;

import com.bbisercic.ort1.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class CreateOrEditNoteActivity extends Activity {

    public static final String CREATE_NOTE_ACTION = "com.bbisercic.ort1.CREATE_NOTE_ACTION";
    
    public static final String EDIT_NOTE_ACTION = "com.bbisercic.ort1.EDIT_NOTE_ACTION";
    
    @Override
    protected void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(aIcicle);

        if (CREATE_NOTE_ACTION.equals(getIntent().getAction())) {
            getActionBar().setTitle(R.string.create_note_title);
        } else if (EDIT_NOTE_ACTION.equals(getIntent().getAction())) {
            getActionBar().setTitle(R.string.edit_note_title);
        }
        
        LinearLayout layout = new LinearLayout(this);
        Button button = new Button(this);        
        layout.addView(button);

        setContentView(layout);
    }

}
