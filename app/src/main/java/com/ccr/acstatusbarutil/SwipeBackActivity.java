package com.ccr.acstatusbarutil;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import com.ccr.library.ACStatusBarUtil;
import com.r0adkll.slidr.Slidr;

import java.util.Random;


/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-01 16:42
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */

public class SwipeBackActivity extends BaseActivity {
    private Button mBtnChangeColor;
    private int mColor = Color.GRAY;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置右滑动返回
        Slidr.attach(this);
        setContentView(R.layout.swipe_back_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mBtnChangeColor = (Button) findViewById(R.id.btn_change_color);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setBackgroundColor(mColor);
        mBtnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                mColor = 0xff000000 | random.nextInt(0xffffff);
                mToolbar.setBackgroundColor(mColor);
                ACStatusBarUtil.setColorForSwipeBack(SwipeBackActivity.this, mColor, 38);
            }
        });
    }

    @Override
    protected void setStatusBar() {
        ACStatusBarUtil.setColorForSwipeBack(this, mColor, 38);
    }
}
