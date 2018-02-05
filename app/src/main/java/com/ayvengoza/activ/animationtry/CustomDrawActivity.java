package com.ayvengoza.activ.animationtry;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by andriizastupailo on 04.02.18.
 */

public class CustomDrawActivity extends AppCompatActivity {
    private ConstraintLayout mConstraintLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_draw);
        mConstraintLayout = (ConstraintLayout)findViewById(R.id.custom_text);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_delete);
        mConstraintLayout.setBackground(new SimpleImageDrawable(bitmap));
    }
}
