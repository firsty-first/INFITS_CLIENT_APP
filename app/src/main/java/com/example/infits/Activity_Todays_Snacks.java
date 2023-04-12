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

public class Activity_Todays_Snacks extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    JSONObject mainJsonobj;
    JSONArray mainJsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_snacks);
        try {


            Intent intent = getIntent();
            JSONArray jsonArray = new JSONArray();
            mainJsonArray=new JSONArray();
            mainJsonobj=new JSONObject();
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("mealInfoForPhoto"));
            sharedPreferences=getSharedPreferences("TodaysSnacks", Context.MODE_PRIVATE);
            if(sharedPreferences.contains("TodaysSnacks")) {
                JSONObject jsonObject1=new JSONObject(sharedPreferences.getString("TodaysSnacks", ""));
                JSONArray jsonArray1=jsonObject1.getJSONArray("TodaysSnacks");
                for (int i=0;i<jsonArray1.length();i++){
                    mainJsonArray.put(jsonArray1.getJSONObject(i));
                }
            }
            mainJsonArray.put(jsonObject);
            mainJsonobj.put("TodaysSnacks",mainJsonArray);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("TodaysSnacks",mainJsonobj.toString());
            editor.commit();
            Log.d("mealInfoForPhoto", sharedPreferences.getString("TodaysSnacks","").toString());

            Bundle bundle=new Bundle();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentTodays_Snacks fragmentTodays_snacks = new FragmentTodays_Snacks();
            fragmentTransaction.replace(R.id.frameLayout,fragmentTodays_snacks).commit();
        }catch (Exception e){
            Log.d("Exception", e.toString());
        }
    }
}