package fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;

import base.BaseFragment2;
import butterknife.ButterKnife;

public class MeTabFragment extends BaseFragment2 {

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("-----fragment", "me initview()");
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        Log.i("-----fragment", "me initData()");
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("-----fragment","me destory()");
    }
}
