package com.example.infits;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NutrientsBreakdownFragment extends Fragment {

  
    public NutrientsBreakdownFragment() {
        // Required empty public constructor
    }
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrients_breakdown, container, false);
        hooks(view);
        return view;
    }

    private void hooks(View view) {

    }
}