package com.example.leona.news_app.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.leona.news_app.model.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

/**
 * Created by leona on 6/18/2017.
 */

public class NetworkUtils {



    /*

    application ID  f85a3dc7

    api key  ec9e2990978081871f45fc9fe8d97f3b
    https://api.nutritionix.com/v1_1/search/
    cheddar%20cheese?fields=item_name%2Citem_id%2Cbrand_name%2
    Cnf_calories%2Cnf_total_fat&appId=f85a3dc7&appKey=ec9e2990978081871f45fc9fe8d97f3b
     */
    //Define the appropriate base_url and query_parameter constants
    // (make sure they are Java constants) here as static class members.
    //5. 5pts: Create a static method in NetworkUtils that uses Uri.Builder to build the appropriate url,
    // the url you used in (2), to return a completed Java URL.
//    private static final String NEWS_BASE_URL = "https://api.nutritionix.com/v1_1/search/cheddar%20cheese?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=f85a3dc7&";
//    private static final String queryApiKey= "apiKey";
    private static final String NEWS_BASE_URL ="https://api.nutritionix.com/v1_1/search/mcdonalds?results=0:10&fields=item_name,brand_name,item_id,nf_calories&appId=f85a3dc7&";
    private static final String queryApiKey ="appKey";
    //appKey=ec9e2990978081871f45fc9fe8d97f3b

    //******************


    public static URL makeUrl() {
        //build the url to were well retrive info
        Uri builtUri = Uri.parse(NEWS_BASE_URL).buildUpon()
                .appendQueryParameter(queryApiKey,"ec9e2990978081871f45fc9fe8d97f3b")
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    //6. 2pts: Put this method in your NetworkUtils class:

    public static String getResponseFromHttpUrl(URL url) throws IOException
    {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if(hasInput)
            {
                return scanner.next();
            }
            else {
                return null;
            }

        }
        finally {
            urlConnection.disconnect();
        }
    }



    public static ArrayList<FoodItem> parseJSON(String json) throws JSONException {
        ArrayList<FoodItem> output = new ArrayList<>();

        JSONObject main = new JSONObject(json);

        JSONArray foods = main.getJSONArray("hits");

        for(int i = 0; i < foods.length(); i++){
            JSONObject indiefood = foods.getJSONObject(i);
                /*
                public String item_id;
    public String item_name;
    public String brand_name;
    public String nf_calories;
                 */
            String item_id = indiefood.getString("item_id");
            String item_name = indiefood.getString("item_name");
            String brand_name = indiefood.getString("brand_name");
            String nf_calories = indiefood.getString("nf_calories");


            FoodItem item = new FoodItem(item_id,item_name,brand_name,nf_calories);
            output.add(item);
        }

        return output;
    }











}


