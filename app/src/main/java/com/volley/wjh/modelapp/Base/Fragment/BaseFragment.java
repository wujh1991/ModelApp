package com.volley.wjh.modelapp.Base.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wujh on 2016/2/22.
 * Email:1049334820@qq.com
 */
public abstract class BaseFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getResourceLayout(),container,false);
        findViews(rootView);
        init();
        setListener();
        return rootView;
    }

    public abstract int getResourceLayout();

    public abstract  void findViews(View rootView);

    public abstract  void init();

    public abstract  void setListener();

}
