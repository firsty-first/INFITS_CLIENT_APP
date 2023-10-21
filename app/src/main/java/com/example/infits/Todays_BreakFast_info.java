package com.example.infits;

import android.graphics.drawable.Drawable;

public class Todays_BreakFast_info {
    Drawable icon;
    String mealName, calorieValue, fatValue, proteinValue, carbsValue,  quantityValue, sizeValue;
    public Todays_BreakFast_info(Drawable icon, String mealName, String calorieValue, String fatValue, String proteinValue, String carbsValue,
                                 String quantityValue, String sizeValue){
        this.icon=icon;
        this.mealName=mealName;
        this.calorieValue=calorieValue;
        this.fatValue = fatValue;
        this.proteinValue = proteinValue;
        this.carbsValue=carbsValue;
        this.quantityValue=quantityValue;
        this.sizeValue=sizeValue;
    }

    public Todays_BreakFast_info(String mealName, String calorieValue, String fatValue, String proteinValue, String carbsValue, String quantityValue, String sizeValue) {
        this.mealName = mealName;
        this.calorieValue = calorieValue;
        this.fatValue = fatValue;
        this.proteinValue = proteinValue;
        this.carbsValue = carbsValue;
        this.quantityValue = quantityValue;
        this.sizeValue = sizeValue;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getCalorieValue() {
        return calorieValue;
    }

    public void setCalorieValue(String calorieValue) {
        this.calorieValue = calorieValue;
    }

    public String getFatValue() {
        return fatValue;
    }

    public void setFatValue(String fatValue) {
        this.fatValue = fatValue;
    }

    public String getProteinValue() {
        return proteinValue;
    }

    public void setProteinValue(String proteinValue) {
        this.proteinValue = proteinValue;
    }

    public String getCarbsValue() {
        return carbsValue;
    }

    public void setCarbsValue(String carbsValue) {
        this.carbsValue = carbsValue;
    }

    public String getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(String quantityValue) {
        this.quantityValue = quantityValue;
    }

    public String getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue;
    }
}
