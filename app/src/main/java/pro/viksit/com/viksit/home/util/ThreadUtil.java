package pro.viksit.com.viksit.home.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.pojo.AssessmentPOJO;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Feroz on 19-04-2017.
 */

public class ThreadUtil implements Runnable {
    private Context context;
    private SharedPreferences sharedPreferences;
    private int user_id;
    private String url,type;
    public ThreadUtil(Context context, SharedPreferences sharedPreferences, int user_id, String url, String type){
        this.context = context;
        this.sharedPreferences = sharedPreferences;
        this.user_id = user_id;
        this.url = url;
        this.type = type;
    }

    @Override
    public void run() {
        HttpUtil httpUtil = new HttpUtil();
        //httpUtil.setUrl(context.getResources().getString(R.string.serverip)+context.getResources().getString(R.string.allassessment)+user_id);
        httpUtil.setUrl(url);
        httpUtil.setType("GET");
        String jsonresponse =httpUtil.getStringResponse();
        Gson gson = new Gson();
        switch (type){
            case "Assessment":
                saveAssessment(jsonresponse,gson);
                break;
            case "Leaderboard":
                saveLeaderboard(jsonresponse,gson);
                break;
            case "Course":
                saveCourse(jsonresponse,gson);
                break;
        }


    }

    private void saveLeaderboard(String jsonresponse, Gson gson) {
        if(!jsonresponse.equalsIgnoreCase("null")){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(context.getResources().getString(R.string.leaderboard), jsonresponse);
            editor.apply();
            editor.commit();
        }
    }

    private void saveAssessment(String jsonresponse, Gson gson) {
        try {
            Type listType = new TypeToken<List<AssessmentPOJO>>() {}.getType();
            ArrayList<AssessmentPOJO> dashboardCards = (ArrayList<AssessmentPOJO>) gson.fromJson(jsonresponse, listType);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for(AssessmentPOJO assessmentPOJO:dashboardCards){
                System.out.println("XXBBXBXBXBXB -> "+assessmentPOJO.getName());
                if(assessmentPOJO != null){
                    editor.putString(context.getResources().getString(R.string.assessment)+assessmentPOJO.getId(), gson.toJson(assessmentPOJO));
                    editor.apply();
                    editor.commit();
                }
            }
        }catch (JsonSyntaxException jse){
            jse.printStackTrace();
        }catch (Exception e){

        }
    }
    private void saveCourse(String jsonresponse, Gson gson) {
    }
}
