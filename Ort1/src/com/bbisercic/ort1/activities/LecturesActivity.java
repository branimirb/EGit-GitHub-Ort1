
package com.bbisercic.ort1.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class LecturesActivity extends Activity {

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

}
