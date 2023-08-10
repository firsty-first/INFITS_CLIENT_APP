package com.example.infits;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class activityfourthfragment extends Fragment {



    Button btn_setgoal,btn_start;
    ImageView backbutton;
    static String activityTrackerWalkingGoalValue = "8000";

    public activityfourthfragment() {
        // Required empty public constructor
    }


    public static activityfourthfragment newInstance(String param1, String param2) {
        activityfourthfragment fragment = new activityfourthfragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_activityfourthfragment, container, false);

        btn_setgoal=view.findViewById ( R.id.imageView74 );
        btn_start=view.findViewById ( R.id.imageView86_walk_start);
        backbutton=view.findViewById(R.id.imageView73);

        Dialog dialog = new Dialog(this.getContext());
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_trck_popup);
        ImageView dailogSetGoal = dialog.findViewById(R.id.imageView89);


        dailogSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etGoalValue = dialog.findViewById(R.id.textView88);
                if (!etGoalValue.getText().toString().equals("")){
                    activityTrackerWalkingGoalValue = etGoalValue.getText().toString();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Please set goal value", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_setgoal.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                dialog.show ();
            }
        } );


        btn_start.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                Navigation.findNavController(v).navigate(R.id. action_activityfourthfragment_to_walking_frag2);
            }
        } );

        backbutton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                Navigation.findNavController(v).navigate(R.id. action_activityfourthfragment_to_activityTracker2);
            }
        } );


        return view;
    }
}