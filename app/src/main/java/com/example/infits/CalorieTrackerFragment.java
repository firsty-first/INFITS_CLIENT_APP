package com.example.infits;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.abhinav.progress_view.ProgressData;
import com.abhinav.progress_view.ProgressView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

public class CalorieTrackerFragment extends Fragment {
    ProgressView progressView1,progressView2,progressView3,progressView4;
    String url=String.format("%scalorieTracker.php", DataFromDatabase.ipConfig);

    CircularProgressIndicator circularProgressIndicatorCC,circularProgressIndicatorCB;

    View bottomSheetN;
    ImageView imgBack;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    CardView calorieConsumed,calorieBurnt,addBreakfast,addLunch,addSnacks,addDinner;
    float CarbsValue=0f,fiberValue=0f,ProteinValue=0f,fatsValue=0f,calorieConsumedValue=0f,calorieBurntValue=0f;
    float CarbsGoals=0f,fiberGoals=0f,ProteinGoals=0f,fatsGoals=0f;
    private String mParam2;
    Button setGoalsButton;
    TextView fatsValueTextview,proteinValueTextview,fiberValueTextview,carbsValueTextView;
    public  CalorieTrackerFragment(){
        // Required Empty Constructor.
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalorieTrackerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalorieTrackerFragment newInstance(String param1, String param2) {
        CalorieTrackerFragment fragment = new CalorieTrackerFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calorie_tracker, container, false);

        hooks(view);
        GetData();
        SetProgress();
        BottomSheetBehavior.from(bottomSheetN).setPeekHeight(350);
        BottomSheetBehavior.from(bottomSheetN).setState(BottomSheetBehavior.STATE_COLLAPSED);
        calorieConsumed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_calorieTrackerFragment_to_calorieConsumedFragment);
            }
        });
        calorieBurnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_calorieTrackerFragment_to_calorieBurntFragment);
            }
        });

        addBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_calorieTrackerFragment_tocalorieAddBreakFastFragment); 
            }
        });
        addLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_calorieTrackerFragment_tocalorieAddLunchFragment); 
            }
        });
        addSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_calorieTrackerFragment_tocalorieAddSnacksFragment); 
            }
        });
        addDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_calorieTrackerFragment_tocalorieAddDinnerFragment); 
            }
        });
        setGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_calorieTrackerFragment_toFragment_Set_Goals); 
            }
        });



        return view;
    }
    public void GetData(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        JSONObject data = jsonObject.getJSONObject("Data");

                        JSONObject Goals = data.getJSONObject("Goals");
                        CarbsGoals = Float.parseFloat(Goals.getString("CarbsGoal"));
                        fatsGoals = Float.parseFloat(Goals.getString("fatsGoal"));
                        ProteinGoals = Float.parseFloat(Goals.getString("ProteinGoal"));
                        fiberGoals = Float.parseFloat(Goals.getString("FiberGoal"));
                        Log.d("nlancnc",data.toString());
                        JSONObject Value = data.getJSONObject("Values");
                        Log.d("fkn",Goals.get("CarbsGoal").toString());

                        calorieBurntValue=Float.parseFloat(data.getString("CalorieBurnt"));

                        Log.d("fkn",Value.toString());
                        CarbsValue = Float.parseFloat(Value.getString("carbs"));
                        fatsValue = Float.parseFloat(Value.getString("fat"));
                        ProteinValue = Float.parseFloat(Value.getString("protein"));
                        fiberValue = Float.parseFloat(Value.getString("fiber"));
                        calorieConsumedValue = Float.parseFloat(Value.getString("caloriesconsumed"));
                        Log.d("carbsValue",String.valueOf(CarbsGoals));
                        SetProgress();

                    }catch (Exception exception){
                        Toast.makeText(getContext(), exception.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("clientID", DataFromDatabase.clientuserID);
//                params.put("time",dtf.format(now).toString());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void SetProgress(){
        Log.d("CarbsValue",String.valueOf(CarbsValue)+"+"+String.valueOf(CarbsGoals));

        ProgressData progressData1 = new ProgressData("Carbs",CarbsValue,CarbsGoals,R.color.progressGreenColor);
        progressView1.setData(progressData1);

        ProgressData progressData2 = new ProgressData("Fiber",fiberValue,fiberGoals,R.color.progressRedColor);
        progressView2.setData(progressData2);

        ProgressData progressData3 = new ProgressData("Fiber",ProteinValue,ProteinGoals,R.color.progressPurpleColor);
        progressView3.setData(progressData3);

        ProgressData progressData4 = new ProgressData("Fats",fatsValue,fatsGoals,R.color.progressBlueColor);
        progressView4.setData(progressData4);

        circularProgressIndicatorCC.setProgress(calorieConsumedValue,10000);
        circularProgressIndicatorCB.setProgress(calorieBurntValue,10000);


        carbsValueTextView.setText(String.valueOf(CarbsGoals-CarbsValue)+"g");
        proteinValueTextview.setText(String.valueOf(ProteinGoals-ProteinValue)+"g");
        fiberValueTextview.setText(String.valueOf(fiberGoals-fiberValue)+"g");
        fatsValueTextview.setText(String.valueOf(fatsGoals-fatsValue)+"g");


    }
    private void hooks(View view) {
        imgBack = view.findViewById(R.id.imgback);
        progressView1 = view.findViewById(R.id.progressView1);
        progressView2 = view.findViewById(R.id.progressView2);
        progressView3 = view.findViewById(R.id.progressView3);
        progressView4 = view.findViewById(R.id.progressView4);
        circularProgressIndicatorCC = view.findViewById(R.id.circular_progressCC);
        circularProgressIndicatorCB = view.findViewById(R.id.circular_progressCB);
        bottomSheetN = view.findViewById(R.id.bottomSheetN);
        addBreakfast = view.findViewById(R.id.add_breakfast);
        addLunch = view.findViewById(R.id.add_lunch);
        addSnacks = view.findViewById(R.id.add_snack);
        addDinner = view.findViewById(R.id.add_dinner);
        calorieConsumed=view.findViewById(R.id.calorieConsumed);
        calorieBurnt=view.findViewById(R.id.calorieBurnt);
        setGoalsButton=view.findViewById(R.id.setGoalsButton);
        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());

        fatsValueTextview=view.findViewById(R.id.fatsValueTextview);
        proteinValueTextview=view.findViewById(R.id.proteinValueTextview);
        fiberValueTextview=view.findViewById(R.id.fiberValueTextview);
        carbsValueTextView=view.findViewById(R.id.carbsValueTextView);

    }
}
