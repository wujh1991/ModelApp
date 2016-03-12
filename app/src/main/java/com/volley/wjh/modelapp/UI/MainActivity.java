package com.volley.wjh.modelapp.UI;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;
import com.volley.wjh.modelapp.Adapter.MyPagerAdapter;
import com.volley.wjh.modelapp.Adapter.QQMenuAdapter;
import com.volley.wjh.modelapp.Base.Activity.BaseFragmentActivity;
import com.volley.wjh.modelapp.Base.Fragment.BaseFragment;
import com.volley.wjh.modelapp.CustomWidget.CircleImageView;
import com.volley.wjh.modelapp.CustomWidget.DragLayout;
import com.volley.wjh.modelapp.CustomWidget.RecyclerView.RecyclerViewItemDecoration;
import com.volley.wjh.modelapp.DataModel.QQMenu;
import com.volley.wjh.modelapp.Fragment.FirstFragment;
import com.volley.wjh.modelapp.Fragment.HomeFragment;
import com.volley.wjh.modelapp.Interface.OnRecyclerViewItemClickListener;
import com.volley.wjh.modelapp.Interface.OnRecyclerViewItemLongClickListener;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.Utils.Image.ImageUtil;
import com.volley.wjh.modelapp.Utils.other.ItemDataUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private CircleImageView civHeadPortrait;
    private ImageView ivRight;
    private DragLayout dragLayout;
    private RecyclerView rcvQQMenu;
    private QQMenuAdapter mQqMenuAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<QQMenu> mData;

    private ViewPager viewPager;
    private List<BaseFragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getResourceLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void findViews(){
        civHeadPortrait = (CircleImageView) findViewById(R.id.civHeadPortrait);
        ivRight = (ImageView) findViewById(R.id.ivRight);
        dragLayout = (DragLayout)findViewById(R.id.dy_m);
        rcvQQMenu = (RecyclerView)findViewById(R.id.rcvQQMenu);
        viewPager = (ViewPager) findViewById(R.id.vpMain);
    }

    @Override
    public void init(){
        initDragLayout();
        showImage();
        mFragmentList = new ArrayList<BaseFragment>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new FirstFragment());

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),mFragmentList));
    }

    @Override
    public void setListener(){
        civHeadPortrait.setOnClickListener(this);
        ivRight.setOnClickListener(this);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.civHeadPortrait:
                dragLayout.open();
                break;
            case R.id.ivRight:
                break;
        }
    }

    public void showImage(){
        ImageUtil.displayImage(civHeadPortrait, "http://avatar.csdn.net/6/6/D/1_lfdfhl.jpg");
    }

    public void initDragLayout(){
        initQQMenu();
        dragLayout.setDragListener(new DragLayout.DragListener() {
            @Override
            public void onOpen() {

            }

            @Override
            public void onClose() {

            }

            @Override
            public void onDrag(float percent) {
                ViewHelper.setAlpha(civHeadPortrait, 1 - percent);
            }
        });
    }

    public void initQQMenu(){
        mData = ItemDataUtil.getMenuData();
        linearLayoutManager = new LinearLayoutManager(this);
        rcvQQMenu.setLayoutManager(linearLayoutManager);
        rcvQQMenu.setHasFixedSize(true);
        rcvQQMenu.addItemDecoration(new RecyclerViewItemDecoration(this));
        mQqMenuAdapter = new QQMenuAdapter(mData);
        rcvQQMenu.setAdapter(mQqMenuAdapter);

        mQqMenuAdapter.setmOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener<QQMenu>() {
            @Override
            public void onClick(View view, QQMenu item, int position) {
                Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_LONG).show();
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, SwpeRefreshLayoutActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, GridViewWithRecyclerViewActivity.class));
                        break;
                }
            }
        });

        mQqMenuAdapter.setmOnRecyclerViewItemLongClickListener(new OnRecyclerViewItemLongClickListener<QQMenu>() {
            @Override
            public void onLongClick(View view, QQMenu item, int position) {
                Toast.makeText(MainActivity.this, item.getName() + "你长按了我", Toast.LENGTH_LONG).show();
            }
        });

    }

    public DragLayout getDragLayout(){
        return dragLayout;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position){
            case 0:
                dragLayout.setDrag(true);
                break;
            case 1:
                dragLayout.setDrag(false);
                break;

        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
