
package com.bbisercic.ort1.activities.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bbisercic.ort1.activities.adapters.holders.ViewHolder;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.utilities.DateUtil;
import com.bbisercic.ort1.utilities.ImageResourceResolver;
import com.bbisercic.ort1.utilities.debug.LogUtility;

public class NotesListAdapter extends ArrayAdapter<NoteBean> {

    private static final String TAG = LogUtility.getTag(NotesListAdapter.class);

    private static final String DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";

    private int mItemLayoutId;

    private List<NoteBean> mLoadedNotes;

    private Context mContext;

    public NotesListAdapter(Context context, int resource, int textViewResourceId, List<NoteBean> items) {
        super(context, resource, textViewResourceId, items);
        LogUtility.d(TAG, "Created NotesListAdapter");

        mContext = context;
        mItemLayoutId = resource;
        mLoadedNotes = items;
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
        final NoteBean noteBean = mLoadedNotes.get(holder.mPosition);

        holder.mCheckBox.setVisibility(View.GONE);
        holder.mTitle.setText(noteBean.getTitle());
        holder.mSubtitle.setText(DateUtil.getDate(noteBean.getTimestamp(), DATE_FORMAT));

        final int iconId = ImageResourceResolver.getArticleImage(noteBean.getArticleType());
        holder.mIcon.setImageDrawable(mContext.getResources().getDrawable(iconId));
    }

}
