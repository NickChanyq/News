package ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends Activity {

    @Bind(R.id.imageview)
    ImageView imageview;
    private MyHandler handler;
    private Context context;

    private static final int HANDLER_MESSAGE = 0x11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        context = SplashActivity.this;
        Glide.with(this).load(R.drawable.loading).
                asGif().
                diskCacheStrategy(DiskCacheStrategy.SOURCE).
                into(imageview);
        MyThread thread = new MyThread();
        handler = new MyHandler();
        thread.start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                handler.sendEmptyMessage(HANDLER_MESSAGE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_MESSAGE:
                    Intent intent = new Intent();
                    intent.setClass(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }
}
