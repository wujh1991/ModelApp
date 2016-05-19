package com.volley.wjh.modelapp.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.volley.wjh.modelapp.App.MyApplication;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.DataModel.WXAccessToken;
import com.volley.wjh.modelapp.DataModel.WXUserInfo;
import com.volley.wjh.modelapp.HttpRequest.Volley.GetVolleyHttpUrl;
import com.volley.wjh.modelapp.HttpRequest.Volley.VolleyHttpRequest;
import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/3/8.
 * Email:1049334820@qq.com
 */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler,View.OnClickListener{
    private Button btnWxLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.mWxapi.handleIntent(getIntent(),this);
    }

    @Override
    public int getResourceLayout() {
        return R.layout.activity_wx_login;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        btnWxLogin = (Button) findViewById(R.id.btnWxLogin);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {
        btnWxLogin.setOnClickListener(this);
    }

    public void sendAuthRequest(){
        final SendAuth.Req rep = new SendAuth.Req();
        rep.scope = "snsapi_userinfo";
        rep.state = "wx_auth_model_app";
        MyApplication.mWxapi.sendReq(rep);
    }

    public void sendGetAccessTokenRequest(String code){
        VolleyHttpRequest<WXAccessToken> accessTokenVolleyHttpRequest = new VolleyHttpRequest<WXAccessToken>(GetVolleyHttpUrl.getWxAccessToken(code)
                , WXAccessToken.class, new Response.Listener<WXAccessToken>() {
            @Override
            public void onResponse(WXAccessToken wxAccessToken) {
                if(wxAccessToken != null){
                    sendGetUserInfo(wxAccessToken.access_token,wxAccessToken.openid);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getHttpQueue().add(accessTokenVolleyHttpRequest);
    }

    public void sendGetUserInfo(String accessToken,String openId){
        VolleyHttpRequest<WXUserInfo> userInfoVolleyHttpRequest = new VolleyHttpRequest<WXUserInfo>(GetVolleyHttpUrl.getWxUserInfo(accessToken, openId)
               , WXUserInfo.class, new Response.Listener<WXUserInfo>() {
            @Override
            public void onResponse(WXUserInfo wxUserInfo) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getHttpQueue().add(userInfoVolleyHttpRequest);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if(baseResp != null){
            switch (baseResp.errCode){
                case BaseResp.ErrCode.ERR_OK:
//                String code = ((SendAuth.Resp)baseResp).code;
//                sendGetAccessTokenRequest(code);
                    break;

            }

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnWxLogin:
                Toast.makeText(this,"已点击认证请求",Toast.LENGTH_LONG).show();
                sendAuthRequest();
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MyApplication.mWxapi.handleIntent(getIntent(), this);
    }
}
