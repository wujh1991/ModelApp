package com.volley.wjh.modelapp.CustomWidget.SwipeView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.UI.ArcMenuActivity;

/**
 * Created by wujh on 2016/4/20.
 * Email:1049334820@qq.com
 */
public class MySwipeBackActivity extends BaseSwipeBackActivity implements View.OnClickListener{

    private Button btnArcMenu;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_swipe_back;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        btnArcMenu = (Button) findViewById(R.id.btnArcMenu);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {
        btnArcMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnArcMenu:
                startActivity(new Intent(MySwipeBackActivity.this, ArcMenuActivity.class));
                break;
        }
    }
}
