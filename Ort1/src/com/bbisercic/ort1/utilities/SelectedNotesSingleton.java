
package com.bbisercic.ort1.utilities;

import java.util.HashSet;
import java.util.Set;

public class SelectedNotesSingleton {

    private static SelectedNotesSingleton sInstance = null;

    private boolean mIsInSelectMode = false;

    private Set<Long> mSelectedNotesIds;

    public static SelectedNotesSingleton getInstance() {
        if (sInstance == null) {
            sInstance = new SelectedNotesSingleton();
        }
        return sInstance;
    }

    public SelectedNotesSingleton() {
        mIsInSelectMode = false;
        mSelectedNotesIds = new HashSet<Long>();
    }

    public boolean isIsInSelectMode() {
        return mIsInSelectMode;
    }

    public void setIsInSelectMode(boolean mIsInSelectMode) {
        this.mIsInSelectMode = mIsInSelectMode;
    }

    public Set<Long> getSelectedNotesIds() {
        return mSelectedNotesIds;
    }

    public void setSelectedNotesIds(Set<Long> mSelectedNotesIds) {
        this.mSelectedNotesIds = mSelectedNotesIds;
    }

    public void clearSelected() {
        mIsInSelectMode = false;
        mSelectedNotesIds.clear();
    }

}
