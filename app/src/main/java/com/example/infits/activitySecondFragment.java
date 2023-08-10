package com.example.infits;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tenclouds.gaugeseekbar.GaugeSeekBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class activitySecondFragment extends Fragment {

    GaugeSeekBar progressBar;
    Button run_goal_btn,btn_start;
    TextView textView71, textView72, textView73,textView61;
    ImageView back_button;
    final String TAG = "ActivitySecondFragment";
    static String activityTrackerRunningGoalValue = "6";

    public activitySecondFragment() {
        // Required empty public constructor
    }


    public static activitySecondFragment newInstance(String param1, String param2) {
        activitySecondFragment fragment = new activitySecondFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_activity_second, container, false);
        run_goal_btn = view.findViewById(R.id.imageView74_btn);
        btn_start = view.findViewById(R.id.imageView86);
        textView71 = view.findViewById(R.id.textView71);
        textView72 = view.findViewById(R.id.textView72);
        textView73 = view.findViewById(R.id.textView73);
        textView61 = view.findViewById(R.id.textView61);
        back_button=view.findViewById(R.id.imageView73);

        Dialog dialog = new Dialog(this.getContext());
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_trck_popup);
        ImageView dailogSetGoal = dialog.findViewById(R.id.imageView89);


        dailogSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etGoalValue = dialog.findViewById(R.id.textView88);
                if (!etGoalValue.getText().toString().equals("")){
                    activityTrackerRunningGoalValue = etGoalValue.getText().toString();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Please set goal value", Toast.LENGTH_SHORT).show();
                }

            }
        });

        run_goal_btn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                dialog.show ();
            }
        } );

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_activitySecondFragment_to_activityTracker2);
            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRunning(activityTrackerRunningGoalValue);
                Navigation.findNavController(v).navigate(R.id.action_activitySecondFragment_to_running_frag1);
            }
        });

        return view;
    }

    private void startRunning(String goal){
        String url = "https://infits.in/androidApi/runningTracker.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {

            try {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG,e.toString());
            }
        }, error -> {
            Log.d(TAG,error.toString());
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s");
                LocalDateTime now = LocalDateTime.now();
                data.put("client_id", DataFromDatabase.client_id);
                data.put("clientuserID", DataFromDatabase.clientuserID);
                data.put("distance", "0");
                data.put("calories", "0");
                data.put("runtime", "0");
                data.put("duration", "0");
                data.put("dateandtime", dtf.format(now));
                data.put("goal", goal);
                data.put("date", dtf.format(now));
                return data;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getContext()).add(request);

    }

}