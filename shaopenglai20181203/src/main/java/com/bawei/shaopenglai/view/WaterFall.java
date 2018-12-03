package com.bawei.shaopenglai.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.transition.ChangeTransform;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WaterFall extends LinearLayout {

    /**
     *
     * 孩子中最高的一个
     */
    private int mChildMaxHeight;

    /**
     *
     * 每个孩子的左右边距为20
     */
    private int mHSpace=20;

    /**
     *
     * 每个孩子的上下边距为20
     */
    private int mVSpace=20;

    public WaterFall(Context context) {
        super(context);

    }

    public WaterFall(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //拿到父容器的宽高以及计算模式
        int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight=MeasureSpec.getSize(heightMeasureSpec);
        //测量孩子的大小
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //找到最高的那个孩子
        findMaxChildMaxHeight();
        //初始化值
        int top=0,left=0;
        //循环遍历所有的孩子
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View view = getChildAt(i);
            //判断是不是一行的开头
            if (left!=0){
                if ((left+view.getMeasuredWidth())>sizeWidth){
                    top+=mChildMaxHeight+mVSpace;
                    left=0;
                }
            }
            left+=view.getMeasuredWidth()+mHSpace;
        }
        setMeasuredDimension(sizeWidth,(top+mChildMaxHeight)>sizeHeight?sizeHeight:top+mChildMaxHeight);
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //找到最高的那个孩子
        findMaxChildMaxHeight();
        //初始化值
        int top = 0, left = 0;
        //循环遍历所有的孩子
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            //判断是不是一行的开头
            if (left != 0) {
                if ((left + view.getMeasuredWidth()) > getWidth()) {
                    top += mChildMaxHeight + mVSpace;
                    left = 0;
                }
            }
            view.layout(left,top,left+view.getMeasuredWidth(),top+view.getMeasuredHeight());
            left+=view.getMeasuredWidth()+mHSpace;
        }
    }

    private void findMaxChildMaxHeight() {
        mChildMaxHeight=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View view = getChildAt(i);
            if (view.getMeasuredHeight()>mChildMaxHeight){
                mChildMaxHeight=view.getMeasuredHeight();
            }
        }
    }
}
