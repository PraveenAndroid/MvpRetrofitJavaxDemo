package com.example.pc.mvpretrofitjavaxdemo.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pc.mvpretrofitjavaxdemo.MainActivity;
import com.example.pc.mvpretrofitjavaxdemo.R;
import com.example.pc.mvpretrofitjavaxdemo.model.Result;
import java.util.List;

/**
 * Created by pc on 8/29/2018.
 */

public class MoviesAadapter extends RecyclerView.Adapter<MoviesAadapter.MyViewHolder> {

    public List<Result> movieList;
    public Context context;

    public MoviesAadapter(List<Result> results, MainActivity mainActivity) {

        this.context=mainActivity;
        this.movieList=results;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Log.d("name","dddd");
        Log.d("name",movieList.get(position).getTitle());
        holder.tvTitle.setText(movieList.get(position).getTitle());
        holder.tvOverview.setText(movieList.get(position).getOverview());
        holder.tvReleaseDate.setText(movieList.get(position).getReleaseDate());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+movieList.get(position).getPosterPath()).into(holder.ivMovie);

    }

    @Override
    public int getItemCount() {

        Log.d("size",movieList.size()+"");
        return movieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvOverview,tvReleaseDate;
        ImageView ivMovie;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverView);
            tvReleaseDate = (TextView) itemView.findViewById(R.id.tvReleaseDate);
            ivMovie = (ImageView) itemView.findViewById(R.id.ivMovie);
        }
    }
}
