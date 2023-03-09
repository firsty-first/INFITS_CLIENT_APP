package com.example.infits;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAddLunch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddLunch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    AutoCompleteTextView lunchSpinner;
    ArrayAdapter<String> arrayAdapter;
    ImageView underlineFrequent,underlineRecent,underlineFavourites;
    TextView recentTextView,FavouritesTextview,frequentTextView;
    SearchView searchlunch;
    RecyclerView lunchitems;
    ArrayList<addmealInfo> addmealInfos;
    ImageView calorieImgback;
    String[] calorieDropDownitems={"Yesterday","Today","Tomorrow"};
    AddMealAdapter addMealAdapter;
    public FragmentAddLunch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAddLunch.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAddLunch newInstance(String param1, String param2) {
        FragmentAddLunch fragment = new FragmentAddLunch();
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
        View view=inflater.inflate(R.layout.fragment_add_lunch, container, false);
        addmealInfos=new ArrayList<>();
        hooks(view);
        AddFrequentMeal();
        calorieImgback.setOnClickListener(v -> requireActivity().onBackPressed());
        lunchSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSuggestion = (String) parent.getItemAtPosition(position);
                AddFrequentMeal();
            }
        });
        searchlunch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        recentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnderLineLayout(v);
                AddRecentMeal();
            }
        });
        frequentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnderLineLayout(v);
                AddFrequentMeal();

            }
        });
        FavouritesTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnderLineLayout(v);
                AddFavouritesMeal();

            }
        });
        addMealAdapter=new AddMealAdapter(getActivity(),addmealInfos);
        lunchitems.setAdapter(addMealAdapter);
        return view;
    }
    private void filter(String text) {
        ArrayList<addmealInfo> filteredlist = new ArrayList<addmealInfo>();

        for (addmealInfo item : addmealInfos) {
            if (item.mealname.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(getActivity(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            addMealAdapter.filterList(filteredlist);
        }
    }
    private void hooks(View view){
        lunchSpinner=view.findViewById(R.id.lunchSpinner);
        arrayAdapter=new ArrayAdapter<>(requireActivity(), R.layout.dropdownitems,calorieDropDownitems);
        lunchSpinner.setAdapter(arrayAdapter);
        underlineFrequent=view.findViewById(R.id.underlineFrequent);
        underlineRecent=view.findViewById(R.id.underlineRecent);
        underlineFavourites=view.findViewById(R.id.underlineFavourites);
        recentTextView=view.findViewById(R.id.recentTextView);
        FavouritesTextview=view.findViewById(R.id.FavouritesTextview);
        frequentTextView=view.findViewById(R.id.frequentTextView);
        searchlunch=view.findViewById(R.id.searchlunch);
        lunchitems=view.findViewById(R.id.lunchitems);
        calorieImgback=view.findViewById(R.id.calorieImgback);
        lunchitems.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }
    private void AddRecentMeal(){
        addmealInfos.clear();
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Pizza","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));

    }
    private void AddFrequentMeal(){
        addmealInfos.clear();
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));

    }
    private void AddFavouritesMeal(){
        addmealInfos.clear();
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));
        addmealInfos.add(new addmealInfo(R.drawable.hotdog,"Hotdog","50 kcal","1 Roti","10g"));

    }
    private void UnderLineLayout(View view){
        switch (view.getId()){
            case R.id.frequentTextView:
                underlineFrequent.setVisibility(View.VISIBLE);
                underlineRecent.setVisibility(View.INVISIBLE);
                underlineFavourites.setVisibility(View.INVISIBLE);
                break;
            case R.id.recentTextView:
                underlineRecent.setVisibility(View.VISIBLE);
                underlineFrequent.setVisibility(View.INVISIBLE);
                underlineFavourites.setVisibility(View.INVISIBLE);
                break;
            case R.id.FavouritesTextview:
                underlineFavourites.setVisibility(View.VISIBLE);
                underlineRecent.setVisibility(View.INVISIBLE);
                underlineFrequent.setVisibility(View.INVISIBLE);
                break;
            default:
                underlineFrequent.setVisibility(View.VISIBLE);
                underlineRecent.setVisibility(View.INVISIBLE);
                underlineFavourites.setVisibility(View.INVISIBLE);
                break;
        }
    }
}