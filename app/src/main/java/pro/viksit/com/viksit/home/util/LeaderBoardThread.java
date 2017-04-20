package pro.viksit.com.viksit.home.util;

import android.content.Context;
import android.content.SharedPreferences;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Feroz on 19-04-2017.
 */

public class LeaderBoardThread implements Runnable {

    private SharedPreferences sharedPreferences;
    private Context context;
    private int user_id;

    public LeaderBoardThread(Context context,SharedPreferences sharedPreferences,int user_id){
        this.sharedPreferences =sharedPreferences;
        this.context = context;
        this.user_id = user_id;
    }
    @Override
    public void run() {

        HttpUtil httpUtil = new HttpUtil();
        httpUtil.setUrl(context.getResources().getString(R.string.serverip) + "courses/user/5267/leaderboard");
        httpUtil.setType("GET");
        httpUtil.setConnectionTimeOut(5000);
        httpUtil.setSocketTimeOut(5000);
        String jsonresponse = httpUtil.getStringResponse();
        if(!jsonresponse.equalsIgnoreCase("null")){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(context.getResources().getString(R.string.leaderboard), jsonresponse);
            editor.apply();
            editor.commit();
        }
        System.out.println("this is url " );

    }
}
