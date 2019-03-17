package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;

import java.util.ArrayList;

import bean.NewTop;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/15.
 */

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.MyViewHolder> implements View.OnClickListener {

    private ArrayList<NewTop.New> list;
    private Context context;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, NewTop.New data);
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public NewAdapter(Context context, ArrayList<NewTop.New> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_new_top, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.source.setText("来源于:"+list.get(position).getSource());
        Glide.with(context).
                load(list.get(position).getTop_image()).
                into(holder.topImage);
        holder.itemView.setTag(list.get(position));
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (NewTop.New) v.getTag());
        }

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.top_image)
        ImageView topImage;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.source)
        TextView source;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
