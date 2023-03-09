package com.example.infits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddMealAdapter extends RecyclerView.Adapter<AddMealAdapter.ViewHolder> {

    Context context;
    ArrayList<addmealInfo> filterItems;
    ArrayList<addmealInfo> addmealInfos;

    public AddMealAdapter(Context context, ArrayList<addmealInfo> addmealInfos){
        this.context=context;
        this.addmealInfos=addmealInfos;
        this.filterItems=addmealInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.mealdetailnfo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.addmealIcon.setImageDrawable(context.getDrawable(addmealInfos.get(position).mealIocn));
        holder.addMealName.setText(addmealInfos.get(position).mealname);
        holder.addMealQuantity.setText(addmealInfos.get(position).mealQuantity);
        holder.addMealCalorie.setText(addmealInfos.get(position).mealcalorie);
        holder.addMealWeight.setText(addmealInfos.get(position).mealWeight);
    }

    @Override
    public int getItemCount() {
        return addmealInfos.size();
    }
    public void filterList(ArrayList<addmealInfo> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        addmealInfos = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton addMealButton;
        ImageView addmealIcon;
        TextView addMealName,addMealCalorie,addMealQuantity,addMealWeight;
        public ViewHolder(View itemView){
            super(itemView);
            addMealButton=itemView.findViewById(R.id.addMealButton);
            addmealIcon=itemView.findViewById(R.id.addmealIcon);
            addMealName=itemView.findViewById(R.id.addMealName);
            addMealCalorie=itemView.findViewById(R.id.addMealCalorie);
            addMealQuantity= itemView.findViewById(R.id.addMealQuantity);
            addMealWeight=itemView.findViewById(R.id.addMealWeight);
        }
    }
}
