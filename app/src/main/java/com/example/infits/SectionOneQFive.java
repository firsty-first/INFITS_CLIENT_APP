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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectionOneQFive#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionOneQFive extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, gendertv;
    RadioButton male, female, other;
    String uGender;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SectionOneQFive() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectionOneQFive.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionOneQFive newInstance(String param1, String param2) {
        SectionOneQFive fragment = new SectionOneQFive();
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
        View view = inflater.inflate(R.layout.fragment_section_one_q_five, container, false);

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        other = view.findViewById(R.id.other);
        RadioGroup radio=view.findViewById(R.id.radioGroup);
        gendertv = view.findViewById(R.id.textView77);
        final String[] storeAnswer = new String[1];


        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setBackgroundResource(R.drawable.radiobtn_on);
                female.setBackgroundResource(R.drawable.radiobtn_off);
                other.setBackgroundResource(R.drawable.radiobtn_off);

                male.setTextColor(Color.WHITE);
                female.setTextColor(Color.BLACK);
                other.setTextColor(Color.BLACK);
                uGender="male";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setBackgroundResource(R.drawable.radiobtn_on);
                male.setBackgroundResource(R.drawable.radiobtn_off);
                other.setBackgroundResource(R.drawable.radiobtn_off);

                female.setTextColor(Color.WHITE);
                male.setTextColor(Color.BLACK);
                other.setTextColor(Color.BLACK);
                uGender="female";
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other.setBackgroundResource(R.drawable.radiobtn_on);
                female.setBackgroundResource(R.drawable.radiobtn_off);
                male.setBackgroundResource(R.drawable.radiobtn_off);

                other.setTextColor(Color.WHITE);
                male.setTextColor(Color.BLACK);
                female.setTextColor(Color.BLACK);
                uGender="other";
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
                if(answer.equals("male")) male.performClick();
                else if(answer.equals("female")) female.performClick();
                else if(answer.equals("other")) other.performClick();




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
                dataVol.put("table", "section1Q5");
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

                //Toast.makeText(getContext(),uGender, Toast.LENGTH_SHORT).show();

                DataSectionOne.gender = uGender;
                DataSectionOne.s1q5 = gendertv.getText().toString();

                if(radio.getCheckedRadioButtonId()==-1)
                    Toast.makeText(getContext(),"Select a gender",Toast.LENGTH_SHORT).show();
                else{
                    ConsultationFragment.psection1+=1;







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









                    Navigation.findNavController(v).navigate(R.id.action_sectionOneQFive_to_sectionOneQSix);




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
                                dataVol.put("newAnswer", uGender);
                                dataVol.put("table", "section1Q5");


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