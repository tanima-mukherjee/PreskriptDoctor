package com.ogma.preskriptdoctor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogma.preskriptdoctor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudyProfileFragment extends Fragment {


    public StudyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_study_profile, container, false);
    }

}
