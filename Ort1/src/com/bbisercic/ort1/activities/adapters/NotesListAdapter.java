
package com.bbisercic.ort1.activities.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bbisercic.ort1.R;
import com.bbisercic.ort1.activities.adapters.holders.ViewHolder;
import com.bbisercic.ort1.database.dao.DaoFactory;
import com.bbisercic.ort1.database.dao.DaoInterface;
import com.bbisercic.ort1.database.dao.beans.ArticleBean;
import com.bbisercic.ort1.database.dao.beans.NoteBean;
import com.bbisercic.ort1.utilities.DateUtil;
import com.bbisercic.ort1.utilities.ImageResourceResolver;
import com.bbisercic.ort1.utilities.IntentFactory;
import com.bbisercic.ort1.utilities.SelectedNotesSingleton;
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

        setChecked(holder);
        holder.mTitle.setText(noteBean.getTitle());
        holder.mDate.setVisibility(View.VISIBLE);
        holder.mDate.setText(DateUtil.getDate(noteBean.getTimestamp(), DATE_FORMAT));
        holder.mSubtitle.setText(noteBean.getArticleTitle());

        final int iconId = ImageResourceResolver.getArticleImage(noteBean.getArticleType());
        holder.mIcon.setImageDrawable(mContext.getResources().getDrawable(iconId));

        bindIconClickListener(holder);
    }

    private void setChecked(ViewHolder holder) {
        SelectedNotesSingleton instance = SelectedNotesSingleton.getInstance();
        final long position = holder.mPosition;
        boolean isSelected = instance.getSelectedListPositions().contains(position);
        holder.mCheckBox.setChecked(isSelected);
        holder.mCheckBox.setVisibility(isSelected ? View.VISIBLE : View.GONE);
    }

    private void bindIconClickListener(final ViewHolder holder) {
        holder.mIcon.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final DaoInterface dao = DaoFactory.getInstance();
                final NoteBean noteBean = mLoadedNotes.get(holder.mPosition);
                final long articleId = noteBean.getArticleId();

                if (articleId > 0) {
                    final ArticleBean articleBean = dao.getArticleById(mContext, articleId);
                    mContext.startActivity(IntentFactory.createIntentForExercice(mContext, articleBean));
                } else {
                    Toast.makeText(mContext, R.string.unassigned_note_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
