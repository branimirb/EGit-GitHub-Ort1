
package com.bbisercic.ort1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bbisercic.ort1.R;

public class QuizQuestionFragment extends Fragment {

    private static final String FRAGMENT_POSITION_EXTRA_KEY = "FRAGMENT_POSITION_EXTRA_KEY";

    /**
     * Static factory method that takes an int parameter, initializes the fragment's arguments, and returns
     * the new fragment to the client.
     */
    public static QuizQuestionFragment newInstance(int position) {
        QuizQuestionFragment fragment = new QuizQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_POSITION_EXTRA_KEY, position);
        fragment.setArguments(args);
        return fragment;
    }
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }
        return inflater.inflate(R.layout.quiz_fragment_layout, container, false);
    }
    
    
}
