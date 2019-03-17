package ui.news;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.myproject.R;

import base.BaseActivity;
import bean.Movie;
import bean.NewBean;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NewDetialActivity extends BaseActivity {
    @Bind(R.id.wv_newsWebView)
    WebView newsWebview;
    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_new_detial);
    }

    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.activity_name_newDetial);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        NewBean New = (NewBean) bundle.getSerializable("new");
        String url = New.getLink();
        newsWebview.setWebViewClient(new WebViewClient());
        newsWebview.loadUrl(url);

    }
}
