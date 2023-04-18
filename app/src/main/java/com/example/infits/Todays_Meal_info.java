package com.example.infits;

import android.graphics.drawable.Drawable;

public class Todays_Meal_info {
    Drawable icon;
    String mealName, calorieValue, fatvalue, protinValue, carbsValue,  quantityValue, sizeValue,Meal_Type;
    public Todays_Meal_info(Drawable icon, String mealName,String Meal_Type, String calorieValue, String fatvalue, String protinValue, String carbsValue,
                                 String quantityValue, String sizeValue){
        this.icon=icon;
        this.mealName=mealName;
        this.Meal_Type=Meal_Type;
        this.calorieValue=calorieValue;
        this.fatvalue=fatvalue;
        this.protinValue=protinValue;
        this.carbsValue=carbsValue;
        this.quantityValue=quantityValue;
        this.sizeValue=sizeValue;
    }
}
