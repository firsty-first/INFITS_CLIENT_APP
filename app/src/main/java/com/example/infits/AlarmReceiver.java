package com.example.infits;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.Map;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//         Get the shared preferences and delete the data
        printData(context);

        SharedPreferences sharedPreferences = context.getSharedPreferences("TodaysBreakFast", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();

        printData(context);

        // Show a toast to indicate that the data has been deleted
        Toast.makeText(context, "Shared preferences data has been deleted", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "AlarmManager: Success");
    }

    private void printData(Context context){
        Log.d("TAG", "printData: Called");
        SharedPreferences sharedPreferences = context.getSharedPreferences("TodaysBreakFast", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("TAG", entry.getKey() + ": " + entry.getValue().toString());
        }
        Log.d("TAG", "printData: Finished");
    }

}

