package com.volley.wjh.modelapp.UI;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.volley.wjh.modelapp.App.MyApplication;
import com.volley.wjh.modelapp.App.MyConstant;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/3/18.
 * Email:1049334820@qq.com
 */
public class WXShareActivity extends BaseActivity implements View.OnClickListener{
    private CheckBox cbShareFriends;
    private Button btnShareText;
    private Button btnShareImg;

//    private IWXAPI wxapi;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_wx_share;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        cbShareFriends = (CheckBox) findViewById(R.id.cbShareFriens);
        btnShareText = (Button) findViewById(R.id.btnShareText);
        btnShareImg = (Button) findViewById(R.id.btnShareImg);
    }

    @Override
    public void init() {
//        wxapi = WXAPIFactory.createWXAPI(getApplicationContext(), MyConstant.APP_ID);
//        wxapi.registerApp(MyConstant.APP_ID);
    }

    @Override
    public void setListener() {
        btnShareText.setOnClickListener(this);
        btnShareImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShareText:
                shareText("今天下大雨咧");
                break;
            case R.id.btnShareImg:
                break;
        }
    }

    private String buildTransaction(String text){
        return (text == null) ? String.valueOf(System.currentTimeMillis()):(text + System.currentTimeMillis());
    }

    private void shareText(String text){
        WXTextObject textObject = new WXTextObject();
        textObject.text = text;

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObject;
        msg.description = text;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction(text);
        req.message = msg;
        req.scene = cbShareFriends.isChecked() ?
                SendMessageToWX.Req.WXSceneTimeline:
                SendMessageToWX.Req.WXSceneSession;
//        Toast.makeText(this,String.valueOf(MyApplication.mWxapi.sendReq(req)),Toast.LENGTH_LONG).show();
        MyApplication.mWxapi.sendReq(req);
    }
}
