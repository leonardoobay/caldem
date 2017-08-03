package com.example.leona.news_app.utilities;


import com.example.leona.news_app.model.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by leona on 6/20/2017.
 */

public class OpenNewsJsonUtils {
    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     * @param newsJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */


    public static boolean parse(ArrayList<FoodItem> foodItemList, String jsonString){

        try {
            JSONObject topLevelObject = new JSONObject(jsonString);
            JSONArray FoodArray = topLevelObject.getJSONArray("hits");
            System.out.println("JsonArray size: " + FoodArray.length());
            for(int i = 0; i < FoodArray.length(); i++){
                System.out.println("looping");
                JSONObject foodObject = FoodArray.getJSONObject(i);
                String item_id = foodObject.getString("item_id");
                String item_name = foodObject.getString("item_name");
                String brand_name = foodObject.getString("brand_name");
                String nf_calories = foodObject.getString("nf_calories");

                foodItemList.add(new FoodItem(item_id,item_name,brand_name,nf_calories));
            }

            return true;
        }
        catch(org.json.JSONException e){
            e.printStackTrace();
        }

        return false;

    }
}
