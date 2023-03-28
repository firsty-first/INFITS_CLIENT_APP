package com.example.infits;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataFromDatabase {
    public static String ipConfig = "http://192.168.178.161/infits/";
//    public static String ipConfig = "http://192.168.101.7/infits/";   // Home 2.5
//      public static String ipConfig = "http://192.168.101.2/infits/";   // Home 5G
//    public static String ipConfig = "http://192.168.36.161/infits/";  // AB
//    public static String ipConfig = "http://192.168.115.161/infits/"; // Yaman
//    public static String ipConfig = "http://192.168.43.149/infits/";  // Deep
//    public static String ipConfig = "http://192.168.39.161/infits/";  // Vineet
//    public static String ipConfig = "http://192.168.108.161/infits/"; // Vivek

    public static boolean flag= false;
    public static String profilePhoto="default.jpg";
    public static String dietitianuserID,clientuserID="Azarudeen",password,name,email,mobile,location,age,gender,weight,height;
    public static Bitmap profile;
    public static Bitmap dtPhoto;
    public static String profilePhotoBase;
    public static String macAddress;
    public static boolean proUser = false;

    public static String stepsStr = "0",stepsGoal= "0",waterStr= "0",waterGoal= "0",sleephrsStr= "0",sleepminsStr= "0",sleepGoal= "0",weightStr= "0",weightGoal= "0",bpm = "0",bpmUp = "0",bpmDown = "0";
}