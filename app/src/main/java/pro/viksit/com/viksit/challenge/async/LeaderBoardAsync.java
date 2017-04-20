package pro.viksit.com.viksit.challenge.async;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.challenge.activity.LeaderBoardActivity;
import pro.viksit.com.viksit.challenge.adapter.LeaderBoardRecyclerAdapter;
import pro.viksit.com.viksit.challenge.pojo.LeaderBoardCourse;
import pro.viksit.com.viksit.util.HttpUtil;


/**
 * Created by Akshay on 17/04/2017.
 */

public class LeaderBoardAsync extends AsyncTask<String, Integer, String> {

    private static final String TAG = "LeaderBoardAsync";

   /* private Context context;
    private RecyclerView recycler;
    private LeaderBoardActivity activity;
    private ArrayList<LeaderBoardCourse> list;
    private LeaderBoardRecyclerAdapter adapter;*/

   private ArrayList<LeaderBoardCourse> leaderBoardCourses;

    private Context context;
    private Gson gson = new Gson();

    public LeaderBoardAsync(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        HttpUtil httpUtil = new HttpUtil();
        httpUtil.setUrl(context.getResources().getString(R.string.serverip) + "courses/user/5209/leaderboard");
        httpUtil.setType("GET");
        httpUtil.setConnectionTimeOut(5000);
        httpUtil.setSocketTimeOut(5000);
        String jsonresponse = httpUtil.getStringResponse();
       /* Gson gson = new Gson();*/

        if(!jsonresponse.equalsIgnoreCase("null")){
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(context.getResources().getString(R.string.leaderboard), jsonresponse);
            editor.apply();
            editor.commit();
        }
        Log.v(TAG,jsonresponse);
        System.out.println("this is url " + httpUtil.getUrl());

        return jsonresponse;
    }

}
