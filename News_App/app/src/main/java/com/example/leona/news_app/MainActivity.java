package com.example.leona.news_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ProgressBar;


import com.example.leona.news_app.model.FoodItem;
import com.example.leona.news_app.utilities.NetworkUtils;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private RecyclerView rView;
    private ProgressBar progress;

    // private TextView textView;

  private  FoodsAdapter mAdapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = (ProgressBar) findViewById(R.id.progress);
        rView = (RecyclerView) findViewById(R.id.rv_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rView.setLayoutManager(layoutManager);

    }


    //oncreateoptons and menu inflater allow the searchicon xml to become an object and be displayed
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchicon, menu);
        return true;

    }


    //*****************************************8

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemNumber = item.getItemId();

        if (itemNumber == R.id.action_search) {
            URL url = NetworkUtils.makeUrl();
            new FoodsRequests().execute(url);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class FoodsRequests extends AsyncTask<URL, Void, ArrayList<FoodItem>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // textView.setText("");
            progress.setVisibility(View.VISIBLE);

        }

        @Override
        protected ArrayList<FoodItem> doInBackground(URL... params) {
            ArrayList<FoodItem> output;
            URL url = NetworkUtils.makeUrl();

            try {
                String json = NetworkUtils.getResponseFromHttpUrl(url);

                output = NetworkUtils.parseJSON(json);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return output;
        }


        @Override
        protected void onPostExecute(final ArrayList<FoodItem> foodsData) {
            super.onPostExecute(foodsData);
            progress.setVisibility(View.INVISIBLE);
            if (foodsData != null) {
                FoodsAdapter adapter = new FoodsAdapter(foodsData, new FoodsAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int itemIndex) {
                    }
                });
                rView.setAdapter(adapter);
            }
//            progress.setVisibility(View.INVISIBLE);
//            if (foodsData != null) {
//                FoodsAdapter adapter = new FoodsAdapter(foodsData, new FoodsAdapter.ItemClickListener() {
//                    @Override
//                    public void onItemClick(int itemIndex) {
//                        Context context = MainActivity.this;
//                       // String url = foodsData.get(itemIndex).getUrl();
//                      // String url = foodsData.get(itemIndex).g
//                        //Uri website = Uri.parse(url);
//
//                        //Intent intent = new Intent(Intent.ACTION_VIEW, website);
//
////                        if (intent.resolveActivity(getPackageManager()) != null) {
////                            startActivity(intent);
////                        }
//                    }
//                });
//                rView.setAdapter(adapter);
//            }
               }
        }






}



