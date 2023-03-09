package com.example.infits;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCalorieBurnt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCalorieBurnt extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    PieChart pieChart;
    ImageView imgBack;
    ArrayList<calorieInfo> calorieInfos;
    int[] colors={Color.parseColor("#FCFF72"),Color.parseColor("#FF6262"),Color.parseColor("#FFA361")};
    RecyclerView calorieRecycleview;
    Button day_btn_calorie,week_btn_calorie,year_btn_calorie;
    TextView totalCalorieValue,caloriedisplaydate;
    public FragmentCalorieBurnt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCalorieBurnt.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCalorieBurnt newInstance(String param1, String param2) {
        FragmentCalorieBurnt fragment = new FragmentCalorieBurnt();
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
        View view=inflater.inflate(R.layout.fragment_calorie_burnt, container, false);
        calorieInfos=new ArrayList<>();

        hooks(view);
        pieChart();
        pastAcivity();
        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());
        return view;
    }
    private void hooks(View view){
        pieChart=view.findViewById(R.id.piechart);
        calorieRecycleview=view.findViewById(R.id.calorierecycleView);
        calorieRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        day_btn_calorie=view.findViewById(R.id.day_btn_calorie);
        week_btn_calorie=view.findViewById(R.id.week_btn_calorie);
        year_btn_calorie=view.findViewById(R.id.year_btn_calorie);
        caloriedisplaydate=view.findViewById(R.id.caloriedisplaydate);
        totalCalorieValue=view.findViewById(R.id.totalCalorieValue);
        imgBack=view.findViewById(R.id.calorieImgback);
        day_btn_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetButtonBackground(v);
                pieChart();
                pastAcivity();
            }
        });
        week_btn_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetButtonBackground(v);
                pieChart();
                pastAcivity();
            }
        });
        year_btn_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetButtonBackground(v);
                pieChart();
                pastAcivity();
            }
        });
    }
    private void pieChart(){
        List<PieEntry> entries=new ArrayList<>();
        entries.add(new PieEntry(40f, getResources().getDrawable(R.drawable.piechart_walk)));
        entries.add(new PieEntry(30f, getResources().getDrawable(R.drawable.piechart_cycle)));
        entries.add(new PieEntry(30f, getResources().getDrawable(R.drawable.piechart_run)));

        pieChart.getLegend().setEnabled(false);
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setSliceSpace(2f);
        PieData data = new PieData(dataSet);
        pieChart.setDrawHoleEnabled(false);
        data.setValueTextSize(0f);
        Description description=new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.setHoleRadius(0f);
        pieChart.setRotationEnabled(false);
        pieChart.setData(data);
        pieChart.animateY(1000, Easing.EaseInOutCubic);
    }
    private void pastAcivity(){
        totalCalorieValue.setText("825");
        caloriedisplaydate.setText("23 January, 2023");
        calorieInfos.clear();
        calorieInfos.add(new calorieInfo(R.drawable.baseline_directions_run_24,"Icon","Running","452 kcal","00:18:52","11:10 a.m."));
        calorieInfos.add(new calorieInfo(R.drawable.baseline_directions_walk_24,"Icon","Walking","452 kcal","00:18:52","11:10 a.m."));
        calorieInfos.add(new calorieInfo(R.drawable.baseline_directions_bike_24,"Icon","Cycling","452 kcal","00:18:52","11:10 a.m."));
        CalorieInfoAdapter calorieInfoAdapter=new CalorieInfoAdapter(getContext(),calorieInfos);
        calorieRecycleview.setAdapter(calorieInfoAdapter);

    }
    private void SetButtonBackground(View view){
        switch (view.getId()){
            case R.id.day_btn_calorie:
                day_btn_calorie.setTextColor(Color.WHITE);
                week_btn_calorie.setTextColor(Color.BLACK);
                year_btn_calorie.setTextColor(Color.BLACK);
                day_btn_calorie.getBackground().setTint(Color.parseColor("#ED9B37"));
                week_btn_calorie.getBackground().setTint(Color.TRANSPARENT);
                year_btn_calorie.getBackground().setTint(Color.TRANSPARENT);
                break;
            case R.id.week_btn_calorie:
                week_btn_calorie.setTextColor(Color.WHITE);
                day_btn_calorie.setTextColor(Color.BLACK);
                year_btn_calorie.setTextColor(Color.BLACK);

                day_btn_calorie.getBackground().setTint(Color.TRANSPARENT);
                week_btn_calorie.getBackground().setTint(Color.parseColor("#ED9B37"));
                year_btn_calorie.getBackground().setTint(Color.TRANSPARENT);
                break;
            case R.id.year_btn_calorie:
                year_btn_calorie.setTextColor(Color.WHITE);
                week_btn_calorie.setTextColor(Color.BLACK);
                day_btn_calorie.setTextColor(Color.BLACK);
                day_btn_calorie.getBackground().setTint(Color.TRANSPARENT);
                week_btn_calorie.getBackground().setTint(Color.TRANSPARENT);
                year_btn_calorie.getBackground().setTint(Color.parseColor("#ED9B37"));
                break;
            default:
                break;
        }
    }
}