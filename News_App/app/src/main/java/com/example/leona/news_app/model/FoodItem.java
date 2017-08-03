package com.example.leona.news_app.model;

/**
 * Created by leona on 8/3/2017.
 */

public class FoodItem {
    public FoodItem(){

    }


    /*
    item_id: "23cb594904816ae840edf65d",
    item_name: "Fried Cherry Pie",
    brand_name: "McDonald's",
    nf_calories: 230,
    nf_serving_size_qty: 1,
    nf_serving_size_unit: "serving"
     */
    public String item_id;
    public String item_name;
    public String brand_name;
    public String nf_calories;

    public FoodItem(String item_id, String item_name, String brand_name, String nf_calories) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.brand_name = brand_name;
        this.nf_calories = nf_calories;
    }


    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getNf_calories() {
        return nf_calories;
    }

    public void setNf_calories(String nf_calories) {
        this.nf_calories = nf_calories;
    }







}
