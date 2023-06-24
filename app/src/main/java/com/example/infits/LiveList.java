package com.example.infits;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class LiveList extends Fragment {

    Button b1;
    public LiveList() {

    }

    public static LiveList newInstance(String param1, String param2) {
        LiveList fragment = new LiveList();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_list, container, false);
        b1 = view.findViewById(R.id.button333);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}