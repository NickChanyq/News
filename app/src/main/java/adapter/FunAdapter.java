package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;

import java.util.ArrayList;

import bean.Movie;
import butterknife.Bind;
import butterknife.ButterKnife;
import ui.fun.FunDetialActivity;

/**
 * Created by Administrator on 2017/1/15.
 */

public class FunAdapter extends RecyclerView.Adapter<FunAdapter.MyViewHolder> {


    private ArrayList<Movie> list;
    private Context context;

//    private RecyclerViewItemClickListener itemlistener=null;

    public FunAdapter(Context context, ArrayList<Movie> list) {
        this.list = list;
        this.context = context;
    }

//    public interface RecyclerViewItemClickListener{
//       void onItemClick(View view, Movie movies);
//    }
//    public void setItemlistener(RecyclerViewItemClickListener itemlistener) {
//        this.itemlistener = itemlistener;
//    }

//    @Override
//    public void onClick(View view) {
//          if(itemlistener!=null){
//              itemlistener.onItemClick(view, (Movie) view.getTag());
//          }
//    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_fun, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        holder.movieView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Movie movie=list.get(position);
                Intent intent =new Intent();
                intent.setClass(context, FunDetialActivity.class);
                Bundle movieBunble=new Bundle();
                movieBunble.putSerializable("movie",movie);
                intent.putExtras(movieBunble);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Glide.with(context).
                load(list.get(position).getMovie_picture()).
                into(holder.moviePicture);
        holder.movieName.setText(list.get(position).getMovie_name());
        holder.movieDirector.setText(list.get(position).getMovie_director());
        holder.movieStarring.setText(list.get(position).getMovie_starring());
        holder.itemView.setTag(list.get(position));
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.movie_picture)
        ImageView moviePicture;
        @Bind(R.id.movie_name)
        TextView movieName;
        @Bind(R.id.movie_director)
        TextView movieDirector;
        @Bind(R.id.movie_starring)
        TextView movieStarring;
        View movieView;


        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            movieView=view;
        }
    }
}
