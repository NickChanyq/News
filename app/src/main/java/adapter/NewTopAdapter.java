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

import bean.NewBean;
import bean.NewTop;
import butterknife.Bind;
import butterknife.ButterKnife;
import ui.fun.FunDetialActivity;
import ui.news.NewDetialActivity;

/**
 * Created by Administrator on 2017/1/15.
 */

public class NewTopAdapter extends RecyclerView.Adapter<NewTopAdapter.MyViewHolder>  {

    private ArrayList<NewBean> list;
    private Context context;

    public NewTopAdapter(Context context, ArrayList<NewBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_new_top, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        holder.newView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                NewBean newBean=list.get(position);
                Intent intent =new Intent();
                intent.setClass(context, NewDetialActivity.class);
                Bundle bunble=new Bundle();
               bunble.putSerializable("new",newBean);
                intent.putExtras(bunble);
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
        holder.title.setText(list.get(position).getTitle());
        holder.source.setText(list.get(position).getDigest());
        Glide.with(context).
                load(list.get(position).getUrl()).
                into(holder.topImage);
        holder.itemView.setTag(list.get(position));
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.top_image)
        ImageView topImage;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.source)
        TextView source;
        View newView;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            newView=view;
        }
    }
}
