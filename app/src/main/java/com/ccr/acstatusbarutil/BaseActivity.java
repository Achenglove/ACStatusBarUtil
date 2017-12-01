package com.ccr.acstatusbarutil;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.ccr.library.ACStatusBarUtil;

/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-01 16:42
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */

public class BaseActivity extends AppCompatActivity {
    public Intent intent=null;
    public Activity mActivity=null;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mActivity=this;
        setStatusBar();
    }
    protected void setStatusBar() {
        ACStatusBarUtil.setColor(mActivity, getResources().getColor(R.color.colorPrimary));
    }
}
