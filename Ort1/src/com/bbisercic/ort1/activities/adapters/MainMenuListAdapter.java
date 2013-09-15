
package com.bbisercic.ort1.activities.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bbisercic.ort1.activities.adapters.holders.ViewHolder;
import com.bbisercic.ort1.utilities.ImageResourceResolver;
import com.bbisercic.ort1.utilities.debug.LogUtility;

public class MainMenuListAdapter extends ArrayAdapter<String> {

    private static final String TAG = LogUtility.getTag(MainMenuListAdapter.class);

    private int mItemLayoutId;

    private List<String> mMainMenuItems;

    private Context mContext;

    public MainMenuListAdapter(Context context, int textViewResourceId, List<String> items) {
        super(context, textViewResourceId, items);
        LogUtility.d(TAG, "Created MainMenuListAdapter");

        mContext = context;
        mItemLayoutId = textViewResourceId;
        mMainMenuItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Inflate list item from resource id.
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mItemLayoutId, null);

            // Creates a ViewHolder and store references to the children views we want to bind data to.
            ViewHolder holder = new ViewHolder(convertView);
            holder.mPosition = position;
            convertView.setTag(holder);
        }

        bindView(convertView);

        return convertView;
    }

    private void bindView(View view) {
        LogUtility.d(TAG, "Called bindView()");

        final ViewHolder holder = (ViewHolder) view.getTag();
        if (holder != null) {
            bindItem(holder);
        } else {
            LogUtility.e(TAG, "NullPointerException: ViewHolder is null!");
        }
    }

    private void bindItem(final ViewHolder holder) {
        final String itemName = mMainMenuItems.get(holder.mPosition);
        holder.mCheckBox.setVisibility(View.GONE);
        holder.mSubtitle.setVisibility(View.GONE);
        holder.mTitle.setText(itemName);
        final int iconId = ImageResourceResolver.getMainMenuItemImage(holder.mPosition);
        holder.mIcon.setImageDrawable(mContext.getResources().getDrawable(iconId));
    }

}
