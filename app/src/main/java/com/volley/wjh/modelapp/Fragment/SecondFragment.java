package com.volley.wjh.modelapp.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.volley.wjh.modelapp.Base.Fragment.BaseFragment;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.UI.BDMapActivity;
import com.volley.wjh.modelapp.wxapi.WXEntryActivity;
import com.volley.wjh.modelapp.UI.WXShareActivity;

/**
 * Created by wujh on 2016/3/18.
 * Email:1049334820@qq.com
 */
public class SecondFragment extends BaseFragment implements View.OnClickListener {
    private Button btnWxLogin;
    private Button btnWxShare;
    private Button btnBdMap;

    @Override
    public int getResourceLayout() {
        return R.layout.fragment_second;
    }

    @Override
    public void findViews(View rootView) {
        btnWxLogin = (Button) rootView.findViewById(R.id.btnWxLogin);
        btnWxShare = (Button) rootView.findViewById(R.id.btnWxShare);
        btnBdMap = (Button) rootView.findViewById(R.id.btnBdMap);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {
        btnWxLogin.setOnClickListener(this);
        btnWxShare.setOnClickListener(this);
        btnBdMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWxLogin:
                startActivity(new Intent(getActivity(), WXEntryActivity.class));
                break;
            case R.id.btnWxShare:
                startActivity(new Intent(getActivity(), WXShareActivity.class));
                break;
            case R.id.btnBdMap:
                startActivity(new Intent(getActivity(), BDMapActivity.class));
        }
    }
}
