package com.bbisercic.ort1.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bbisercic.ort1.utilities.debug.LogUtility;

/**
 * Class used for displaying main menu {@link Activity}.
 */
public class MainMenuActivity extends Activity {

	private static final String TAG = LogUtility.getTag(MainMenuActivity.class);
	
	@Override
	protected void onCreate(Bundle aIcicle) {
		LogUtility.d(TAG, "Called OnCreate");
		super.onCreate(aIcicle);		
		
		LinearLayout layout = new LinearLayout(this);
        Button button = new Button(this);
        button.setText("Predavanja");
        layout.addView(button);
		
		setContentView(layout);
	}
	
}
