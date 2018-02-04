package com.ayvengoza.activ.animationtry;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.ChangeBounds;
import android.support.transition.Fade;
import android.support.transition.Scene;
import android.support.transition.Transition;
import android.support.transition.TransitionInflater;
import android.support.transition.TransitionManager;
import android.support.v4.os.TraceCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ang on 29.01.18.
 */

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mTransactionButton;
    private ViewGroup mSceneRoot;
    private Scene mScene1;
    private Scene mScene2;
    private Scene mSceneCurrent;
    private Transition mTransition;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        mLinearLayout = (LinearLayout)findViewById(R.id.linear_layout);
        mTransactionButton = (Button) findViewById(R.id.transaction_button);
        mTransactionButton.setOnClickListener(this);

        mSceneRoot = (ViewGroup) findViewById(R.id.scene_holder);
        mLinearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        mScene1 = Scene.getSceneForLayout(mSceneRoot, R.layout.frame_scene1, this);
        mScene2 = Scene.getSceneForLayout(mSceneRoot, R.layout.frame_scene2, this);

        mTransition = TransitionInflater.from(this).inflateTransition(R.transition.fade_transition);
//        TransitionManager.beginDelayedTransition(mSceneRoot, mTransition);
        mSceneCurrent = mScene1;
        TransitionManager.go(mSceneCurrent);


    }

    @Override
    public void onClick(View v) {
        TraceCompat.beginSection("TESTING - onClick");
        boolean hasScene1 = mSceneCurrent == mScene1;
        if(hasScene1){
            TransitionManager.go(mScene2, mTransition);
            mSceneCurrent = mScene2;
        } else {
            TransitionManager.go(mScene1, mTransition);
            mSceneCurrent = mScene1;
        }

        final int x = (int) v.getX();
        final int y = (int) v.getY();

        final Animator animator = ViewAnimationUtils.createCircularReveal(mLinearLayout,x, y, 0, 800);
        animator.start();
        TraceCompat.endSection();

    }
}
