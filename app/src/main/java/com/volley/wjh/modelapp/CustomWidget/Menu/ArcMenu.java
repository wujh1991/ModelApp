package com.volley.wjh.modelapp.CustomWidget.Menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/4/22.
 * Email:1049334820@qq.com
 * 自定义卫星菜单
 */
public class ArcMenu extends ViewGroup implements View.OnClickListener{
    private static final String TAG = ArcMenu.class.getSimpleName();

    private static final int POS_LEFT_TOP = 0;
    private static final int POS_RIGHT_TOP = 1;
    private static final int POS_LEFT_BOTTOM = 2;
    private static final int POS_RIGHT_BOTTOM = 3;
    private static final int POS_CENTER_BOTTOM = 4;

    private int mRadius;
    private Position mPosition = Position.CENTER_BOTTOM;
    private Status mCurrentStatus = Status.CLOSE;

    private View mCButton;
    //主菜单宽度
    private int mCBWidth;
    //主菜单高度
    private int mCBHeight;

    /**
     * 主菜单位置
     */
    public enum Position {
        LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM, CENTER_BOTTOM
    }

    /**
     * 菜单状态
     */
    public enum Status {
        OPEN, CLOSE
    }


    public ArcMenu(Context context) {
        this(context, null);
    }

    public ArcMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ArcMenu);
        mRadius = (int) a.getDimension(R.styleable.ArcMenu_radius,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                        getResources().getDisplayMetrics()));
        int pos = a.getInt(R.styleable.ArcMenu_position, POS_CENTER_BOTTOM);
        switch (pos) {
            case POS_LEFT_TOP:
                mPosition = Position.LEFT_TOP;
                break;
            case POS_RIGHT_TOP:
                mPosition = Position.RIGHT_TOP;
                break;
            case POS_LEFT_BOTTOM:
                mPosition = Position.LEFT_BOTTOM;
                break;
            case POS_RIGHT_BOTTOM:
                mPosition = Position.RIGHT_BOTTOM;
                break;
            case POS_CENTER_BOTTOM:
                mPosition = Position.CENTER_BOTTOM;
                break;
        }
        a.recycle();

        Log.e(TAG, "mRadius = " + mRadius + " mPosition = " + mPosition);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for(int i = 0; i < count;i++){
            //测量child
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            layoutCButton();
            layoutChilds();
        }
    }

    /**
     * 定位子菜单
     */
    private void layoutChilds() {
        int count = getChildCount();
        for(int i = 1; i < count;i++){
            View child = getChildAt(i);
            child.setVisibility(View.GONE);
            int cWidth = child.getMeasuredWidth();
            int cHeigth = child.getMeasuredHeight();
            int cl = (int) ((mRadius + mCBWidth /2 + cWidth /2) * Math.sin(Math.PI / 2 /count * i))
                    + mCBWidth / 2 - cWidth / 2;
            int ct = (int) ((mRadius + mCBWidth /2 + cWidth /2) * Math.cos(Math.PI /2 /count * i))
                    + mCBHeight / 2 - cHeigth / 2 ;

            //左下，右下,中下
            if(mPosition == Position.LEFT_BOTTOM
                    || mPosition == Position.RIGHT_BOTTOM){
                ct = getMeasuredHeight() - cHeigth - ct;
            }

            //右上，右下
            if(mPosition == Position.RIGHT_TOP
                    || mPosition == Position.RIGHT_BOTTOM){
                cl = getMeasuredWidth() - cWidth -cl;
            }

            //中下
            if(mPosition == Position.CENTER_BOTTOM){
                cl = (getMeasuredWidth() / 2 - cWidth / 2)
                        - (int) ((mRadius + mCBWidth / 2 + cWidth / 2)
                        * Math.cos(Math.PI / count * i));
                ct = (getMeasuredHeight() - cHeigth )
                        - (int)((mRadius + mCBWidth / 2 + cWidth / 2)
                        * Math.sin(Math.PI / count * i));
            }

            child.layout(cl,ct,cl + cWidth,ct + cHeigth);
        }
    }

    /**
     * 定位主菜单
     */
    private void layoutCButton() {
        mCButton = getChildAt(0);
        mCButton.setOnClickListener(this);

        int l = 0;
        int t = 0;

        mCBWidth = mCButton.getMeasuredWidth();
        mCBHeight = mCButton.getMeasuredHeight();

        switch (mPosition){
            case LEFT_TOP:
                l = 0;
                t = 0;
                break;
            case RIGHT_TOP:
                l = getMeasuredWidth() - mCBWidth;
                t = 0;
                break;
            case LEFT_BOTTOM:
                l = 0;
                t = getMeasuredHeight() - mCBHeight;
                break;
            case RIGHT_BOTTOM:
                l = getMeasuredWidth() - mCBWidth;
                t = getMeasuredHeight() - mCBHeight;
                break;
            case CENTER_BOTTOM:
                l = getMeasuredWidth() /2 - mCBWidth / 2;
                t = getMeasuredHeight() - mCBHeight;
                break;
        }
        mCButton.layout(l, t, l + mCBWidth, t + mCBHeight);
    }

    @Override
    public void onClick(View v) {
        if(mCurrentStatus == Status.CLOSE){
            rotateCButton(v, 0f, 45f, 300);
        }else{
            rotateCButton(v, 45f, 0f, 300);
        }
        toggleMenu(300);
    }

    private void toggleMenu(int duration) {
        int count = getChildCount();
        for(int i =  1; i < count;i++) {
            final View child = getChildAt(i);
            child.setVisibility(View.VISIBLE);
            int cWidth = child.getMeasuredWidth();
            int cHeigth = child.getMeasuredHeight();
            int cl = (int) ((mRadius + mCBWidth /2 + cWidth /2) * Math.sin(Math.PI / 2 /count * i))
                    + mCBWidth / 2 - cWidth / 2;
            int ct = (int) ((mRadius + mCBWidth /2 + cWidth /2) * Math.cos(Math.PI /2 /count * i))
                    + mCBHeight / 2 - cHeigth / 2 ;

            int xflag = 1;
            int yflag = 1;
            if(mPosition == Position.LEFT_TOP || mPosition == Position.LEFT_BOTTOM){
                xflag = -1;
            }

            if(mPosition == Position.LEFT_TOP|| mPosition == Position.RIGHT_TOP){
                yflag = -1;
            }

            if(mPosition == Position.CENTER_BOTTOM){
                cl = (getMeasuredWidth() / 2 - cWidth / 2)
                        - (int) ((mRadius + mCBWidth / 2 + cWidth / 2)
                        * Math.cos(Math.PI / count * i));
                ct = (getMeasuredHeight() - cHeigth )
                        - (int)((mRadius + mCBWidth / 2 + cWidth / 2)
                        * Math.sin(Math.PI / count * i));

                Log.i(TAG, "xflag = " + xflag + "yflag = " + yflag);
            }

            AnimationSet animset = new AnimationSet(true);;
            Animation tranAnim = null;

            if(mCurrentStatus == Status.CLOSE){
                if(mPosition == Position.CENTER_BOTTOM){
                    tranAnim = new TranslateAnimation(getMeasuredWidth() / 2 - mCBWidth / 2 - xflag * cl,0,getMeasuredHeight() - mCBHeight - yflag * ct,0);
                }else{
                    tranAnim = new TranslateAnimation(xflag * cl,0,yflag * ct,0);
                }
                child.setClickable(true);
                child.setFocusable(true);
            }else{
                if(mPosition == Position.CENTER_BOTTOM){
                    tranAnim = new TranslateAnimation(0,getMeasuredWidth() / 2 - mCBWidth / 2  - xflag * cl,0,getMeasuredHeight() - mCBHeight - yflag * ct);
                }else{
                    tranAnim = new TranslateAnimation(0,xflag * cl,0,yflag * ct);
                }
                child.setClickable(false);
                child.setFocusable(false);
            }
            tranAnim.setDuration(duration);
            tranAnim.setFillAfter(true);
            tranAnim.setStartOffset((i * 100) / count);

            tranAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (mCurrentStatus == Status.CLOSE) {
                        child.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            RotateAnimation rorateAnim = new RotateAnimation(0f,720f,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            rorateAnim.setDuration(duration);
            rorateAnim.setFillAfter(true);

            ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnim.setDuration(duration);
            scaleAnim.setFillAfter(true);

            AlphaAnimation alphaAnim = new AlphaAnimation(1f, 0.0f);
            alphaAnim.setDuration(duration);
            alphaAnim.setFillAfter(true);

            //添加动画
            animset.addAnimation(rorateAnim);
            animset.addAnimation(scaleAnim);
            animset.addAnimation(alphaAnim);
            animset.addAnimation(tranAnim);
            child.startAnimation(animset);
        }
        //切换菜单状态
        changeStatus();
    }

    private void changeStatus() {
        mCurrentStatus = (mCurrentStatus == Status.CLOSE ? Status.OPEN : Status.CLOSE);
    }

    private void rotateCButton(View v, float start, float end, int duration) {
        RotateAnimation anim = new RotateAnimation(start,end,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        v.startAnimation(anim);
    }
}
