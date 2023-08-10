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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class activitythirdfragment extends Fragment {

    Button btn_setgoal, btn_start_trd;
    ImageView back_button;
    static String activityTrackerCyclingGoalValue = "6";

    public activitythirdfragment() {
        // Required empty public constructor
    }


    public static activitythirdfragment newInstance(String param1, String param2) {
        activitythirdfragment fragment = new activitythirdfragment();
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
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_activitythirdfragment, container, false);
        btn_start_trd=view.findViewById ( R.id.imageView86_trd);
        btn_setgoal=view.findViewById ( R.id.imageView74 );
        back_button=view.findViewById( R.id.imageView73);
        Dialog dialog = new Dialog(this.getContext());
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_trck_popup);
        ImageView dailogSetGoal = dialog.findViewById(R.id.imageView89);
        dailogSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etGoalValue = dialog.findViewById(R.id.textView88);
                if (!etGoalValue.getText().toString().equals("")){
                    activityTrackerCyclingGoalValue = etGoalValue.getText().toString();
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

        btn_start_trd.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                Navigation.findNavController(v).navigate(R.id.action_activitythirdfragment_to_cycling_frag2);
            }
        } );

        back_button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                Navigation.findNavController(v).navigate(R.id.action_activitythirdfragment_to_activityTracker2);
            }
        } );

        return view;

    }
}