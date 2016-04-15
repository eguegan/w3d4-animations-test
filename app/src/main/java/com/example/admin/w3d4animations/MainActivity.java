package com.example.admin.w3d4animations;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mButtonFade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonFade = (Button) findViewById(R.id.btn_1);
    }

    public void fadeOut(View view) {
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(mButtonFade, "alpha", 0.2f);
        fadeAnim.start();
    }
}
