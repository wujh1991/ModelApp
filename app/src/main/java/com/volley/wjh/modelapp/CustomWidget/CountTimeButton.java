package com.volley.wjh.modelapp.CustomWidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/3/2.
 * Email:1049334820@qq.com
 */
public class CountTimeButton extends TextView  implements View.OnClickListener{
    private static final int defaultTotalTime = 60 * 1000;
    private static final int defaultStepTime = 1 * 1000;
    private static final int ableDefaultColor = 0x6c948b;
    private static final int disableDefaultColor = 0xf2f3f3;
    private long mTotalTime = 0;
    private long mStepTime = 0;
    private int mAbleClickBackground;
    private int mDisableClickBackground;
    private MyCountDownTimer myCountTimer;

    public CountTimeButton(Context context) {
        super(context);
        init();
    }

    public CountTimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundAndTime(context,attrs);
        init();
    }

    public CountTimeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundAndTime(context,attrs);
        init();
    }

    private void init(){
        myCountTimer = new MyCountDownTimer(mTotalTime,mStepTime);
        this.setOnClickListener(this);
        this.setClickable(true);
    }

    private void setBackgroundAndTime(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountTimeButton);
        mAbleClickBackground = typedArray.getColor(R.styleable.CountTimeButton_ableClickBackground,ableDefaultColor);
        mDisableClickBackground = typedArray.getColor(R.styleable.CountTimeButton_disableClickBackground,disableDefaultColor);
        mTotalTime = typedArray.getInt(R.styleable.CountTimeButton_totalTime, defaultTotalTime);
        mStepTime = typedArray.getInt(R.styleable.CountTimeButton_stepTime,defaultStepTime);
        typedArray.recycle();
    }

    @Override
    public void onClick(View v) {
        myCountTimer.start();
    }

    public  class MyCountDownTimer extends CountDownTimer{

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            CountTimeButton.this.setClickable(false);
            CountTimeButton.this.setText(millisUntilFinished / 1000 + "s后重新发送");
            CountTimeButton.this.setTextColor(Color.GRAY);
            CountTimeButton.this.setBackground(new ColorDrawable(mDisableClickBackground));
        }

        @Override
        public void onFinish() {
            CountTimeButton.this.setText("获取验证码");
            CountTimeButton.this.setTextColor(Color.BLACK);
            CountTimeButton.this.setClickable(true);
            CountTimeButton.this.setBackground(new ColorDrawable(mAbleClickBackground));
        }
    }
}
