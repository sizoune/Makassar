package com.example.wildan.makassar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SplashScreen extends AppCompatActivity {


    private static boolean splashLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView iv1 = (ImageView) findViewById(R.id.foot);
        ImageView iv = (ImageView) findViewById(R.id.splash);
        Picasso.with(this).load(R.drawable.splashscreen).fit().centerCrop().into(iv);
        Picasso.with(this).load(R.drawable.logoidev).fit().centerCrop().into(iv1);
        if (!splashLoaded) {
            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashScreen.this, LandingPage.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, 3000);
            splashLoaded = true;
        }else {
            Intent goToMainActivity = new Intent(SplashScreen.this, LandingPage.class);
            goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToMainActivity);
            finish();
        }
    }
}
