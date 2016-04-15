package com.example.admin.w3d4animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mButtonFade;
    private Button mButtonFall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonFade = (Button) findViewById(R.id.btn_1);
        mButtonFall = (Button) findViewById(R.id.btn_2);
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
}
