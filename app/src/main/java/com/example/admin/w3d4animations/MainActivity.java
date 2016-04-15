package com.example.admin.w3d4animations;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mButtonFade;
    private Button mButtonFall;
    private Button mButtonStrechAndColor;
    private Button mButtonFadeXml;
    private Button mButtonMultiXml;
    private Button mButtonValueAnimator;
    private Button mButtonViewAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonFade = (Button) findViewById(R.id.btn_1);
        mButtonFall = (Button) findViewById(R.id.btn_2);
        mButtonStrechAndColor = (Button) findViewById(R.id.btn_3);
        mButtonFadeXml = (Button) findViewById(R.id.btn_4);
        mButtonMultiXml = (Button) findViewById(R.id.btn_5);
        mButtonValueAnimator = (Button) findViewById(R.id.btn_6);
        mButtonViewAnimation = (Button) findViewById(R.id.btn_7);
    }

    public void fadeOut(View view) {
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(mButtonFade, "alpha", 0.2f);
        fadeAnim.setDuration(3000);
        fadeAnim.setRepeatCount(ValueAnimator.INFINITE);
        fadeAnim.setRepeatMode(ValueAnimator.REVERSE);
        fadeAnim.start();
    }

    public void fall(View view) {
        ObjectAnimator moveAnim = ObjectAnimator.ofFloat(mButtonFall, "Y", 1000);
        moveAnim.setDuration(2000);
        moveAnim.setInterpolator(new BounceInterpolator());
        moveAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(MainActivity.this, "End!", Toast.LENGTH_SHORT).show();
            }
        });
        moveAnim.start();
    }

    public void strechAndColor(View view) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(mButtonStrechAndColor, "scaleX", 1.0f, 2.0f)
                        .setDuration(2000),
                ObjectAnimator.ofFloat(mButtonStrechAndColor, "scaleY", 1.0f, 2.0f)
                        .setDuration(2000),
                ObjectAnimator.ofObject(mButtonStrechAndColor, "backgroundColor", new ArgbEvaluator(),
          /*Red*/0xFFFF8080, /*Blue*/0xFF8080FF)
                        .setDuration(2000)
        );
        set.start();
    }

    public void fadeOutXML(View view) {
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.fade_out);
        anim.setTarget(mButtonFadeXml);
        anim.start();
    }

    public void multiXML(View view) {
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.multi);
        anim.setTarget(mButtonMultiXml);
        anim.setDuration(1000);
        anim.setStartDelay(10);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(MainActivity.this, "Started...", Toast.LENGTH_SHORT).show();
            };
        });
        anim.start();
    }

    public void valueAnimator(View view) {
        // Construct the value animator and define the range
        ValueAnimator valueAnim = ValueAnimator.ofFloat(0, 1);
        valueAnim.setTarget(mButtonValueAnimator);
        // Animate over the course of 700 milliseconds
        valueAnim.setDuration(700);
        // Choose an interpolator
        valueAnim.setInterpolator(new DecelerateInterpolator());
        // Define how to update the view at each "step" of the animation
        valueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                // Use the animated value to affect the view
            }
        });
        // Start the animation if not already running
        if (!valueAnim.isStarted()) {
            valueAnim.start();
        }
    }

    public void viewAnimation(View view) {
        // Inflate animation from XML
        Animation animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        // Setup listeners (optional)
        animFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Fires when animation starts
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // ...
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // ...
            }
        });
        // start the animation
        mButtonViewAnimation.startAnimation(animFadeOut);
    }
}
