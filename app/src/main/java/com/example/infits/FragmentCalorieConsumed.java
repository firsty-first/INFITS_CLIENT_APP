package com.example.infits;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCalorieConsumed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCalorieConsumed extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String url = String.format("%scalorieConsumed.php", DataFromDatabase.ipConfig);

    PieChart pieChart;
    ImageView imgBack;
    ArrayList<calorieInfo> calorieInfos;
    int BreakFastCalorieConsumed=0,LunchCalorieConsumed=0,DinnerCalorieConsumed=0,SnacksCalorieConsumed=0;
    int TotalCalorieConsumed=0;
    ArrayList<calorieconsumedInfo> BreakFast;
    ArrayList<calorieconsumedInfo> Lunch;
    ArrayList<calorieconsumedInfo> Dinner;
    ArrayList<calorieconsumedInfo> Snacks;

    int[] colors={Color.parseColor("#FCFF72"),Color.parseColor("#ACAFFD"),Color.parseColor("#FF6262"),Color.parseColor("#FFA361")   };
    RecyclerView calorieRecycleview;
    SimpleDateFormat dateFormat;
    SimpleDateFormat dateFormat1;
    Button day_btn_calorie,week_btn_calorie,year_btn_calorie;
    JSONObject BreakFastObject,LunchObject,SnacksObject,DinnerObject;
    TextView totalCalorieValue,caloriedisplaydate;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    public FragmentCalorieConsumed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCalorieConsumed.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCalorieConsumed newInstance(String param1, String param2) {
        FragmentCalorieConsumed fragment = new FragmentCalorieConsumed();
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
        View view=inflater.inflate(R.layout.fragment_calorie_consumed, container, false);
        calorieInfos=new ArrayList<>();
        BreakFast=new ArrayList<>();
        Lunch=new ArrayList<>();
        Dinner=new ArrayList<>();
        Snacks=new ArrayList<>();
         dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         dateFormat1 = new SimpleDateFormat("hh:mm aa");
        hooks(view);
        RetriveData("today");
        day_btn_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetriveData("today");
                SetButtonBackground(v);
//                pieChart();
//                pastAcivity();

            }
        });
        week_btn_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetriveData("week");
                SetButtonBackground(v);
//                pieChart();
//                pastAcivity();
            }
        });
        year_btn_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetriveData("month");
                SetButtonBackground(v);
//                pieChart();
//                pastAcivity();
            }
        });

        pieChart();
        pastAcivity();
        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());
        return view;
    }

    public void RetriveData(String duration){

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
            StringRequest stringRequest=new StringRequest(Request.Method.POST,url,
                    response -> {
                    try {


                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject value = jsonObject.getJSONObject("value");
                        TotalCalorieConsumed= Integer.parseInt(value.getString("total_caloriesconsumed"));
                        BreakFastObject=value.getJSONObject("BreakFast");
                        LunchObject=value.getJSONObject("Lunch");
                        DinnerObject=value.getJSONObject("Dinner");
                        SnacksObject=value.getJSONObject("Snacks");

                        DinnerCalorieConsumed=Integer.parseInt(DinnerObject.getString("MealType_caloriesconsumed"));
                        SnacksCalorieConsumed=Integer.parseInt(SnacksObject.getString("MealType_caloriesconsumed"));
                        BreakFastCalorieConsumed=Integer.parseInt(BreakFastObject.getString("MealType_caloriesconsumed"));
                        LunchCalorieConsumed=Integer.parseInt(LunchObject.getString("MealType_caloriesconsumed"));

                        pastAcivity();
                        pieChart();
                        BreakFastInfo(BreakFastObject);
                        LunchInfo(LunchObject);
                        SnacksInfo(SnacksObject);
                        DinnerInfo(DinnerObject);
                        Log.d("values", String.valueOf(LunchObject));
                    }catch (JSONException e){
                        Toast.makeText(getContext(),"Error is "+e.getMessage().toString(),Toast.LENGTH_LONG).show();

                    }
                    },
                    error -> {
                        Toast.makeText(getContext(),"Error is "+error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String ,String> params=new HashMap<>();
                    params.put("clientID",DataFromDatabase.clientuserID);
                    params.put("for",duration);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
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

    }
    private void pieChart(){
        List<PieEntry> entries=new ArrayList<>();
        entries.add(new PieEntry(Float.intBitsToFloat(DinnerCalorieConsumed),"D"));
        entries.add(new PieEntry(Float.intBitsToFloat(SnacksCalorieConsumed),"S"));
        entries.add(new PieEntry(Float.intBitsToFloat(BreakFastCalorieConsumed),"B"));
        entries.add(new PieEntry(Float.intBitsToFloat(LunchCalorieConsumed),"L"));


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
        pieChart.setEntryLabelTextSize(25f);
        Typeface typeface=Typeface.defaultFromStyle(Typeface.BOLD);

        pieChart.setEntryLabelTypeface(typeface);
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelColor(Color.WHITE);
    }
    private String getCurrentDate(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
        String formattedDate = dateFormat.format(currentDate);
        return formattedDate;
    }
    private void pastAcivity(){
        totalCalorieValue.setText(String.valueOf(TotalCalorieConsumed));
        caloriedisplaydate.setText(String.valueOf(getCurrentDate()));
        calorieInfos.clear();
        BreakFast.clear();
        Lunch.clear();
        Dinner.clear();
        Snacks.clear();

        calorieInfos.add(new calorieInfo(R.string.breakfast,"String","BREAKFAST",String.valueOf(BreakFastCalorieConsumed)+" kcal","00:18:52","11:10 a.m.",BreakFast));
        calorieInfos.add(new calorieInfo(R.string.lunch,"String","LUNCH",String.valueOf(LunchCalorieConsumed)+" kcal","00:18:52","11:10 a.m.",Lunch));
        calorieInfos.add(new calorieInfo(R.string.snacks,"String","SNACKS",String.valueOf(SnacksCalorieConsumed)+" kcal","00:18:52","11:10 a.m.",Snacks));
        calorieInfos.add(new calorieInfo(R.string.dinner,"String","DINNER",String.valueOf(DinnerCalorieConsumed)+" kcal","00:18:52","11:10 a.m.",Dinner));

        CalorieInfoAdapter calorieInfoAdapter=new CalorieInfoAdapter(getContext(),calorieInfos);
        calorieRecycleview.setAdapter(calorieInfoAdapter);

    }
    private void BreakFastInfo(JSONObject BreakFastObject) {
        String mealType = "BreakFast";
        try {
            JSONArray data = BreakFastObject.getJSONArray("data");
            if (data.length() > 0) {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    Date date=dateFormat.parse(jsonObject.getString("time"));
                    String date1=dateFormat1.format(date);

                    BreakFast.add(new calorieconsumedInfo(R.drawable.istockphoto_860224944_612x612_removebg_preview_1, mealType,
                            jsonObject.getString("Meal_Name"),
                            jsonObject.getString("caloriesconsumed") + " kcal",
                            jsonObject.getString("Quantity"),
                            jsonObject.getString("Size"),
                             date1));
                }
            }
        }catch (Exception jsonException){
            Log.d("jsonException",jsonException.toString());
        }
    }
    private void LunchInfo(JSONObject LunchObject){
        String mealType="Lunch";
        try {
            JSONArray data = LunchObject.getJSONArray("data");
            if (data.length() > 0) {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    Date date=dateFormat.parse(jsonObject.getString("time"));
                    String date1=dateFormat1.format(date);

                    Lunch.add(new calorieconsumedInfo(R.drawable.istockphoto_860224944_612x612_removebg_preview_1, mealType,
                            jsonObject.getString("Meal_Name"),
                            jsonObject.getString("caloriesconsumed") + " kcal",
                            jsonObject.getString("Quantity"),
                            jsonObject.getString("Size"),
                            date1));
                }
            }
        }catch (Exception jsonException){
            Log.d("jsonException",jsonException.toString());
        }
    }
    private void DinnerInfo(JSONObject DinnerObject){
        String mealType="Dinner";
        try {
            JSONArray data = DinnerObject.getJSONArray("data");
            if (data.length() > 0) {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    Date date=dateFormat.parse(jsonObject.getString("time"));
                    String date1=dateFormat1.format(date);

                    Dinner.add(new calorieconsumedInfo(R.drawable.istockphoto_860224944_612x612_removebg_preview_1, mealType,
                            jsonObject.getString("Meal_Name"),
                            jsonObject.getString("caloriesconsumed") + " kcal",
                            jsonObject.getString("Quantity"),
                            jsonObject.getString("Size"),
                            date1));
                }
            }
        }catch (Exception jsonException){
            Log.d("jsonException",jsonException.toString());
        }
    }
    private void SnacksInfo(JSONObject SnacksObject){
        String mealType="Snacks";
        try {
            JSONArray data = SnacksObject.getJSONArray("data");
            if (data.length() > 0) {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    Date date=dateFormat.parse(jsonObject.getString("time"));
                    String date1=dateFormat1.format(date);

                    Snacks.add(new calorieconsumedInfo(R.drawable.istockphoto_860224944_612x612_removebg_preview_1, mealType,
                            jsonObject.getString("Meal_Name"),
                            jsonObject.getString("caloriesconsumed") + " kcal",
                            jsonObject.getString("Quantity"),
                            jsonObject.getString("Size"),
                            date1));
                }
            }
        }catch (Exception jsonException){
            Log.d("jsonException",jsonException.toString());
        }
    }
    private void SetButtonBackground(View view){
        switch (view.getId()){
            case R.id.day_btn_calorie:
                day_btn_calorie.setTextColor(Color.WHITE);
                week_btn_calorie.setTextColor(Color.BLACK);
                year_btn_calorie.setTextColor(Color.BLACK);
                day_btn_calorie.setBackground(getActivity().getDrawable(R.drawable.btn_background));
                week_btn_calorie.setBackgroundColor(Color.TRANSPARENT);
                year_btn_calorie.setBackgroundColor(Color.TRANSPARENT);
                break;
            case R.id.week_btn_calorie:
                week_btn_calorie.setTextColor(Color.WHITE);
                day_btn_calorie.setTextColor(Color.BLACK);
                year_btn_calorie.setTextColor(Color.BLACK);

                day_btn_calorie.setBackgroundColor(Color.TRANSPARENT);
                week_btn_calorie.setBackground(getActivity().getDrawable(R.drawable.btn_background));
                year_btn_calorie.setBackgroundColor(Color.TRANSPARENT);
                break;
            case R.id.year_btn_calorie:
                year_btn_calorie.setTextColor(Color.WHITE);
                week_btn_calorie.setTextColor(Color.BLACK);
                day_btn_calorie.setTextColor(Color.BLACK);
                day_btn_calorie.setBackgroundColor(Color.TRANSPARENT);
                week_btn_calorie.setBackgroundColor(Color.TRANSPARENT);
                year_btn_calorie.setBackground(getActivity().getDrawable(R.drawable.btn_background));
                break;
            default:
                break;
        }
    }
}