package com.example.infits;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link breakFastList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class breakFastList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TabLayout tabLayout;

    ViewPager viewPager1;

    public breakFastList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment breakFastList.
     */
    // TODO: Rename and change types and number of parameters
    public static breakFastList newInstance(String param1, String param2) {
        breakFastList fragment = new breakFastList();
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
        View view = inflater.inflate(R.layout.fragment_break_fast_list, container, false);
        hooks(view);
        tabLayout.setupWithViewPager(viewPager1);
        BeakfastAdapterFoodDetails breakfastAdapterFoodDetails = new BeakfastAdapterFoodDetails(getParentFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        breakfastAdapterFoodDetails.addFragment(new breakfast_food_details_viewpager_fragment(),"Frequent");
        breakfastAdapterFoodDetails.addFragment(new breakfast_food_details_recent(),"Recent");
        breakfastAdapterFoodDetails.addFragment(new favourite_food_details_breakfast(),"Favourite");
        viewPager1.setAdapter(breakfastAdapterFoodDetails);
        return view;
    }

    private void hooks(View view) {
        tabLayout = view.findViewById(R.id.tabLayout2);
        viewPager1 = (ViewPager) view.findViewById(R.id.viewPagerN);
    }
}