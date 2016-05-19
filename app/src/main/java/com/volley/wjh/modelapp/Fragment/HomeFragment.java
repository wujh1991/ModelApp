package com.volley.wjh.modelapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.volley.wjh.modelapp.Base.Fragment.BaseFragment;
import com.volley.wjh.modelapp.CustomWidget.CountTimeButton;
import com.volley.wjh.modelapp.CustomWidget.ExpandMenu.FoldMenuActivity;
import com.volley.wjh.modelapp.CustomWidget.LoadingDialog.WLoadingDialog;
import com.volley.wjh.modelapp.CustomWidget.LoadingDialog.ZProgressHUD;
import com.volley.wjh.modelapp.CustomWidget.PullToRefresh2.TestActivity;
import com.volley.wjh.modelapp.CustomWidget.SwipeView.MySwipeBackActivity;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.UI.AddressSelectActivity;
import com.volley.wjh.modelapp.UI.BannerViewActivity;
import com.volley.wjh.modelapp.UI.BitMapSampleSizeActivity;
import com.volley.wjh.modelapp.UI.CardViewActivity;
import com.volley.wjh.modelapp.UI.GridViewWithRecyclerViewActivity;
import com.volley.wjh.modelapp.UI.MeterialProgressActivity;
import com.volley.wjh.modelapp.UI.PullToRefreshActivity;
import com.volley.wjh.modelapp.UI.RecyclerViewActivity;
import com.volley.wjh.modelapp.UI.SwpeRefreshLayoutActivity;
import com.volley.wjh.modelapp.UI.WaterFallViewWithRecyclerViewActivity;
import com.volley.wjh.modelapp.Update.ApkDownloadManager;

/**
 * Created by wujh on 2016/3/10.
 * Email:1049334820@qq.com
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private Button btnBanner;
    private Button btnAppDownLoad;
    private Button btnAddressSelect;
    private Button btnMeterialProgress;
    private Button btnLoadingDialog;
    private Button btnCardVIew;
    private ZProgressHUD progressHUD;

    private Button btnSwipeRefreshLayout;
    private Button btnRecyclerView;
    private Button btnGridView;
    private Button btnWaterFallView;
    private CountTimeButton cbtnTime;

    private Button btnPullToRefresh;
    private Button btnSwipeBack;
    private Button btnFoldMenu;
    private Button btnBitMapSimple;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getResourceLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void findViews(View rootView) {
        btnBanner = (Button) rootView.findViewById(R.id.btnBanner);
        btnAppDownLoad = (Button) rootView.findViewById(R.id.btnAppDownLoad);
        btnSwipeRefreshLayout = (Button) rootView.findViewById(R.id.btnSwipeRefreshLayout);
        btnRecyclerView = (Button) rootView.findViewById(R.id.btnRecyclerView);
        btnGridView = (Button) rootView.findViewById(R.id.btnGridView);
        btnWaterFallView = (Button) rootView.findViewById(R.id.btnWaterFallView);
        cbtnTime = (CountTimeButton) rootView.findViewById(R.id.cbtnTime);
        btnAddressSelect = (Button) rootView.findViewById(R.id.btnAddressSelect);
        btnMeterialProgress = (Button) rootView.findViewById(R.id.btnMeterialProgress);
        btnLoadingDialog = (Button) rootView.findViewById(R.id.btnLoadingDialog);
        btnCardVIew = (Button) rootView.findViewById(R.id.btnCardVIew);
        btnPullToRefresh = (Button) rootView.findViewById(R.id.btnPullToRefresh);
        btnSwipeBack = (Button) rootView.findViewById(R.id.btnSwipeBack);
        btnFoldMenu = (Button) rootView.findViewById(R.id.btnFoldMenu);
        btnBitMapSimple = (Button) rootView.findViewById(R.id.btnBitMapSimple);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {
        btnBanner.setOnClickListener(this);
        btnAppDownLoad.setOnClickListener(this);
        btnSwipeRefreshLayout.setOnClickListener(this);
        btnRecyclerView.setOnClickListener(this);
        btnGridView.setOnClickListener(this);
        btnWaterFallView.setOnClickListener(this);
        btnAddressSelect.setOnClickListener(this);
        btnMeterialProgress.setOnClickListener(this);
        btnLoadingDialog.setOnClickListener(this);
        btnCardVIew.setOnClickListener(this);
        btnPullToRefresh.setOnClickListener(this);
        btnSwipeBack.setOnClickListener(this);
        btnFoldMenu.setOnClickListener(this);
        btnBitMapSimple.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBanner:
                startActivity(new Intent(getActivity(), BannerViewActivity.class));
                break;
            case R.id.btnAppDownLoad:  //app下载
                ApkDownloadManager apkDownloadManager = new ApkDownloadManager(getActivity(), "http://file.liqucn.com/upload/2013/tongxun/net.qihoo.launcher.app.whitespot_1.3_liqucn.com.apk");
                apkDownloadManager.download();
                break;
            case R.id.btnSwipeRefreshLayout:
                startActivity(new Intent(getActivity(), SwpeRefreshLayoutActivity.class));
                break;
            case R.id.btnRecyclerView:
                startActivity(new Intent(getActivity(), RecyclerViewActivity.class));
                break;
            case R.id.btnGridView:
                startActivity(new Intent(getActivity(), GridViewWithRecyclerViewActivity.class));
                break;
            case R.id.btnWaterFallView:
                startActivity(new Intent(getActivity(), WaterFallViewWithRecyclerViewActivity.class));
                break;
            case R.id.btnAddressSelect:
                startActivity(new Intent(getActivity(), AddressSelectActivity.class));
                break;
            case R.id.btnMeterialProgress:
                startActivity(new Intent(getActivity(), MeterialProgressActivity.class));
                break;
            case R.id.btnLoadingDialog:
                openLoading();
                break;
            case R.id.btnCardVIew:
                startActivity(new Intent(getActivity(), CardViewActivity.class));
                break;
            case R.id.btnPullToRefresh:
//                startActivity(new Intent(getActivity(), PullToRefreshActivity.class));
                startActivity(new Intent(getActivity(), TestActivity.class));
                break;
            case R.id.btnSwipeBack:
                startActivity(new Intent(getActivity(), MySwipeBackActivity.class));
                break;
            case R.id.btnFoldMenu:
                startActivity(new Intent(getActivity(), FoldMenuActivity.class));
                break;
            case R.id.btnBitMapSimple:
                startActivity(new Intent(getActivity(), BitMapSampleSizeActivity.class));
        }
    }

    public void openLoading() {
        progressHUD = ZProgressHUD.getInstance(getActivity());
        progressHUD.setMessage("加载中...");
        progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
        progressHUD.show();
    }

}
