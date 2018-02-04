package com.ayvengoza.activ.animationtry;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by andriizastupailo on 04.02.18.
 */

public class CustomTextActivity extends AppCompatActivity {
    private ConstraintLayout mConstraintLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_text);
        mConstraintLayout = (ConstraintLayout)findViewById(R.id.custom_text);
        mConstraintLayout.setBackground(new SimpleTextDrawable("Custom text that I try to write more"));
    }
}
