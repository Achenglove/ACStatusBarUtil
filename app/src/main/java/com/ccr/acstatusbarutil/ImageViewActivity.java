package com.ccr.acstatusbarutil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ccr.library.ACStatusBarUtil;

import com.r0adkll.slidr.Slidr;


/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-01 16:42
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */
public class ImageViewActivity extends BaseActivity {
    private Toolbar mToolbar;
    private View mViewNeedOffset;
    private SeekBar mSbChangeAlpha;
    private TextView mTvStatusAlpha;

    private int mAlpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        // 设置右滑动返回
        Slidr.attach(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewNeedOffset = findViewById(R.id.view_need_offset);
        mTvStatusAlpha = (TextView) findViewById(R.id.tv_status_alpha);
        mSbChangeAlpha = (SeekBar) findViewById(R.id.sb_change_alpha);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mSbChangeAlpha.setMax(255);
        mSbChangeAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                ACStatusBarUtil.setTranslucentForImageView(ImageViewActivity.this, mAlpha, mViewNeedOffset);
                mTvStatusAlpha.setText(String.valueOf(mAlpha));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSbChangeAlpha.setProgress(ACStatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    @Override
    protected void setStatusBar() {
        mViewNeedOffset = findViewById(R.id.view_need_offset);
        ACStatusBarUtil.setTranslucentForImageView(this, mViewNeedOffset);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
