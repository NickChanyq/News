package ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myproject.R;

import java.util.ArrayList;
import java.util.List;

import adapter.ViewPagerAdapter;
import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.FunFragment;
import fragment.LearnFragment;
import fragment.MeTabFragment;
import fragment.NewFragment;


public class MainActivity extends BaseActivity {

    @Bind(R.id.viewpage)
    ViewPager viewpage;
    @Bind(R.id.iv_learn)
    ImageView ivLearn;
    @Bind(R.id.tv_chat)
    TextView tvChat;
    @Bind(R.id.ll_learn)
    LinearLayout llLearn;
    @Bind(R.id.iv_contact)
    ImageView ivContact;
    @Bind(R.id.tv_contact)
    TextView tvContact;
    @Bind(R.id.ll_contact)
    LinearLayout llContact;
    @Bind(R.id.iv_find)
    ImageView ivFind;
    @Bind(R.id.tv_find)
    TextView tvFind;
    @Bind(R.id.ll_find)
    LinearLayout llFind;
    @Bind(R.id.iv_me)
    ImageView ivMe;
    @Bind(R.id.tv_me)
    TextView tvMe;
    @Bind(R.id.ll_me)
    LinearLayout llMe;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    private ViewPagerAdapter viewPagerAapter;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void setUpView() {
    }

    @Override
    protected void setUpToolbar(int titleResId, int menuId, int mode) {
        super.setUpToolbar(R.string.activity_name_main, menuId, 1);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        List<Fragment> list=new ArrayList<Fragment>();
        list.add(new LearnFragment());
        list.add(new NewFragment());
        list.add(new FunFragment());
        list.add(new MeTabFragment());
        viewPagerAapter=new ViewPagerAdapter(getSupportFragmentManager(),list);
        viewpage.setAdapter(viewPagerAapter);
    }
    private void resetTextViewColor() {
        tvChat.setTextColor(Color.parseColor("#A6A6A6"));
        tvContact.setTextColor(Color.parseColor("#A6A6A6"));
        tvFind.setTextColor(Color.parseColor("#A6A6A6"));
        tvMe.setTextColor(Color.parseColor("#A6A6A6"));
    }
    private void resetImageViewSrc() {
        ivLearn.setImageResource(R.mipmap.chat1);
        ivContact.setImageResource(R.mipmap.contact1);
        ivFind.setImageResource(R.mipmap.find1);
        ivMe.setImageResource(R.mipmap.me1);
    }
    @OnClick({R.id.ll_learn, R.id.ll_contact, R.id.ll_find, R.id.ll_me})
    public void onClick(View view) {
        resetTextViewColor();
        resetImageViewSrc();
        switch (view.getId()) {
            case R.id.ll_learn:
                viewpage.setCurrentItem(0, false);
                ivLearn.setImageResource(R.mipmap.chat);
                tvChat.setTextColor(Color.parseColor("#008000"));
                break;
            case R.id.ll_contact:
                viewpage.setCurrentItem(1, false);
                ivContact.setImageResource(R.mipmap.contact);
                tvContact.setTextColor(Color.parseColor("#008000"));
                break;
            case R.id.ll_find:
                viewpage.setCurrentItem(2, false);
                ivFind.setImageResource(R.mipmap.find);
                tvFind.setTextColor(Color.parseColor("#008000"));
                break;
            case R.id.ll_me:
                viewpage.setCurrentItem(3, false);
                ivMe.setImageResource(R.mipmap.me);
                tvMe.setTextColor(Color.parseColor("#008000"));
                break;
        }
    }
}
