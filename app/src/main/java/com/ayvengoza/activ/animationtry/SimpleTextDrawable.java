package com.ayvengoza.activ.animationtry;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by andriizastupailo on 04.02.18.
 */

public class SimpleTextDrawable extends Drawable {
    private static final int TEXT_COLOR = 0xFF311892;
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final String mText;

    public SimpleTextDrawable(String text){
        mText = text;
        mPaint.setColor(TEXT_COLOR);
        mPaint.setTextSize(100);
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) mPaint.getTextSize();
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) mPaint.measureText(mText);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawText(mText, 0, mPaint.getTextSize(), mPaint);
    }

    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
