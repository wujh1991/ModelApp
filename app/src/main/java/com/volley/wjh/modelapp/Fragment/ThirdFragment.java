package com.volley.wjh.modelapp.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.volley.wjh.modelapp.Base.Fragment.BaseFragment;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.UI.GreenDaoActivity;

/**
 * Created by wujh on 2016/3/29.
 * Email:1049334820@qq.com
 */
public class ThirdFragment extends BaseFragment implements View.OnClickListener{

    private Button btnGreenDao;
    private Button btnOther;

    @Override
    public int getResourceLayout() {
        return R.layout.fragment_third;
    }

    @Override
    public void findViews(View rootView) {
        btnGreenDao = (Button) rootView.findViewById(R.id.btnGreenDao);
        btnOther = (Button) rootView.findViewById(R.id.btnOther);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {
        btnGreenDao.setOnClickListener(this);
        btnOther.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGreenDao:
                startActivity(new Intent(getActivity(), GreenDaoActivity.class));
                break;
            case R.id.btnOther:
                break;
        }
    }
}
