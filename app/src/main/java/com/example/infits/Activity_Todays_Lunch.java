package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class Activity_Todays_Lunch extends AppCompatActivity {
    
    SharedPreferences sharedPreferences;
    JSONObject mainJsonobj;
    JSONArray mainJsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_lunch);
        try {


            Intent intent = getIntent();
            JSONArray jsonArray = new JSONArray();
            mainJsonArray=new JSONArray();
            mainJsonobj=new JSONObject();
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("mealInfoForPhoto"));
            sharedPreferences=getSharedPreferences("TodaysLunch", Context.MODE_PRIVATE);
            if(sharedPreferences.contains("TodaysLunch")) {
                JSONObject jsonObject1=new JSONObject(sharedPreferences.getString("TodaysLunch", ""));
                JSONArray jsonArray1=jsonObject1.getJSONArray("TodaysLunch");
                for (int i=0;i<jsonArray1.length();i++){
                    mainJsonArray.put(jsonArray1.getJSONObject(i));
                }
            }
            mainJsonArray.put(jsonObject);
            mainJsonobj.put("TodaysLunch",mainJsonArray);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("TodaysLunch",mainJsonobj.toString());
            editor.commit();
            Log.d("mealInfoForPhoto", sharedPreferences.getString("TodaysLunch","").toString());

            Bundle bundle=new Bundle();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentTodays_Lunch fragmentTodays_lunch = new FragmentTodays_Lunch();
            fragmentTransaction.replace(R.id.frameLayout,fragmentTodays_lunch).commit();
        }catch (Exception e){
            Log.d("Exception", e.toString());
        }
    }
}