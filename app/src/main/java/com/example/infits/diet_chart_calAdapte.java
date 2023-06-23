package com.example.infits;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class diet_chart_calAdapte extends RecyclerView.Adapter<diet_chart_calAdapte.viewHolder>{

    ArrayList<Date> dates;
    Context context;
    OnDateSelectedListener listener;
    private Date selectedDate;

    public diet_chart_calAdapte(Context context, ArrayList<Date> arr){
        this.context=context;
        this.dates=arr;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.meal_schedule_calendar_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Date date = dates.get ( position );
        Calendar calendar = Calendar.getInstance ();
        calendar.setTime ( date );
        holder.day.setText ( new SimpleDateFormat( "EEE" , Locale.getDefault () ).format ( date ).substring ( 0 , 3 ) );
        holder.date.setText ( String.valueOf ( calendar.get ( Calendar.DAY_OF_MONTH ) ) );


        // Check if the current date being bound is today's date
        Calendar today = Calendar.getInstance ();
        if (selectedDate != null && selectedDate.equals ( date )) {
            holder.layout.setBackgroundResource ( R.drawable.diet_chart_date_selected);
            holder.day.setTextColor ( Color.WHITE );
            holder.date.setTextColor ( Color.WHITE );
        } else if (selectedDate == null && calendar.get ( Calendar.YEAR ) == today.get ( Calendar.YEAR )
                && calendar.get ( Calendar.MONTH ) == today.get ( Calendar.MONTH )
                && calendar.get ( Calendar.DAY_OF_MONTH ) == today.get ( Calendar.DAY_OF_MONTH )) {
            holder.layout.setBackgroundResource ( R.drawable.diet_chart_date_selected );
            holder.day.setTextColor ( Color.WHITE );
            holder.date.setTextColor ( Color.WHITE );
        } else {
            holder.layout.setBackgroundResource ( R.drawable.diet_chart_date );
            holder.day.setTextColor ( Color.BLACK );
            holder.date.setTextColor ( Color.BLACK );
        }
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView day,date;
        LinearLayout layout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            day=itemView.findViewById(R.id.day);
            date=itemView.findViewById(R.id.date);
            layout=itemView.findViewById(R.id.dietDate);

            itemView.setOnClickListener (v -> {
                selectedDate = dates.get ( getAdapterPosition () );
                notifyDataSetChanged();
                if (listener != null) {
                    listener.onDateSelected ( selectedDate );
                }
            });
        }
    }

    public interface OnDateSelectedListener {
        void onDateSelected ( Date date );
    }

}
