
package com.bbisercic.ort1.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class NotesListActivity extends Activity {

    @Override
    protected void onCreate(Bundle aIcicle) {

        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(aIcicle);

        LinearLayout layout = new LinearLayout(this);
        Button button = new Button(this);
        String title = getIntent().getExtras().getString("TITLE");
        button.setText(title);
        layout.addView(button);

        setContentView(layout);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }
}
