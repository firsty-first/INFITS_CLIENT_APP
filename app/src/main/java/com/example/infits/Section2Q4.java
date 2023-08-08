package com.example.infits;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section2Q4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section2Q4 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, reporttv, textView79;
    CardView ivUpload;

    ImageView ivUploadimg;

    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgpath = null;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section2Q4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section2Q4.
     */
    // TODO: Rename and change types and number of parameters
    public static Section2Q4 newInstance(String param1, String param2) {
        Section2Q4 fragment = new Section2Q4();
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
        View view = inflater.inflate(R.layout.fragment_section2_q4, container, false);

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);

        reporttv = view.findViewById(R.id.textView80);

        textView79 = view.findViewById(R.id.textView79);

        ivUpload = view.findViewById(R.id.ivUpload);

        ivUploadimg = view.findViewById(R.id.ivUploadimg);

        ivUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Image", "Start");
                pickImage();

                Log.e("Image", "Emd");


            }
        });

        retrieveImage();





        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataSectionTwo.s2q4 = reporttv.getText().toString();
                try {
                    Navigation.findNavController(v).navigate(R.id.action_section2Q4_to_section2Q5);
                    if (imgpath.equals("") || imgpath.equals(" "))
                        Toast.makeText(getContext(), "Upload an image", Toast.LENGTH_SHORT).show();
                    else {
                        ConsultationFragment.psection2 += 1;


                        Navigation.findNavController(v).navigate(R.id.action_section2Q4_to_section2Q5);



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


                    }
                }catch(NullPointerException ex){

                }


                uploadImage();
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

    // Create an ActivityResultLauncher for selecting an image
    private ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null && result.getData().getData() != null) {
                    Uri imageUri = result.getData().getData();
                    try {
                        Log.e("Image", "inside imagePickerLauncher");
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                        ivUploadimg.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    );




    /*private void selectImage() {
        try {

            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Camera Permission error", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "Change app permission in your device settings", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }*/

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (requestCode == PICK_IMAGE_GALLERY) {
            try {
                if (data.getData() != null) {
                    Uri selectedImage = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    //ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    // bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                    ivUploadimg.setImageBitmap(bitmap);
                    imgpath = getRealPathFromURI(selectedImage);
                    destination = new File(imgpath.toString());
                    //Toast.makeText(getActivity(), "Path: "+imgPath, Toast.LENGTH_SHORT).show();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
                    byte[] b = baos.toByteArray();
                    DataSectionTwo.imgPath = Base64.encodeToString(b, Base64.DEFAULT);

                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), "No picture selected", Toast.LENGTH_SHORT).show();
            }

        }
    }*/

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        Log.e("Image", "inside pickImage");
        imagePickerLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }



    private void uploadImage() {
        Log.e("Image", "inside uploadImage upper");
        if (bitmap != null) {
            Log.e("Image", "inside uploadImage");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);


            String uploadUrl = "http://192.168.0.114/section2Q4ImageUpload.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadUrl, response -> {
                Log.e("Checking", "CheckingImage");

            }, error -> {
                Log.d("Data", error.toString().trim());
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> dataVol = new HashMap<>();
                    Log.e("Image", "Image uploaded successfully");
                    dataVol.put("clientuserID", DataFromDatabase.clientuserID);
                    dataVol.put("image", imageString);
                    //Log.e("imageString", imageString);



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


            /*JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("image", imageString);
                jsonObject.put("clientuserID", DataFromDatabase.clientuserID);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            //String uploadUrl = "https://infits.in/androidApi/section2Q4ImageUpload.php";
            String uploadUrl = "http://192.168.0.114/section2Q4ImageUpload.php";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, uploadUrl, jsonObject,
                    response -> {
                        // Handle server response here
                        Log.e("Image upload status", "Image uploaded");
                    },
                    error -> {
                        // Handle error here
                    });

            requestQueue.add(request);*/
        }
    }


    private void retrieveImage() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String retrieveImageUrl = "http://192.168.0.114/section2Q4ImageRetrieve.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, retrieveImageUrl, response -> {
            Log.e("Checking", "Checking1");
            System.out.println(DataFromDatabase.clientuserID);
            System.out.println(response);

            JSONObject jsonResponse = null;

            try {
                jsonResponse = new JSONObject(response);
                JSONArray cast = jsonResponse.getJSONArray("image");
                JSONObject actor = cast.getJSONObject(0);



                String imageData = actor.getString("image");




                byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                ivUploadimg.setImageBitmap(decodedBitmap);


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



        /*StringRequest request = new StringRequest(Request.Method.POST, retrieveImageUrl,


                new Response.Listener<>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.e("Image", "Inside Image read response");
                            // Process the response here
                            String key = response.getString("key");
                            Log.e("key", key);
                            String value = response.getString("value");
                            Log.e("value", value);
                            JSONObject jsonResponse = null;
                            jsonResponse = new JSONObject(response);
                            JSONArray cast = jsonResponse.getJSONArray("image");
                            JSONObject actor = cast.getJSONObject(0);
                            String imageData = actor.getString("imageString");




                            byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
                            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            ivUploadimg.setImageBitmap(decodedBitmap);

                            // Do something with the key-value pair
                            //Log.d("Response", "Key: " + key + ", Value: " + value);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error here
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> dataVol = new HashMap<>();
                Log.e("Checking", "Checking");
                dataVol.put("clientuserID", DataFromDatabase.clientuserID);

                return dataVol;
            }
        };*/
        /*{

            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {
                try {
                    // Parse the raw data from the response to a JSON object
                    String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    JSONObject jsonObject = new JSONObject(jsonString);

                    // Return the parsed JSON object as a Response
                    return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException | JSONException e) {
                    return Response.error(new VolleyError(e));
                }
            }
        };*/
                /*response -> {
                    try {
                        Log.e("Image", "Inside image retrieve");

                        String value = response.getString()
                        JSONObject jsonResponse = null;
                        jsonResponse = new JSONObject(response);
                        JSONArray cast = jsonResponse.getJSONArray("image");
                        JSONObject actor = cast.getJSONObject(0);
                        String imageData = actor.getString("image");

                        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
                        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        ivUploadimg.setImageBitmap(decodedBitmap);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Handle error here
                });*/

        /*requestQueue.add(request);*/
    }

}