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
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section2Q8#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section2Q8 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, famtv;
    CheckBox dia,hyperthy,hypothy,hyperten,pcod,fattyl,lactose;
    EditText oth;
    ArrayList<String> fam;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section2Q8() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section2Q8.
     */
    // TODO: Rename and change types and number of parameters
    public static Section2Q8 newInstance(String param1, String param2) {
        Section2Q8 fragment = new Section2Q8();
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
        View view = inflater.inflate(R.layout.fragment_section2_q8, container, false);

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

        fam = new ArrayList<>();

        famtv = view.findViewById(R.id.textView77);

        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dia.isChecked())
                    fam.add("Diabetes");
                else
                    fam.remove("Diabetes");
            }
        });

        hyperthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hyperthy.isChecked())
                    fam.add("Hyperthyroidism");
                else
                    fam.remove("Hyperthyroidism");
            }
        });

        hypothy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hypothy.isChecked())
                    fam.add("Hypothyroidism");
                else
                    fam.remove("Hypothyroidism");
            }
        });

        hyperten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hyperten.isChecked())
                    fam.add("Hypertension");
                else
                    fam.remove("Hypertension");
            }
        });

        pcod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pcod.isChecked())
                    fam.add("PCOD/PCOS");
                else
                    fam.remove("PCOD/PCOS");
            }
        });

        fattyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fattyl.isChecked())
                    fam.add("Fatty liver");
                else
                    fam.remove("Fatty liver");
            }
        });

        lactose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fattyl.isChecked())
                    fam.add("Lactose intolerance");
                else
                    fam.remove("Lactose intolerance");
            }
        });

        String other = oth.getText().toString();

        if(other!=null)
            fam.add(other);



        //String url = "http://192.168.0.114/section2Q8Read.php";
        String url = "https://infits.in/androidApi/section2Q8Read.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("Checking", "Checking1");
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
                    fam.add(answer);
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


                Log.d("diagnosed Array1", fam.toString());
                if (!TextUtils.isEmpty(oth.getText())){ //!other.matches("")){
                    fam.add(oth.getText().toString());
                    Log.e("other added check", "Added");
                    Log.d("diagnosed Array", fam.toString());
                }

                DataSectionTwo.familyHistory = fam;
                DataSectionTwo.s2q8 = famtv.getText().toString();


                //Toast.makeText(getContext(), "Data:" + DataSectionTwo.familyHistory, Toast.LENGTH_SHORT).show();
                if ((!dia.isChecked()) && (!hyperten.isChecked()) && (!hyperthy.isChecked())
                        && (!hypothy.isChecked()) && (!pcod.isChecked()) && (!fattyl.isChecked()) &&
                        (other.equals("") || other.equals(" ")))
                    Toast.makeText(getContext(), "Select atleast one of the given options", Toast.LENGTH_SHORT).show();
                else {
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








                    Navigation.findNavController(v).navigate(R.id.action_section2Q8_to_consultationFragment);


                    //String url1 = "http://192.168.0.114/section2Q8delete.php";
                    String url1 = "https://infits.in/androidApi/section2Q8delete.php";
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
                    //String url = "http://192.168.0.114/section2Q8Update.php";
                    String url = "https://infits.in/androidApi/section2Q8Update.php";

                    for(String disease: fam)
                    {
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
                                dataVol.put("newAnswer", disease);
                                dataVol.put("table", "section3Q4");


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