package com.example.infits;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section2Q5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section2Q5 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, diagtv;
    CheckBox dia,hyperthy,hypothy,hyperten,pcod,fattyl,lactose;
    EditText oth;
    ArrayList<String> diagnosed;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section2Q5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section2Q5.
     */
    // TODO: Rename and change types and number of parameters
    public static Section2Q5 newInstance(String param1, String param2) {
        Section2Q5 fragment = new Section2Q5();
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
        View view = inflater.inflate(R.layout.fragment_section2_q5, container, false);

        //dia,hyperthy,hypothy,hyperten,pcod,fattyl,lactose

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        dia = view.findViewById(R.id.dia);
        hyperthy = view.findViewById(R.id.hyperthy);
        hypothy = view.findViewById(R.id.hypothy);
        hyperten = view.findViewById(R.id.hyperten);
        pcod = view.findViewById(R.id.pcod);
        fattyl = view.findViewById(R.id.fattyl);
        lactose = view.findViewById(R.id.lactose);
        oth = view.findViewById(R.id.oth);

        diagnosed = new ArrayList<>();

        diagtv = view.findViewById(R.id.textView77);



        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dia.isChecked())
                    diagnosed.add("Diabetes");
                else
                    diagnosed.remove("Diabetes");
            }
        });

        hyperthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hyperthy.isChecked())
                    diagnosed.add("Hyperthyroidism");
                else
                    diagnosed.remove("Hyperthyroidism");
            }
        });

        hypothy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hypothy.isChecked())
                    diagnosed.add("Hypothyroidism");
                else
                    diagnosed.remove("Hypothyroidism");
            }
        });

        hyperten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hyperten.isChecked())
                    diagnosed.add("Hypertension");
                else
                    diagnosed.remove("Hypertension");
            }
        });

        pcod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pcod.isChecked())
                    diagnosed.add("PCOD/PCOS");
                else
                    diagnosed.remove("PCOD/PCOS");
            }
        });

        fattyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fattyl.isChecked())
                    diagnosed.add("Fatty liver");
                else
                    diagnosed.remove("Fatty liver");
            }
        });

        lactose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fattyl.isChecked())
                    diagnosed.add("Lactose intolerance");
                else
                    diagnosed.remove("Lactose intolerance");
            }
        });






        //String url = "http://192.168.0.114/section2Q5Read.php";
        String url = "https://infits.in/androidApi/section2Q5Read.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("Checking", "Checking");
            System.out.println(DataFromDatabase.clientuserID);
            System.out.println(response);

            JSONObject jsonResponse = null;

            try {
                jsonResponse = new JSONObject(response);
                JSONArray cast = jsonResponse.getJSONArray("answer");
                for(int i=0; i<cast.length(); i++)
                {
                    JSONObject actor = cast.getJSONObject(i);
                    String answer = actor.getString("answer");
                    diagnosed.add(answer);
                    if(answer.equals("Diabetes")) dia.setChecked(true);
                    else if(answer.equals("Hyperthyroidism")) hyperthy.setChecked(true);
                    else if(answer.equals("Hypothyroidism")) hypothy.setChecked(true);
                    else if(answer.equals("Hypertension")) hyperten.setChecked(true);
                    else if(answer.equals("PCOD/PCOS")) pcod.setChecked(true);
                    else if(answer.equals("Fatty liver")) fattyl.setChecked(true);
                    else if(answer.equals("Lactose intolerance")) lactose.setChecked(true);
                    else oth.setText(answer);

                }

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

                Log.d("diagnosed Array1", diagnosed.toString());
                if (!TextUtils.isEmpty(oth.getText())){ //!other.matches("")){
                    diagnosed.add(oth.getText().toString());
                    Log.e("other added check", "Added");
                    Log.d("diagnosed Array", diagnosed.toString());
                }



                DataSectionTwo.s2q5 = diagtv.getText().toString();
                if ((!dia.isChecked()) && (!hyperten.isChecked()) && (!hyperthy.isChecked())
                && (!hypothy.isChecked()) && (!pcod.isChecked()) && (!fattyl.isChecked()) &&
                        (!lactose.isChecked())){
                    Toast.makeText(getContext(), "Select atleast one of the given options", Toast.LENGTH_SHORT).show();
                }
                else {

                    DataSectionTwo.diagnosed = diagnosed;
                    ConsultationFragment.psection2 += 1;







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
                            dataVol.put("newAnswer", Integer.toString(ConsultationFragment.psection2));
                            dataVol.put("sectionNo", "section2");


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









                    Navigation.findNavController(v).navigate(R.id.action_section2Q5_to_section2Q6);

                    //String url1 = "http://192.168.0.114/section2Q5delete.php";
                    String url1 = "https://infits.in/androidApi/section2Q5delete.php";
                    StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url1, response -> {
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

                            return dataVol;
                        }
                    };
                    stringRequest2.setRetryPolicy(new RetryPolicy() {
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
                    Volley.newRequestQueue(getActivity()).add(stringRequest2);





                    // Assuming you have the URL for your PHP script where you want to send the array
                    //String url = "http://192.168.0.114/section2Q5Update.php";
                    String url = "https://infits.in/androidApi/section2Q5Update.php";

                    for(String disease: diagnosed)
                    {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                            Log.e("Checking", "Checking1(TOP)");

                        }, error -> {
                            Log.d("Data", error.toString().trim());
                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> dataVol = new HashMap<>();
                                Log.e("Checking", "Checking(FOR)");
                                dataVol.put("clientuserID", DataFromDatabase.clientuserID);
                                dataVol.put("newAnswer", disease);



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

            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConsultationFragment.psection2>0)
                    ConsultationFragment.psection2-=1;
                requireActivity().onBackPressed();
            }
        });

        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;

    }
}