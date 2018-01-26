package com.ayvengoza.activ.animationtry;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.media.Image;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int MIN_PASSWORD_LENGTH = 8;

    private TextView mTextView;
    private Button mStartButton;
    private TextInputLayout mTextInputLayoutEmail;
    private TextInputLayout mTextInputLayoutPassword;
    private TextView mTextViewEmail;
    private TextView mTextViewPassword;
    private Button mCheckButton;
    private ImageView mImageViewSvg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.textView);
        mStartButton = (Button)findViewById(R.id.btnStartAninmation);

        mTextView.setText("Hello");

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTextAnimation((TextView)findViewById(R.id.editText), "Second");
            }
        });

        mTextInputLayoutEmail = (TextInputLayout)findViewById(R.id.input_email);
        mTextViewEmail = (TextView)findViewById(R.id.email);
        mTextInputLayoutPassword = (TextInputLayout)findViewById(R.id.input_password);
        mTextViewPassword = (TextView)findViewById(R.id.password);
        mCheckButton = (Button)findViewById(R.id.check_button);

        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                boolean hasError = false;

                if(Patterns.EMAIL_ADDRESS.matcher(mTextViewEmail.getText()).matches()){
                    mTextInputLayoutEmail.setErrorEnabled(false);
                } else {
                    mTextInputLayoutEmail.setError(getString(R.string.email_error));
                    hasError = true;
                }

                int passwordLenght = mTextViewPassword.getText() == null ? 0 : mTextViewPassword.getText().length();
                if(mTextViewPassword.getText().length() >= MIN_PASSWORD_LENGTH){
                    mTextInputLayoutPassword.setErrorEnabled(false);
                } else {
                    mTextInputLayoutPassword.setError(getString(R.string.password_error));
                }

                if(hasError){
                    Toast.makeText(MainActivity.this, R.string.form_mistake,Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.form_ok,Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImageViewSvg = (ImageView)findViewById(R.id.svg_image);
        mImageViewSvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageView imageView = (ImageView) v;
                final AnimatedVectorDrawable avd = (AnimatedVectorDrawable) imageView.getDrawable();
                avd.start();
            }
        });

    }

    private void doOneAnimation(View v){
        final ObjectAnimator anim = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f, 1f);
        anim.setDuration(5000);
        anim.start();
    }

    private void doTwoAnimation(View v){
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, "x", 200f);
        ObjectAnimator animY = ObjectAnimator.ofFloat(v, "y", 50f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(5000);
        animatorSet.playTogether(animX, animY);
        animatorSet.start();
    }

    private void doTextAnimation(final TextView v, CharSequence text){
        ValueAnimator anim = ValueAnimator.ofObject(new CharSequenceEvaluator(), v.getText(), text);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration((int) text.length()*30);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final CharSequence text = (CharSequence) animation.getAnimatedValue();
                v.setText(text);
            }
        });
        anim.start();
    };

    private void doKeyFrameAnimation(final View v){
        mTextView.setEnabled(false);

        Keyframe keyframe1 = Keyframe.ofFloat(0f, 0f);
        Keyframe keyframe2 = Keyframe.ofFloat(.4f, 90f);
        Keyframe keyframe3 = Keyframe.ofFloat(.6f, 180f);
        Keyframe keyframe4 = Keyframe.ofFloat(1f, 0f);

        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.
                ofKeyframe("rotation", keyframe1, keyframe2, keyframe3, keyframe4);
        ObjectAnimator rotationAnimator = ObjectAnimator.ofPropertyValuesHolder(v, propertyValuesHolder);
        rotationAnimator.setDuration(5000);
        rotationAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mStartButton.setEnabled(true);
            }
        });
        rotationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        rotationAnimator.start();
    }

    private void doPropertyAnimation(View v){
        v.animate().setDuration(5000).x(200f).y(50f).start();
    }


}
