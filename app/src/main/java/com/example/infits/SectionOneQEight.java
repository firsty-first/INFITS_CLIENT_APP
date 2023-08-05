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
import android.widget.RadioGroup;
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
 * Use the {@link SectionOneQEight#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionOneQEight extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, shifttv;
    RadioButton sGeneral,sMorning, sEvening, sChange, sNA;
    String shift;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SectionOneQEight() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectionOneQEight.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionOneQEight newInstance(String param1, String param2) {
        SectionOneQEight fragment = new SectionOneQEight();
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
        View view = inflater.inflate(R.layout.fragment_section_one_q_eight, container, false);

        //sGeneral,sMorning, sEvening, sChange, sNA;

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        sGeneral = view.findViewById(R.id.sGeneral);
        sMorning = view.findViewById(R.id.sMorning);
        sEvening = view.findViewById(R.id.sEvening);
        sChange = view.findViewById(R.id.sChange);
        sNA = view.findViewById(R.id.sNA);
        RadioGroup r=view.findViewById(R.id.radioGroup);
        shifttv = view.findViewById(R.id.textView77);
        final String[] storeAnswer = new String[1];


        sGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sGeneral.setBackgroundResource(R.drawable.radiobtn_on);
                sMorning.setBackgroundResource(R.drawable.radiobtn_off);
                sEvening.setBackgroundResource(R.drawable.radiobtn_off);
                sChange.setBackgroundResource(R.drawable.radiobtn_off);
                sNA.setBackgroundResource(R.drawable.radiobtn_off);

                sGeneral.setTextColor(Color.WHITE);
                sMorning.setTextColor(Color.BLACK);
                sEvening.setTextColor(Color.BLACK);
                sChange.setTextColor(Color.BLACK);
                sNA.setTextColor(Color.BLACK);

                shift="General Shift";
            }
        });

        sMorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sGeneral.setBackgroundResource(R.drawable.radiobtn_off);
                sMorning.setBackgroundResource(R.drawable.radiobtn_on);
                sEvening.setBackgroundResource(R.drawable.radiobtn_off);
                sChange.setBackgroundResource(R.drawable.radiobtn_off);
                sNA.setBackgroundResource(R.drawable.radiobtn_off);

                sMorning.setTextColor(Color.WHITE);
                sGeneral.setTextColor(Color.BLACK);
                sEvening.setTextColor(Color.BLACK);
                sChange.setTextColor(Color.BLACK);
                sNA.setTextColor(Color.BLACK);

                shift="Morning Shift";
            }
        });

        sEvening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sGeneral.setBackgroundResource(R.drawable.radiobtn_off);
                sMorning.setBackgroundResource(R.drawable.radiobtn_off);
                sEvening.setBackgroundResource(R.drawable.radiobtn_on);
                sChange.setBackgroundResource(R.drawable.radiobtn_off);
                sNA.setBackgroundResource(R.drawable.radiobtn_off);

                sEvening.setTextColor(Color.WHITE);
                sMorning.setTextColor(Color.BLACK);
                sGeneral.setTextColor(Color.BLACK);
                sChange.setTextColor(Color.BLACK);
                sNA.setTextColor(Color.BLACK);

                shift="Evening Shift";
            }
        });

        sChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sGeneral.setBackgroundResource(R.drawable.radiobtn_off);
                sMorning.setBackgroundResource(R.drawable.radiobtn_off);
                sEvening.setBackgroundResource(R.drawable.radiobtn_off);
                sChange.setBackgroundResource(R.drawable.radiobtn_on);
                sNA.setBackgroundResource(R.drawable.radiobtn_off);

                sChange.setTextColor(Color.WHITE);
                sMorning.setTextColor(Color.BLACK);
                sEvening.setTextColor(Color.BLACK);
                sGeneral.setTextColor(Color.BLACK);
                sNA.setTextColor(Color.BLACK);

                shift="Shift changes every week or every 2 weeks or monthly";
            }
        });

        sNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sGeneral.setBackgroundResource(R.drawable.radiobtn_off);
                sMorning.setBackgroundResource(R.drawable.radiobtn_off);
                sEvening.setBackgroundResource(R.drawable.radiobtn_off);
                sChange.setBackgroundResource(R.drawable.radiobtn_off);
                sNA.setBackgroundResource(R.drawable.radiobtn_on);

                sNA.setTextColor(Color.WHITE);
                sMorning.setTextColor(Color.BLACK);
                sEvening.setTextColor(Color.BLACK);
                sChange.setTextColor(Color.BLACK);
                sGeneral.setTextColor(Color.BLACK);

                shift="NA";
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
                storeAnswer[0] = answer;
                if(answer.equals("General Shift")) sGeneral.performClick();
                else if(answer.equals("Morning Shift")) sMorning.performClick();
                else if(answer.equals("Evening Shift")) sEvening.performClick();
                else if(answer.equals("Shift changes every week or every 2 weeks or monthly")) sChange.performClick();
                else if(answer.equals("NA")) sNA.performClick();





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
                dataVol.put("table", "section1Q8");
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

                //Toast.makeText(getContext(),DataSectionOne.gender, Toast.LENGTH_SHORT).show();

                DataSectionOne.shift = shift;
                DataSectionOne.s1q8 = shifttv.getText().toString();
                if (r.getCheckedRadioButtonId()==-1)
                    Toast.makeText(getContext(), "Select the shift", Toast.LENGTH_SHORT).show();
                else {
                    ConsultationFragment.psection1 += 1;







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
                            dataVol.put("newAnswer", Integer.toString(ConsultationFragment.psection1));
                            dataVol.put("sectionNo", "section1");


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









                    Navigation.findNavController(v).navigate(R.id.action_sectionOneQEight_to_consultationFragment);



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
                            dataVol.put("newAnswer", shift);
                            dataVol.put("table", "section1Q8");


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
                if(ConsultationFragment.psection1>0)
                    ConsultationFragment.psection1-=1;
                requireActivity().onBackPressed();
            }
        });

        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }
}