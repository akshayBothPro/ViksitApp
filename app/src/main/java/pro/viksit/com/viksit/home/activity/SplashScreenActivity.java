package pro.viksit.com.viksit.home.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.dashboard.pojo.StudentProfile;
import pro.viksit.com.viksit.home.util.DashBoardCallable;
import pro.viksit.com.viksit.home.util.LeaderBoardThread;

/**
 * Created by ravy on 17/03/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;
    private SharedPreferences sharedpreferences;
    private StudentProfile studentProfile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);

        String profile_date= sharedpreferences.getString(getResources().getString(R.string.user_profile),"");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        studentProfile = gson.fromJson(profile_date,StudentProfile.class);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        DashBoardCallable dashBoardCallable = new DashBoardCallable(SplashScreenActivity.this,studentProfile.getId());
        FutureTask<String> dashboardFuture = new FutureTask<String>(dashBoardCallable);
        LeaderBoardThread leaderBoardThread = new LeaderBoardThread(SplashScreenActivity.this,sharedpreferences);

        executor.execute(dashboardFuture);
        executor.execute(leaderBoardThread);
                try {
                    String response =dashboardFuture.get();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(getResources().getString(R.string.dashboardcards), response);
                    editor.apply();
                    editor.commit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                executor.shutdown();
        while (!executor.isTerminated()) {
            startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));

        }
            }
        }, SPLASH_TIME_OUT);
    }

}
