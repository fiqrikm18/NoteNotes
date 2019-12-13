package com.idts.notenotes.fragments.CompleteTask;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.idts.notenotes.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompleteTaskFragment extends Fragment {


    public CompleteTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complete_task, container, false);
    }

}
