package com.example.clubdemer.alossforwords;

/**
 * Created by rharriso on 4/13/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 250; //Time (set for 3000 in production)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                if (GooglePlay.ready()) {
                   finish();
                }
                else {
                   //SPLASH_TIME_OUT += 1000;
                }
            }
        }, SPLASH_TIME_OUT);
    }
}