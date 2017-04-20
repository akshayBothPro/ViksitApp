package pro.viksit.com.viksit.home.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Feroz on 19-04-2017.
 */

public class AssessmentThread implements Runnable {
    private Context context;
    private SharedPreferences sharedPreferences;
    private int user_id;

    public AssessmentThread(Context context,SharedPreferences sharedPreferences,int user_id){
        this.context = context;
        this.sharedPreferences = sharedPreferences;
        this.user_id = user_id;
    }

    @Override
    public void run() {
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.setUrl(context.getResources().getString(R.string.serverip)+context.getResources().getString(R.string.allassessment).replaceAll("user_id",user_id+""));
        httpUtil.setType("GET");
        try {
            String jsonresponse = httpUtil.getStringResponse();
            Type listType = new TypeToken<List<DashboardCard>>() {
            }.getType();
        }catch (JsonSyntaxException jse){
            jse.printStackTrace();
        }

    }
}
