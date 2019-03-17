package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/14.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> list;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        this.list=list;

    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
