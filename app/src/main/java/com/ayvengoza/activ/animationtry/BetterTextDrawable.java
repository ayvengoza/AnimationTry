package com.ayvengoza.activ.animationtry;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

/**
 * Created by andriizastupailo on 04.02.18.
 */

public class BetterTextDrawable extends Drawable {

    private static final int TEXT_COLOR = 0xFF311892;

    private final TextPaint mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private final String mText;
    private StaticLayout mStaticLayout;

    public BetterTextDrawable(String text){
        mText = text;
        mPaint.setColor(TEXT_COLOR);
        mPaint.setTextSize(100);
        mStaticLayout = new StaticLayout(
                mText,
                mPaint,
                (int) mPaint.measureText(mText),
                Layout.Alignment.ALIGN_NORMAL,
                1,
                0,
                false);
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) mStaticLayout.getHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) mStaticLayout.getWidth();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mStaticLayout.draw(canvas);
    }

    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mStaticLayout = new StaticLayout(
                mText,
                mPaint,
                bounds.width(),
                Layout.Alignment.ALIGN_NORMAL,
                1,
                0,
                false);
    }
}
