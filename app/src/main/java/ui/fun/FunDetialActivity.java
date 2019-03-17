package ui.fun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;

import base.BaseActivity;
import bean.Movie;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FunDetialActivity extends Activity {
    @Bind(R.id.movie_director)
    TextView movieDirector;
    @Bind(R.id.movie_starring)
    TextView movieStarring;
    @Bind(R.id.imageview)
    ImageView imageView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_fun_detial);
        ButterKnife.bind(this);
      toolbar.setNavigationIcon(R.mipmap.bt_back);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });
        Bundle movieBundle=getIntent().getExtras();
        Movie movie=(Movie) movieBundle.getSerializable("movie");
        String name = movie.getMovie_name();
        toolbar.setTitle(name);
        Glide.with(this).load(movie.getMovie_picture()).into(imageView);
        movieDirector.setText(movie.getMovie_director());
        movieStarring.setText(movie.getMovie_starring());
    }
}
