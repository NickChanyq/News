package fragment.news;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;

import java.util.ArrayList;

import adapter.NewTopAdapter;
import base.BaseFragment2;
import bean.NewBean;
import bean.NewTop;
import butterknife.Bind;
import butterknife.ButterKnife;
import network.Network;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import view.widgets.MySwipyRefreshLayout;


public class NewFunFragment extends BaseFragment2 {

    protected Subscription subscription;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefresh)
    MySwipyRefreshLayout swiperefresh;
    private NewTopAdapter adapter;
    private ArrayList<NewBean> list;

    public static NewFunFragment newInstance() {

        NewFunFragment fragemnt_location = new NewFunFragment();

        return fragemnt_location;

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("----new", "NewFunFragment setUpView");
        View view = inflater.inflate(R.layout.fragment_new2, container, false);
        ButterKnife.bind(this, view);
        swiperefresh.post(new Runnable() {
            @Override
            public void run() {
                swiperefresh.setRefreshing(true);
            }
        });
        return view;

    }

    @Override
    protected void initData() {

        list = new ArrayList<NewBean>();
        adapter = new NewTopAdapter(getContext(), list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        initDate();
        recyclerview.setAdapter(adapter);
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    private void initDate() {

        swiperefresh.setOnRefreshListener(new MySwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                swiperefresh.setRefreshing(false);
                return;
            }

            @Override
            public void onLoad(int index) {
                swiperefresh.setRefreshing(false);
                return;

            }
        });

    }

    Observer<NewTop> observer = new Observer<NewTop>() {
        @Override
        public void onCompleted() {
            //swiperefresh.setRefreshing(false);
            Log.i("--rxjava--", "onCompleted");
            swiperefresh.setRefreshing(false);
        }

        @Override
        public void onError(Throwable e) {
            Log.i("--rxjava--e", e.toString());
            swiperefresh.setRefreshing(false);
        }

        @Override
        public void onNext(NewTop d) {
            Log.i("--rxjava--onNext", d.getData().size() + "");
            adapter.notifyDataSetChanged();
            swiperefresh.setRefreshing(false);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  ButterKnife.unbind(this);
    }
}
