package base;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.myproject.R;

import java.lang.reflect.Method;


/**
 * Created yu
 *
 */
public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    protected Toolbar toolbar;
    protected TextView toolbar_title;
    public static final int MODE_BACK = 0;
    public static final int MODE_DRAWER = 1;
    public static final int MODE_NONE = 2;
    public static final int MODE_HOME = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpContentView();
        setUpView();
        setUpData(savedInstanceState);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    protected abstract void setUpContentView();

    protected abstract void setUpView();

    protected abstract void setUpData(Bundle savedInstanceState);


    /**
     *
     * @param layoutResID 填充layout
     */
    @Override
    public void setContentView(int layoutResID) {
        setContentView(layoutResID, -1, -1, MODE_BACK);
    }

    public void setContentView(int layoutResID, int titleResId) {
        setContentView(layoutResID, titleResId, -1, MODE_BACK);
    }

    public void setContentView(int layoutResID, int titleResId, int mode) {
        setContentView(layoutResID, titleResId, -1, mode);
    }

    public void setContentView(int layoutResID, int titleResId, int menuId, int mode) {
        super.setContentView(layoutResID);
        setUpToolbar(titleResId, menuId, mode);
    }

    /**
     *
     * @param titleResId  标题名称
     * @param menuId      菜单
     * @param mode        是否有返回按钮，默认有
     */
    protected void setUpToolbar(int titleResId, int menuId, int mode) {
        if (mode != MODE_NONE) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("");
            toolbar_title = (TextView) findViewById(R.id.toolbar_title);

            setSupportActionBar(toolbar);
            if (mode == MODE_BACK) {
                toolbar.setNavigationIcon(R.mipmap.bt_back);
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationBtnClicked();
                }
            });
            showToolbar(true);
            setUpTitle(titleResId);
            setUpMenu(menuId);
        }
    }

    protected  void showToolbar(boolean show){
        if (toolbar == null) {
        } else {
            int paddingTop = toolbar.getPaddingTop();
            int paddingBottom = toolbar.getPaddingBottom();
            int paddingLeft = toolbar.getPaddingLeft();
            int paddingRight = toolbar.getPaddingRight();
            int statusHeight = getStatusHeight(this);
            ViewGroup.LayoutParams params = toolbar.getLayoutParams();
            int height = params.height;
            /**
             * 利用状态栏的高度，4.4及以上版本给Toolbar设置一个paddingTop值为status_bar的高度，
             * Toolbar延伸到status_bar顶部
             **/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setTranslucentStatus(show);
                if (show) {
                    paddingTop += statusHeight;
                    height += statusHeight;
                } else {
                    paddingTop -= statusHeight;
                    height -= statusHeight;
                }
            }
            params.height = height;
            toolbar.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            toolbar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void setUpMenu(int menuId) {
        if (toolbar != null){
            toolbar.getMenu().clear();
            if (menuId > 0) {
                toolbar.inflateMenu(menuId);
                toolbar.setOnMenuItemClickListener(this);
            }
        }
    }

    /**
     * 设置标题名称
     * @param titleResId
     */
    protected void setUpTitle(int titleResId) {
        if (titleResId > 0 && toolbar_title != null) {
            toolbar_title.setText(titleResId);
        }
    }

    protected void onNavigationBtnClicked() {
        finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    /**
     * 显示菜单item中的图片；
     * 如果不设置，图片可能不显示
     * @param view
     * @param menu
     * @return
     */
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
