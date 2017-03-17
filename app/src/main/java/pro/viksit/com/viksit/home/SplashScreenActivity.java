package pro.viksit.com.viksit.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import pro.viksit.com.viksit.R;

/**
 * Created by ravy on 17/03/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        /*startActivity(new Intent(SplashScreenActivity.this, WelcomeActivity.class));
        SplashScreenActivity.this.finish();*/
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                /*startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));*/
                startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_TIME_OUT);

    }

}
