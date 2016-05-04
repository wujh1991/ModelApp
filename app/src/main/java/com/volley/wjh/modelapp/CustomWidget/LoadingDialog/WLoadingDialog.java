package com.volley.wjh.modelapp.CustomWidget.LoadingDialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/4/10.
 * Email:1049334820@qq.com
 * 自定义加载对话框
 */
public class WLoadingDialog extends Dialog{

    private View view;
    private ProgressBar pbLoadingIcon;
    private TextView tvTipContent;

    public WLoadingDialog(Context context) {
        super(context, R.style.CustomLoadigDialog);
        view = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null,false);
        pbLoadingIcon = (ProgressBar) view.findViewById(R.id.pbLoadingIcon);
        tvTipContent = (TextView) view.findViewById(R.id.tvTipContent);
        setContentView(view);
    }

    public void setTipContent(String tipContent){
        tvTipContent.setText(tipContent);
    }
}
