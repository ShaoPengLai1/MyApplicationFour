package com.bawei.shaopenglai.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bawei.shaopenglai.R;

@SuppressLint("AppCompatCustomView")
public class WaterGroupNameLayout extends TextView {
    public WaterGroupNameLayout(Context context) {
        super(context);
    }

    public WaterGroupNameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.WaterFlowLayout);
        int color=array.getColor(R.styleable.WaterFlowLayout_textColor,Color.BLUE);
        setTextColor(color);
        array.recycle();
    }
}
