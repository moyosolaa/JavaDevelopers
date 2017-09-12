package com.geekhub.javadevelopers;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;

/**
 * Created by mo.yosiwealth on 8/22/2017.
 */

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case the app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Starts the next activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                // this closes the splash
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}