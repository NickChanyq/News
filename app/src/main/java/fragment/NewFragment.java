package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;

import base.BaseFragment2;
import butterknife.Bind;
import butterknife.ButterKnife;
import fragment.news.NewCarFragment;
import fragment.news.NewFinanceFragment;
import fragment.news.NewJokeFragment;
import fragment.news.NewMilitaryFragment;
import fragment.news.NewSportFragment;
import fragment.news.NewTopFragment;
import fragment.news.NewFunFragment;
import view.tab.PagerSlidingTabStrip;

public class NewFragment extends BaseFragment2 {


    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    private NewTopFragment topFragment;
    private NewFunFragment funFragment;
    private NewMilitaryFragment militaryFragment;
    private NewCarFragment carFragment;
    private NewFinanceFragment financeFragment;
    private NewJokeFragment jokeFragment;
    private NewSportFragment sportFragment;
    private MyPagerAdapter adapter;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("-----fragment", "New initViews()");
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        Log.i("-----fragment", "New initData()");
        //getChildFragmentManager()获取下一级别管理者
        adapter = new MyPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabs.setUnderlineHeight(2);

        tabs.setViewPager(pager);
        tabs.setIndicatorHeight(4);
        tabs.setIndicatorColor(Color.parseColor("#2e73e3"));
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // ButterKnife.unbind(this);
        Log.i("-----fragment", "new destory()");
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"头条", "娱乐",
                "军事", "汽车", "财经", "笑话", "体育",
        };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    if (topFragment == null) {
                        topFragment = NewTopFragment.newInstance();
                    }
                    return topFragment;
                case 1:
                    if (funFragment == null) {
                        funFragment = NewFunFragment.newInstance();
                    }
                    return funFragment;
                case 2:
                    if (militaryFragment == null) {
                        militaryFragment = NewMilitaryFragment.newInstance();
                    }
                    return militaryFragment;
                case 3:
                    if (carFragment == null) {
                        carFragment = NewCarFragment.newInstance();
                    }
                    return carFragment;
                case 4:
                    if (financeFragment == null) {
                        financeFragment = NewFinanceFragment.newInstance();
                    }
                    return financeFragment;
                case 5:
                    if (jokeFragment == null) {
                        jokeFragment = NewJokeFragment.newInstance();
                    }
                    return jokeFragment;
                case 6:
                    if (sportFragment == null) {
                        sportFragment = NewSportFragment.newInstance();
                    }
                    return sportFragment;

            }
            return null;
        }

    }
}
