
package com.bbisercic.ort1.utilities;

import java.util.HashSet;
import java.util.Set;

public class SelectedNotesSingleton {

    private static SelectedNotesSingleton sInstance = null;

    private boolean mIsInSelectMode = false;

    private Set<Long> mSelectedListPositions;

    public static SelectedNotesSingleton getInstance() {
        if (sInstance == null) {
            sInstance = new SelectedNotesSingleton();
        }
        return sInstance;
    }

    public SelectedNotesSingleton() {
        mIsInSelectMode = false;
        mSelectedListPositions = new HashSet<Long>();
    }

    public boolean isIsInSelectMode() {
        return mIsInSelectMode;
    }

    public void setIsInSelectMode(boolean mIsInSelectMode) {
        this.mIsInSelectMode = mIsInSelectMode;
    }

    public Set<Long> getSelectedListPositions() {
        return mSelectedListPositions;
    }

    public void setSelectedListPositions(Set<Long> mSelectedNotesIds) {
        this.mSelectedListPositions = mSelectedNotesIds;
    }

    public void clearSelected() {
        mIsInSelectMode = false;
        mSelectedListPositions.clear();
    }

}
