package com.example.infits;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section3Q2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section3Q2 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, contv;
    RadioButton yes,some,occ,no,afterFood,beforeFood;
    String constipation="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section3Q2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section3Q2.
     */
    // TODO: Rename and change types and number of parameters
    public static Section3Q2 newInstance(String param1, String param2) {
        Section3Q2 fragment = new Section3Q2();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section3_q2, container, false);

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        yes = view.findViewById(R.id.yes);
        some = view.findViewById(R.id.some);
        no = view.findViewById(R.id.no);
        occ = view.findViewById(R.id.occ);
        afterFood = view.findViewById(R.id.afterFood);
        beforeFood = view.findViewById(R.id.beforeFood);

        contv = view.findViewById(R.id.textView77);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes.setBackgroundResource(R.drawable.radiobtn_on);
                some.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);
                afterFood.setBackgroundResource(R.drawable.radiobtn_off);
                beforeFood.setBackgroundResource(R.drawable.radiobtn_off);

                yes.setTextColor(Color.WHITE);
                some.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);
                afterFood.setTextColor(Color.BLACK);
                beforeFood.setTextColor(Color.BLACK);

                constipation="Yes";
            }
        });

        some.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                some.setBackgroundResource(R.drawable.radiobtn_on);
                yes.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);
                afterFood.setBackgroundResource(R.drawable.radiobtn_off);
                beforeFood.setBackgroundResource(R.drawable.radiobtn_off);

                some.setTextColor(Color.WHITE);
                yes.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);
                afterFood.setTextColor(Color.BLACK);
                beforeFood.setTextColor(Color.BLACK);

                constipation="Sometimes";
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no.setBackgroundResource(R.drawable.radiobtn_on);
                some.setBackgroundResource(R.drawable.radiobtn_off);
                yes.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);
                afterFood.setBackgroundResource(R.drawable.radiobtn_off);
                beforeFood.setBackgroundResource(R.drawable.radiobtn_off);

                no.setTextColor(Color.WHITE);
                some.setTextColor(Color.BLACK);
                yes.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);
                afterFood.setTextColor(Color.BLACK);
                beforeFood.setTextColor(Color.BLACK);

                constipation="No";
            }
        });

        occ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                occ.setBackgroundResource(R.drawable.radiobtn_on);
                some.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                yes.setBackgroundResource(R.drawable.radiobtn_off);
                afterFood.setBackgroundResource(R.drawable.radiobtn_off);
                beforeFood.setBackgroundResource(R.drawable.radiobtn_off);

                occ.setTextColor(Color.WHITE);
                some.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);
                yes.setTextColor(Color.BLACK);
                afterFood.setTextColor(Color.BLACK);
                beforeFood.setTextColor(Color.BLACK);

                constipation="Occasionally";
            }
        });

        afterFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afterFood.setBackgroundResource(R.drawable.radiobtn_on);
                some.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);
                yes.setBackgroundResource(R.drawable.radiobtn_off);
                beforeFood.setBackgroundResource(R.drawable.radiobtn_off);

                afterFood.setTextColor(Color.WHITE);
                some.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);
                yes.setTextColor(Color.BLACK);
                beforeFood.setTextColor(Color.BLACK);

                constipation="After having food";
            }
        });

        beforeFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beforeFood.setBackgroundResource(R.drawable.radiobtn_on);
                some.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);
                afterFood.setBackgroundResource(R.drawable.radiobtn_off);
                yes.setBackgroundResource(R.drawable.radiobtn_off);

                beforeFood.setTextColor(Color.WHITE);
                some.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);
                afterFood.setTextColor(Color.BLACK);
                yes.setTextColor(Color.BLACK);

                constipation="Before having food";
            }
        });

        //String url = "http://192.168.0.114/sectionRead.php";
        String url = "https://infits.in/androidApi/sectionRead.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("Checking", "Checking1");
            System.out.println(DataFromDatabase.clientuserID);
            System.out.println(response);

            JSONObject jsonResponse = null;

            try {
                jsonResponse = new JSONObject(response);
                JSONArray cast = jsonResponse.getJSONArray("answer");
                JSONObject actor = cast.getJSONObject(0);
                String answer = actor.getString("answer");

                if(answer.equals("Yes")) yes.performClick();
                else if(answer.equals("Sometimes")) some.performClick();
                else if(answer.equals("No")) no.performClick();
                else if(answer.equals("Occasionally")) occ.performClick();
                else if(answer.equals("After having food")) afterFood.performClick();
                else if(answer.equals("Before having food")) beforeFood.performClick();



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.d("Data", error.toString().trim());
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> dataVol = new HashMap<>();
                Log.e("Checking", "Checking");
                dataVol.put("clientuserID", DataFromDatabase.clientuserID);
                dataVol.put("table", "section3Q2");
                return dataVol;
            }
        };
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(),employment, Toast.LENGTH_SHORT).show();

                DataSectionThree.constipation = constipation;
                DataSectionThree.s3q2 = contv.getText().toString();

                if (constipation.equals(""))
                    Toast.makeText(getContext(), "Select atleast one of the given options", Toast.LENGTH_SHORT).show();
                else {
                    ConsultationFragment.psection3 += 1;

                    //Updating Sections Progress
                    //String urlProgress = "http://192.168.0.114/sectionProgressUpdate.php";
                    String urlProgress = "https://infits.in/androidApi/sectionProgressUpdate.php";
                    StringRequest stringRequestPro = new StringRequest(Request.Method.POST, urlProgress, response -> {
                        Log.e("Checking", "Checking1");

                    }, error -> {
                        Log.d("Data", error.toString().trim());
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> dataVol = new HashMap<>();
                            Log.e("Checking", "Checking");
                            dataVol.put("clientuserID", DataFromDatabase.clientuserID);
                            dataVol.put("newAnswer", Integer.toString(ConsultationFragment.psection3));
                            dataVol.put("sectionNo", "section3");


                            return dataVol;
                        }
                    };
                    stringRequestPro.setRetryPolicy(new RetryPolicy() {
                        @Override
                        public int getCurrentTimeout() {
                            return 50000;
                        }

                        @Override
                        public int getCurrentRetryCount() {
                            return 50000;
                        }

                        @Override
                        public void retry(VolleyError error) throws VolleyError {

                        }
                    });
                    Volley.newRequestQueue(getActivity()).add(stringRequestPro);



                    Navigation.findNavController(v).navigate(R.id.action_section3Q2_to_section3Q3);

                    //String url = "http://192.168.0.114/sectionUpdate.php";
                    String url = "https://infits.in/androidApi/sectionUpdate.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                        Log.e("Checking", "Checking1");

                    }, error -> {
                        Log.d("Data", error.toString().trim());
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> dataVol = new HashMap<>();
                            Log.e("Checking", "Checking");
                            dataVol.put("clientuserID", DataFromDatabase.clientuserID);
                            dataVol.put("newAnswer", constipation);
                            dataVol.put("table", "section3Q2");


                            return dataVol;
                        }
                    };
                    stringRequest.setRetryPolicy(new RetryPolicy() {
                        @Override
                        public int getCurrentTimeout() {
                            return 50000;
                        }

                        @Override
                        public int getCurrentRetryCount() {
                            return 50000;
                        }

                        @Override
                        public void retry(VolleyError error) throws VolleyError {

                        }
                    });
                    Volley.newRequestQueue(getActivity()).add(stringRequest);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConsultationFragment.psection3>0)
                    ConsultationFragment.psection3-=1;
                requireActivity().onBackPressed();
            }
        });

        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }
}