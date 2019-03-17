package fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;

import java.util.ArrayList;

import adapter.LearnAdapter;
import base.BaseFragment2;
import bean.DataInfo;
import butterknife.Bind;
import butterknife.ButterKnife;
import network.Api;
import network.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import view.widgets.MySwipyRefreshLayout;

public class LearnFragment extends BaseFragment2 {
    public static final String TAG = "LearnFragment";

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefresh)
    MySwipyRefreshLayout swiperefresh;
    private LearnAdapter adapter;
    private int startIndex;
    private int currentIndex = 1;

    private ArrayList<DataInfo.Data> list;

    private Context context = getActivity();

    private final int TOP_REFRESH = 1;
    private final int BOTTOM_REFRESH = 2;

    Retrofit retrofit;
    Call<DataInfo> call;

//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        Log.i(TAG, "onCreateView");
//        View view = inflater.inflate(R.layout.fragment_learn, container, false);
//        ButterKnife.bind(this, view);
//        return view;
//    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("-----fragment", "learn initViews()");
        View view = inflater.inflate(R.layout.fragment_learn, container, false);
        ButterKnife.bind(this, view);
        swiperefresh.post(new Runnable() {
            @Override
            public void run() {
                swiperefresh.setRefreshing(true);
            }
        });
        swiperefresh.setOnRefreshListener(new MySwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                Log.i("---onRefresh", "---");
                dataOption(TOP_REFRESH);
            }

            @Override
            public void onLoad(int index) {
                dataOption(BOTTOM_REFRESH);
                Log.i("---onLoad", "---");
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        Log.i("-----fragment", "learn initData()");
        list = new ArrayList<DataInfo.Data>();
        adapter = new LearnAdapter(getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);

        dataOption(1);
        adapter = new LearnAdapter(getContext(), list);
        recyclerview.setAdapter(adapter);
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }


    private void loadDate(int page) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Log.i("---page", page + "");
        call = api.getDataLearn(5, page);
        call.enqueue(new Callback<DataInfo>() {
            @Override
            public void onResponse(Call<DataInfo> call, Response<DataInfo> response) {
                response.body().toString();
                DataInfo dataInfo = response.body();
                list.addAll(dataInfo.results);
                Log.i("---list", list.size() + "");
                adapter.notifyDataSetChanged();
                swiperefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<DataInfo> call, Throwable t) {
                swiperefresh.setRefreshing(false);
            }

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (call != null) {
            call.cancel();
        }
        Log.i("-----fragment","learn destory()");
    }

    private void dataOption(int option) {
        switch (option) {
            case TOP_REFRESH:
                list.clear();
                Log.i("---", "下拉");
                loadDate(1);
                break;
            case BOTTOM_REFRESH:
                Log.i("---", "上拉");
                loadDate(currentIndex++);
                break;
        }
        adapter.notifyDataSetChanged();

    }
}
