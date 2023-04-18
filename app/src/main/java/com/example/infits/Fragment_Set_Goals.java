package com.example.infits;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Set_Goals#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Set_Goals extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String[] values = {"0", "100", "200", "300", "400", "500"};
    RangeSlider carbsSlider, fatSlider, proteinSlider, fiberSlider;
    ImageView calorieImgback;
    Button SetButton;
    TextView carbsSliderValue, fiberSliderValue, proteinSliderValue, fatSliderValue;
    LinearLayout linear_layout1,linear_layout2;
    public Fragment_Set_Goals() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Set_Goals.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Set_Goals newInstance(String param1, String param2) {
        Fragment_Set_Goals fragment = new Fragment_Set_Goals();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__set__goals, container, false);
        hooks(view);
        calorieImgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        //slider Backend
        carbsSlider.setValues(50f);
        fatSlider.setValues(100f);
        proteinSlider.setValues(40f);
        fiberSlider.setValues(70f);

        carbsSliderValue.setText("50 g");
        fiberSliderValue.setText("50 g");
        proteinSliderValue.setText("50 g");
        fatSliderValue.setText("50 g");


        carbsSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                carbsSlider.setValues(value);
                carbsSliderValue.setText(String.valueOf(Math.round(value))+" g");
            }
        });
        fiberSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                fiberSlider.setValues(value);
                fiberSliderValue.setText(String.valueOf(Math.round(value))+" g");
            }
        });
        proteinSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                proteinSlider.setValues(value);
                proteinSliderValue.setText(String.valueOf(Math.round(value))+" g");
            }
        });
        fatSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                fatSlider.setValues(value);
                fatSliderValue.setText(String.valueOf(Math.round(value))+" g");
            }
        });

        SetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_layout1.setVisibility(View.GONE);
                linear_layout2.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Navigation.findNavController(v).navigate(R.id.action_fragment_Set_Goals_toCalorieTracker);
                    }
                },2000);

            }
        });
        return view;
    };

    public void hooks(View view) {
        //sliders code
        carbsSlider = view.findViewById(R.id.CarbsGoalSlider);
        fatSlider = view.findViewById(R.id.fatSlider);
        proteinSlider = view.findViewById(R.id.proteinSlider);
        fiberSlider = view.findViewById(R.id.fiberSlider);

        setProgressBarView(carbsSlider, getResources().getColor(R.color.progressGreenColor));
        setProgressBarView(fatSlider, getResources().getColor(R.color.progressBlueColor));
        setProgressBarView(proteinSlider, getResources().getColor(R.color.progressPurpleColor));
        setProgressBarView(fiberSlider, getResources().getColor(R.color.progressRedColor));

        carbsSliderValue = view.findViewById(R.id.carbsSliderValue);
        fiberSliderValue = view.findViewById(R.id.fiberSliderValue);
        proteinSliderValue = view.findViewById(R.id.proteinSliderValue);
        fatSliderValue = view.findViewById(R.id.fatSliderValue);

        linear_layout1=view.findViewById(R.id.linear_layout1);
        linear_layout2=view.findViewById(R.id.linear_layout2);

        //Buttons
        calorieImgback = view.findViewById(R.id.calorieImgback);
        SetButton = view.findViewById(R.id.SetButton);


    }

    public void setProgressBarView(RangeSlider rangeSlider, int progressColor) {

        rangeSlider.setTrackActiveTintList(ColorStateList.valueOf(progressColor));
        rangeSlider.setTrackInactiveTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        rangeSlider.setTrackHeight(12);
        rangeSlider.setThumbTintList(ColorStateList.valueOf(progressColor));
        rangeSlider.setThumbElevation(10);
        rangeSlider.setThumbRadius(18);
        rangeSlider.setTickVisible(false);


    }
}