
package com.bbisercic.ort1.activities.adapters.holders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbisercic.ort1.R;

public class ViewHolder {
    
    public int mPosition;

    public TextView mTitle;

    public TextView mSubtitle;
    
    public TextView mDate;

    public ImageView mIcon;

    public CheckBox mCheckBox;

    public ViewHolder(View parent) {       
        mIcon = (ImageView) parent.findViewById(R.id.row_icon);
        mTitle = (TextView) parent.findViewById(R.id.row_title);
        mSubtitle = (TextView) parent.findViewById(R.id.row_subtitle);
        mDate = (TextView) parent.findViewById(R.id.row_date);
        mCheckBox = (CheckBox) parent.findViewById(R.id.row_check);
    }

}
