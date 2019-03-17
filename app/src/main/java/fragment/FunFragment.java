package fragment;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.FunAdapter;
import base.BaseFragment2;
import bean.Movie;
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
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ui.fun.FunDetialActivity;
import utils.Decode;
import view.widgets.MySwipyRefreshLayout;

public class FunFragment extends BaseFragment2 {
    public static final String TAG = "FunFragment";
    private  static  final String  LOAD_COUNT="8";
    private  static int startIndex=0;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefresh)
    MySwipyRefreshLayout swiperefresh;
    private FunAdapter adapter;

    private ArrayList<Movie> list;

    private Context context = getActivity();

    Call<Movie> call;
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fun, container, false);
        ButterKnife.bind(this, view);
        swiperefresh.setOnRefreshListener(new MySwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                Log.i("---onRefresh", "---");
                list.clear();
                adapter.notifyDataSetChanged();
                startIndex=0;
                loadDate();
                swiperefresh.setRefreshing(false);
            }

            @Override
            public void onLoad(int index) {
                loadDate();
               swiperefresh.setRefreshing(false);
            }
        });

        return view;
    }
    @Override
    protected void initData() {
        list = new ArrayList<Movie>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        adapter = new FunAdapter(getContext(), list);
        recyclerview.setAdapter(adapter);
        loadDate();
    }
    @Override
    protected void setDefaultFragmentTitle(String title) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
      //  ButterKnife.unbind(this);
        if (call != null) {
            call.cancel();
        }
       Log.i("-----fragment","fun destory()");
    }
    private void loadDate() {
        Api api = Network.getInstance2();
        Map map = new HashMap();
        map.put("city", "广州");
        map.put("start", startIndex + "");
        map.put("count", LOAD_COUNT);
        Call<ResponseBody> call = api.getMovieString(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    //JSON数据解析
                    String jsonStr = new String(response.body().bytes());
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray array = jsonObject.getJSONArray("subjects");
                    Log.i("-----fragment", array.length() + "");
                    for (int i = 0; i < array.length(); i++) {
                        Movie movie = new Movie();
                        JSONObject movieObject = array.getJSONObject(i);
                        Log.i("-----fragment", Decode.decode(movieObject.getString("title")));
                        movie.setMovie_name(Decode.decode(movieObject.getString("title")));
                        JSONObject picObiect = movieObject.getJSONObject("images");
                        String pic = picObiect.getString("small");
                        movie.setMovie_picture(pic);
                        JSONArray directors=movieObject.getJSONArray("directors");
                        String directorsName="导演： ";
                        for (int k = 0; k < directors.length(); k++){
                            JSONObject director=directors.getJSONObject(k);
                            String name=Decode.decode(director.getString("name"));
                            if(k==directors.length()-1){
                            directorsName=directorsName+name;
                            }
                            else{
                                directorsName=directorsName+name+"  |  ";
                            }
                        }
                        String starsName="主演： ";
                        JSONArray stars=movieObject.getJSONArray("casts");
                        for( int j=0;j<stars.length();j++){
                            JSONObject star=stars.getJSONObject(j);
                            String starName=Decode.decode(star.getString("name"));
                            if(j==stars.length()-1){
                                starsName=starsName+starName;
                            }
                            else{
                                starsName=starsName+starName+"  |  ";
                            }
                        }
                        movie.setMovie_director(directorsName);
                        movie.setMovie_starring(starsName);
                        list.add(movie);
                    }
                    startIndex = startIndex + Integer.parseInt(LOAD_COUNT);
                    Log.i("-----fragment", startIndex + "");
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.i("-----fragment", "exception");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Log.i("-----fragment", throwable.toString());
            }
        });

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        startIndex=0; //fragment切换状态时，令开始位归零
    }



}