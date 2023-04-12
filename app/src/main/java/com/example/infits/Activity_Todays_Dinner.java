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

public class Activity_Todays_Dinner extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    JSONObject mainJsonobj;
    JSONArray mainJsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_dinner);
        try {


            Intent intent = getIntent();
            JSONArray jsonArray = new JSONArray();
            mainJsonArray=new JSONArray();
            mainJsonobj=new JSONObject();
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("mealInfoForPhoto"));
            sharedPreferences=getSharedPreferences("TodaysDinner", Context.MODE_PRIVATE);
            if(sharedPreferences.contains("TodaysDinner")) {
                JSONObject jsonObject1=new JSONObject(sharedPreferences.getString("TodaysDinner", ""));
                JSONArray jsonArray1=jsonObject1.getJSONArray("TodaysDinner");
                for (int i=0;i<jsonArray1.length();i++){
                    mainJsonArray.put(jsonArray1.getJSONObject(i));
                }
            }
            mainJsonArray.put(jsonObject);
            mainJsonobj.put("TodaysDinner",mainJsonArray);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("TodaysDinner",mainJsonobj.toString());
            editor.commit();
            Log.d("mealInfoForPhoto", sharedPreferences.getString("TodaysDinner","").toString());

            Bundle bundle=new Bundle();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentTodays_Dinner fragmentTodays_dinner = new FragmentTodays_Dinner();
            fragmentTransaction.replace(R.id.frameLayout,fragmentTodays_dinner).commit();
        }catch (Exception e){
            Log.d("Exception", e.toString());
        }
    }
}