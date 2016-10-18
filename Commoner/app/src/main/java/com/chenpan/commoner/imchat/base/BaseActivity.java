package com.chenpan.commoner.imchat.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * V,T未指定类型，这里我们让子类去定义
 * Created by Administrator on 2016/5/24.
 */
public abstract class BaseActivity extends AppCompatActivity  {
    /**
     * 主线程
     */
    private long mUIThreadId;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            if (Build.VERSION.SDK_INT >= 21 && !isSetStatusBar()) {
                View decorView = getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= 21 && isSetStatusBar()) {
                View decorView = getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setNavigationBarColor(Color.TRANSPARENT);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        AppManager.getAppManager().addActivity(this);

    }




    /**
     * 获取UI线程ID
     *
     * @return UI线程ID
     */
    public long getUIThreadId() {
        return mUIThreadId;
    }


    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);//弹出栈
        super.onDestroy();
    }


    /**
     * 是否设置沉浸式
     *
     * @return
     */
    public abstract boolean isSetStatusBar();

    /**
     * 将模式设为singletask，跳转时系统调用
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        mUIThreadId = android.os.Process.myTid();
        super.onNewIntent(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


}
