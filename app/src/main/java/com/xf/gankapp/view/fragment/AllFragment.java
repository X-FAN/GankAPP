package com.xf.gankapp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xf.gankapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {


    public AllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

}
