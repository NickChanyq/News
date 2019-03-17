package fragment.news;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.NewTopAdapter;
import base.BaseFragment2;
import bean.NewBean;
import bean.NewTop;
import butterknife.Bind;
import butterknife.ButterKnife;
import network.Api;
import network.Constants;
import network.Network;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.internal.schedulers.NewThreadWorker;
import rx.schedulers.Schedulers;
import ui.news.NewDetialActivity;
import utils.Decode;
import view.widgets.MySwipyRefreshLayout;

public class NewTopFragment extends BaseFragment2 {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefresh)
    android.support.v4.widget.SwipeRefreshLayout swiperefresh;

    private ArrayList<NewBean> list;

    private Context context = getActivity();
    private NewTopAdapter adapter;


    public static NewTopFragment newInstance() {

        NewTopFragment fragemnt_location = new NewTopFragment();

        return fragemnt_location;

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("----new","NewTopFragment initViews");
        View view = inflater.inflate(R.layout.fragment_new_top, container, false);
        ButterKnife.bind(this, view);
        list = new ArrayList<NewBean>();
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                loadData();
                swiperefresh.setRefreshing(false);
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        Log.i("----new","initViews");
        String Chinese= Decode.decode("\\u9738\\u738b\\u522b\\u59ec");
        Log.i("----new",Chinese);
        list = new ArrayList<NewBean>();
        adapter = new NewTopAdapter(getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        loadData();
        recyclerview.setAdapter(adapter);

    }

    private void loadData() {
//        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL_NEW).
//                addConverterFactory(GsonConverterFactory.create()).build();
        Api api=Network.getInstance();
        Call<ResponseBody> call=api.getString();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonStr = new String(response.body().bytes());
                    JSONObject jsonObject=new JSONObject(jsonStr);
                    JSONObject objectData=jsonObject.getJSONObject("data");
                    JSONArray array=objectData.getJSONArray("tech");
                    Log.i("----new",array.length()+"");
                    for(int i=0;i<array.length();i++){
                        NewBean bean=new NewBean();
                        JSONObject jObject=array.getJSONObject(i);
                        Log.i("----new",jObject.getString("title"));
                        JSONArray picArray=jObject.getJSONArray("picInfo");
                        JSONObject x=picArray.getJSONObject(0);
                       String picUrl=x.getString("url");
                       Log.i("----new",picUrl);
                        Log.i("----new",jObject.getString("link"));
                        Log.i("----new",jObject.getString("digest"));
                        bean.setTitle(jObject.getString("title"));
                       bean.setUrl(picUrl);
                        bean.setLink(jObject.getString("link"));
                        bean.setDigest(jObject.getString("digest"));
                        list.add(bean);
                    }
                    adapter.notifyDataSetChanged();
                }
                catch(Exception e){
                    e.printStackTrace();
                    Log.i("----new","exception");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Log.i("----new",throwable.toString());
            }
        });




//        subscription = Network.getNewFunApi().getNewTop("1", "10")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
    }

//    Observer<NewTop> observer = new Observer<NewTop>() {
//        @Override
//        public void onCompleted() {
//            //swiperefresh.setRefreshing(false);
//            Log.i("--rxjava--", "onCompleted");
//            swiperefresh.setRefreshing(false);
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            Log.i("--rxjava--e", e.toString());
//            swiperefresh.setRefreshing(false);
//        }
//
//        @Override
//        public void onNext(NewTop d) {
//            Log.i("--rxjava--onNext", d.getData().size() + "");
//            list.addAll(d.getData());
//            adapter.notifyDataSetChanged();
//            swiperefresh.setRefreshing(false);
//        }
//    };

    @Override
    protected void setDefaultFragmentTitle(String title) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
