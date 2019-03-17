package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myproject.R;

import java.util.ArrayList;

import bean.DataInfo;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yu on 2017/1/15.
 */

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.MyViewHolder> {

    private ArrayList<DataInfo.Data> list;
    private Context context;

    public LearnAdapter(Context context, ArrayList<DataInfo.Data> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_learn, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDesc.setText(list.get(position).desc);
        holder.tvUrl.setText(list.get(position).url);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_desc)
        TextView tvDesc;
        @Bind(R.id.tv_url)
        TextView tvUrl;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
