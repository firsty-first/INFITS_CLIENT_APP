package com.example.infits;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Adapter_Todays_Meal extends RecyclerView.Adapter<Adapter_Todays_Meal.ViewHolder> {

    Context context;
    ArrayList<Todays_Meal_info> todays_meal_infos;
    ArrayList<String> mealInfotransfer;


    public Adapter_Todays_Meal(Context context,ArrayList<Todays_Meal_info> todays_meal_infos){
        this.todays_meal_infos=todays_meal_infos;
        this.context=context;
        mealInfotransfer=new ArrayList<>();
    }
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.todays_breakfast_detail,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.icon.setImageDrawable(todays_meal_infos.get(position).icon);
        holder.mealName.setText(todays_meal_infos.get(position).mealName);
        holder.calorieValue.setText(todays_meal_infos.get(position).calorieValue);
        holder.fatvalue.setText(todays_meal_infos.get(position).fatvalue);
        holder.protinValue.setText(todays_meal_infos.get(position).protinValue);
        holder.carbsValue.setText(todays_meal_infos.get(position).carbsValue);
        holder.quantityValue.setText(todays_meal_infos.get(position).quantityValue);
        holder.sizeValue.setText(todays_meal_infos.get(position).sizeValue);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String sharedPreferencesName = "Todays" + todays_meal_infos.get(position).Meal_Type;
                    SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
                    JSONObject jsonObject = new JSONObject(sharedPreferences.getString(sharedPreferencesName, ""));
                    JSONArray jsonArray=jsonObject.getJSONArray(sharedPreferencesName);
                    Log.d("sharedPreferences", jsonObject.toString());

//                    Boolean jsonObject1=jsonArray.equals(todays_meal_infos.get(position).mealName);
//                    JSONArray jsonArray1=jsonArray.remove(position);
                    jsonArray.remove((jsonArray.length()-1)-position);
                    jsonObject.put(sharedPreferencesName,jsonArray);

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(sharedPreferencesName,jsonObject.toString());
                    editor.commit();
//                    Bundle bundle=new Bundle();
//                    bundle.putString("mealInfoForPhoto",jsonObject.toString());
                    Log.d("sharedPreferences1", sharedPreferences.getString(sharedPreferencesName,"").toString());
                    if (todays_meal_infos.get(position).Meal_Type.equals("BreakFast")){
                        Navigation.findNavController(v).navigate(R.id.selfTransition);

                    }
                    if (todays_meal_infos.get(position).Meal_Type.equals("Lunch")){
                        Navigation.findNavController(v).navigate(R.id.selfTransition);


                    }
                    if (todays_meal_infos.get(position).Meal_Type.equals("Dinner")){
//                        FragmentTodays_Dinner fragmentTodays_dinner=new FragmentTodays_Dinner();
//                        fragmentTodays_dinner.requireActivity().finishAndRemoveTask();


                        Navigation.findNavController(v).navigate(R.id.selfTransition);


//                        fragmentTodays_dinner.AfterDeleteFromSharedPreference(position);
//                        todays_meal_infos.remove(position);

//                        fragmentTodays_dinner.Change(todays_meal_infos);

                    }
                    if (todays_meal_infos.get(position).Meal_Type.equals("Snacks")){
                        Navigation.findNavController(v).navigate(R.id.selfTransition);

                    }


                }catch (Exception e){
                    Log.d("Exception", e.toString());
                }
            }
        });
        holder.addTomealTrackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealInfotransfer.clear();

                Drawable icon=todays_meal_infos.get(position).icon;
                String Meal_Name=todays_meal_infos.get(position).mealName;
                String Meal_type=todays_meal_infos.get(position).Meal_Type;
                String calorie=todays_meal_infos.get(position).calorieValue;
                String carbs=todays_meal_infos.get(position).carbsValue;
                String protin=todays_meal_infos.get(position).protinValue;
                String fat=todays_meal_infos.get(position).fatvalue;

                mealInfotransfer.add(Meal_Name);
                mealInfotransfer.add(Meal_type);
                mealInfotransfer.add(calorie);
                mealInfotransfer.add(carbs);
                mealInfotransfer.add(protin);
                mealInfotransfer.add(fat);
                mealInfotransfer.add(String.valueOf(icon));
                mealInfotransfer.add("1");

                Bundle bundle=new Bundle();
                bundle.putStringArrayList("mealInfotransfer",mealInfotransfer);
                Log.d("mealInfotransfer",mealInfotransfer.toString());

                if(Meal_type.equals("BreakFast")){

                    Navigation.findNavController(v).navigate(R.id.action_FragmentTodaysBreakFast_to_mealInfoWithPhoto_meal_tracker,bundle);
                }
                if(Meal_type.equals("Lunch")){

                    Navigation.findNavController(v).navigate(R.id.action_FragmentTodaysLunch_to_mealInfoWithPhoto_meal_tracker,bundle);
                }
                if(Meal_type.equals("Dinner")){


                    Navigation.findNavController(v).navigate(R.id.action_FragmentTodaysDinner_to_mealInfoWithPhoto_meal_tracker,bundle);
                }
                if(Meal_type.equals("Snacks")){

                    Navigation.findNavController(v).navigate(R.id.action_FragmentTodaysSnacks_to_mealInfoWithPhoto_meal_tracker,bundle);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return todays_meal_infos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        ImageButton deleteButton;
        Button addTomealTrackerButton;
        TextView mealName, calorieValue, fatvalue, protinValue, carbsValue,  quantityValue, sizeValue;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            icon=itemView.findViewById(R.id.mealImage);
            mealName=itemView.findViewById(R.id.mealName);
            calorieValue=itemView.findViewById(R.id.calorieValue);
            fatvalue=itemView.findViewById(R.id.fatValue);
            protinValue=itemView.findViewById(R.id.protinValue);
            carbsValue=itemView.findViewById(R.id.carbsValue);
            quantityValue=itemView.findViewById(R.id.quantityValue);
            sizeValue=itemView.findViewById(R.id.sizeValue);
            deleteButton=itemView.findViewById(R.id.deleteButton);
            addTomealTrackerButton=itemView.findViewById(R.id.addTomealTrackerButton);
        }
    }
}
