package com.ccr.acstatusbarutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ccr.library.ACStatusBarUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private CheckBox mChbTranslucent;
    private ViewGroup contentLayout;
    private SeekBar mSbChangeAlpha;
    private TextView mTvStatusAlpha;

    private int mStatusBarColor;
    private int mAlpha = ACStatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        contentLayout = (ViewGroup) findViewById(R.id.main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mChbTranslucent = (CheckBox) findViewById(R.id.chb_translucent);
        findViewById(R.id.btn_set_color).setOnClickListener(this);
        findViewById(R.id.btn_set_transparent).setOnClickListener(this);
        findViewById(R.id.btn_set_translucent).setOnClickListener(this);
        findViewById(R.id.btn_set_for_image_view).setOnClickListener(this);
        findViewById(R.id.btn_use_in_fragment).setOnClickListener(this);
        findViewById(R.id.btn_set_color_for_swipe_back).setOnClickListener(this);
        mSbChangeAlpha = (SeekBar) findViewById(R.id.sb_change_alpha);
        mTvStatusAlpha = (TextView) findViewById(R.id.tv_status_alpha);
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        mChbTranslucent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChbTranslucent.isChecked()) {
                    contentLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_monkey));
                    ACStatusBarUtil.setTranslucentForDrawerLayout(MainActivity.this, mDrawerLayout, mAlpha);
                    mToolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                } else {
                    contentLayout.setBackgroundDrawable(null);
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    ACStatusBarUtil.setColorForDrawerLayout(MainActivity.this, mDrawerLayout,
                            getResources().getColor(R.color.colorPrimary), mAlpha);
                }
            }
        });

        mSbChangeAlpha.setMax(255);
        mSbChangeAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                if (mChbTranslucent.isChecked()) {
                    ACStatusBarUtil.setTranslucentForDrawerLayout(MainActivity.this, mDrawerLayout, mAlpha);
                } else {
                    ACStatusBarUtil.setColorForDrawerLayout(MainActivity.this, mDrawerLayout, mStatusBarColor, mAlpha);
                }
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_color:
                intent = new Intent(mActivity, ColorStatusBarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_set_transparent:
                intent = new Intent(mActivity, ImageStatusBarActivity.class);
                intent.putExtra(ImageStatusBarActivity.EXTRA_IS_TRANSPARENT, true);
                startActivity(intent);
                break;
            case R.id.btn_set_translucent:
                intent = new Intent(mActivity, ImageStatusBarActivity.class);
                intent.putExtra(ImageStatusBarActivity.EXTRA_IS_TRANSPARENT, true);
                startActivity(intent);
                break;
            case R.id.btn_set_for_image_view:
                intent = new Intent(mActivity, ImageViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_use_in_fragment:
                intent = new Intent(mActivity, UseInFragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_set_color_for_swipe_back:
                intent = new Intent(mActivity, SwipeBackActivity.class);
                startActivity(intent);
                break;

        }
    }
    @Override
    protected void setStatusBar() {
        mStatusBarColor = getResources().getColor(R.color.colorPrimary);
        ACStatusBarUtil.setColorForDrawerLayout(this, (DrawerLayout) findViewById(R.id.drawer_layout), mStatusBarColor, mAlpha);
    }
}
