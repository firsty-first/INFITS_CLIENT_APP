package com.example.infits;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class activityTracker2 extends Fragment {

    RecyclerView recyclerView;
    List<Date> dateList;
    CalAdapter_at adapter;
    ImageView running_img,walking_img,cycling_img;
    ImageButton backbutton;
    private static final int PERMISSION_REQUEST_ALL_SENSORS = 100;

    public activityTracker2 () {
        // Required empty public constructor
    }



    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_activity_tracker2 , container , false);
        Date today = new Date();
        dateList = new ArrayList<> ();

        // Add some test data to the dateList
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        dateList.add(today);
        //  imageView=view.findViewById ( R.id.image_honey_pancake );

        for (int i = 0; i < 500; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(calendar.getTime());
        }

        recyclerView = view.findViewById(R.id.date_recyclerView);

        adapter = new CalAdapter_at(dateList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new LinearSnapHelper ();
        snapHelper.attachToRecyclerView(recyclerView);

        // imageView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_diet_fourth2_to_fragment_diet_third_scrn));

        running_img=view.findViewById ( R.id.imageView79 );
        cycling_img=view.findViewById ( R.id.imageView73 );
        walking_img=view.findViewById ( R.id.imageView82 );
        backbutton=view.findViewById(R.id.activitytrackerimgback);

        running_img.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_activityTracker2_to_activitySecondFragment);
        });
        cycling_img.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_activityTracker2_to_activitythirdfragment);
        });

        walking_img.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_activityTracker2_to_activityfourthfragment);
        });

        backbutton.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_activityTracker2_to_dashBoardFragment);
        });


      //  backbutton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Navigation.findNavController(v).navigate(R.id.action_activityTracker2_to_dashBoardFragment);
       //     }
       // });

//        return inflater.inflate(R.layout.fragment_diet_fourth, container, false);
        getPermission_Body();
        statusCheck();
        return  view;


    }
    public  void getPermission_Body()
    {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.BODY_SENSORS) == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted, proceed with your activity logic
            // ...
        } else {
            // Permission not yet granted, request it from the user
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.BODY_SENSORS, Manifest.permission.ACTIVITY_RECOGNITION},PERMISSION_REQUEST_ALL_SENSORS);
        }
    }
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}