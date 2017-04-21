package pro.viksit.com.viksit.home.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.dashboard.pojo.StudentProfile;
import pro.viksit.com.viksit.home.util.SplashScreenTask;
import pro.viksit.com.viksit.home.util.ThreadUtil;

/**
 * Created by Feroz on 19/04/2017.
 */

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenTask.SplashScreenTaskCallback {

    private static int SPLASH_TIME_OUT = 1000;
    private int user_id;
    private SharedPreferences sharedpreferences;
    private StudentProfile studentProfile;
    private final Handler handler = new Handler();
    private SharedPreferences.Editor editor;
    private String storedResponse;

    private Runnable startBackgroundTasks = new Runnable() {
        @Override
        public void run () {
            //  Do initial background tasks like sounds load
            SplashScreenTask task = new SplashScreenTask (SplashScreenActivity.this,editor,user_id);
            task.setListener (SplashScreenActivity.this);
            task.execute ();
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        editor  = sharedpreferences.edit();
        String profile_date= sharedpreferences.getString(getResources().getString(R.string.user_profile),"");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        studentProfile = gson.fromJson(profile_date,StudentProfile.class);
        user_id = studentProfile.getId();
        handler.postDelayed(startBackgroundTasks, SPLASH_TIME_OUT);
    }

    @Override
    public void OnSplashScreenTaskCompleted() {
        storedResponse = sharedpreferences.getString(getResources().getString(R.string.dashboardcards),"");

        int totalnos = 3;
        ExecutorService executor = null;
        ArrayList<DashboardCard> dashboardCardslist = null;
        if(!storedResponse.equalsIgnoreCase("[]")) {
            System.out.println("jsonresponse " + storedResponse);
            Type listType = new TypeToken<List<DashboardCard>>() {
            }.getType();
            dashboardCardslist = (ArrayList<DashboardCard>) new Gson().fromJson(storedResponse, listType);

        }

        if(dashboardCardslist != null){
            executor = Executors.newFixedThreadPool(totalnos+dashboardCardslist.size());
            for(DashboardCard dashboardCards:dashboardCardslist) {
                ThreadUtil threadUtil = new ThreadUtil(editor, 5239, getResources().getString(R.string.serverip) + (getResources().getString(R.string.dashboardcardurl).replaceAll("user_id", 2632 + "")) + "/" +dashboardCards.getId(),"Task",getResources().getString(R.string.task_store));
            executor.execute(threadUtil);
            }
            }else{
            executor = Executors.newFixedThreadPool(totalnos);
        }

        ThreadUtil assessmentThread = new ThreadUtil(editor,3504,getResources().getString(R.string.serverip)+getResources().getString(R.string.allassessment)+3504,"Assessment",getResources().getString(R.string.assessment));
        ThreadUtil leaderBoardThread = new ThreadUtil(editor,3504,getResources().getString(R.string.serverip)+getResources().getString(R.string.leaderboarddata).replaceAll("user_id",3504+""),"Leaderboard",getResources().getString(R.string.leaderboard));
        ThreadUtil courseThread = new ThreadUtil(editor,5209,getResources().getString(R.string.serverip)+getResources().getString(R.string.allcourse)+5209,"Course",getResources().getString(R.string.course));

        executor.execute(leaderBoardThread);
        executor.execute(assessmentThread);
        executor.execute(courseThread);

        startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));
        executor.shutdown();
    }
}
