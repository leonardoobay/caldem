package com.example.leona.news_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leona.news_app.model.FoodItem;

import java.util.ArrayList;

/**
 * Created by leona on 8/3/2017.
 */

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.FoodsItemViewHolder>{
        //extends RecyclerView.Adapter<FoodsAdapter.FoodsItemViewHolder> {

    private ArrayList<FoodItem> foodsData;
    FoodsAdapter.ItemClickListener listener;

    public FoodsAdapter(ArrayList<FoodItem> foodsData, FoodsAdapter.ItemClickListener listener) {
        this.foodsData = foodsData;
     this.listener =listener;
    }

    public interface ItemClickListener {
        void onItemClick(int itemIndex);
    }

//    @Override
//    public FoodsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        View view = inflater.inflate(R.layout.recycleview, parent, false);
//
//        FoodsItemViewHolder holder = new FoodsItemViewHolder(view);
//
//        return holder;
//    }
    @Override
    public FoodsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview,parent,false);
        FoodsItemViewHolder holder = new FoodsItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder (FoodsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (foodsData == null) {
            return 0;
        }
        else {
            return foodsData.size();
        }
    }

    class FoodsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView foodName;
        TextView foodbrand;
        TextView foodCal;

        //  ImageView urlToImage;

        FoodsItemViewHolder(View view){
            super(view);
            foodName = (TextView)view.findViewById(R.id.food_name);
            foodbrand = (TextView)view.findViewById(R.id.food_brand);
            foodCal = (TextView)view.findViewById(R.id.food_calories);
            view.setOnClickListener(this);
        }

        public void bind(int pos){
            FoodItem item = foodsData.get(pos);
            foodName.setText(item.getItem_name());
            foodbrand.setText(item.getBrand_name());
            foodCal.setText(item.getNf_calories());
            //  urlToImage.setImageURI(item.getUrlToImage());
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos);
        }
    }





}
