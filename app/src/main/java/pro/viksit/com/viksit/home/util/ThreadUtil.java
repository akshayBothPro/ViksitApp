package pro.viksit.com.viksit.home.util;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.assessment.pojo.AssessmentPOJO;
import pro.viksit.com.viksit.role.pojo.CoursePOJO;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Feroz on 19-04-2017.
 */

public class ThreadUtil implements Runnable {

    private int user_id;
    private String url,type,stored_name;
    private SharedPreferences.Editor editor;
    private   HttpUtil httpUtil;

    public ThreadUtil( SharedPreferences.Editor editor, int user_id, String url, String type,String stored_name){
        this.editor = editor;
        this.user_id = user_id;
        this.url = url;
        this.type = type;
        this.stored_name = stored_name;
    }

    @Override
    public void run() {
        httpUtil = new HttpUtil();
        httpUtil.setUrl(url);
        httpUtil.setType("GET");
        String jsonresponse =httpUtil.getStringResponse();
        Gson gson = new Gson();
        switch (type){
            case "Assessment":
                saveAssessment(jsonresponse,gson,editor);
                break;
            case "Leaderboard":
                saveLeaderboard(jsonresponse,gson,editor);
                break;
            case "Course":
                saveCourse(jsonresponse,gson,editor);
                break;
            case "Task":
                saveTask(jsonresponse,gson,editor);
                break;
            case "Assessment_report":
                saveAssessmentReport(jsonresponse,gson,editor);
                break;
        }

    }

    private void saveTask(String jsonresponse, Gson gson, SharedPreferences.Editor editor) {
       int index = httpUtil.getUrl().lastIndexOf("/");
        String taskid = httpUtil.getUrl().substring(index+1,httpUtil.getUrl().length());
        if(!jsonresponse.equalsIgnoreCase("null")){
            editor.putString(stored_name+taskid, jsonresponse);
            editor.apply();
            editor.commit();
        }
    }

    private void saveLeaderboard(String jsonresponse, Gson gson,SharedPreferences.Editor editor) {
        if(!jsonresponse.equalsIgnoreCase("null")){
            editor.putString(stored_name, jsonresponse);
            editor.apply();
            editor.commit();
        }
    }

    private void saveAssessment(String jsonresponse, Gson gson, SharedPreferences.Editor editor) {
        try {
            Type listType = new TypeToken<List<AssessmentPOJO>>() {}.getType();
            ArrayList<AssessmentPOJO> dashboardCards = (ArrayList<AssessmentPOJO>) gson.fromJson(jsonresponse, listType);
            for(AssessmentPOJO assessmentPOJO:dashboardCards){
                System.out.println("XXBBXBXBXBXB -> "+assessmentPOJO.getName());
                if(assessmentPOJO != null){
                    editor.putString(stored_name+assessmentPOJO.getId(), gson.toJson(assessmentPOJO));
                    editor.apply();
                    editor.commit();
                }
            }
        }catch (JsonSyntaxException jse){
            jse.printStackTrace();
        }catch (Exception e){

        }
    }

    private void saveCourse(String jsonresponse, Gson gson,SharedPreferences.Editor editor) {
        if(!jsonresponse.equalsIgnoreCase("null")) {
            Type listType = new TypeToken<List<CoursePOJO>>() {}.getType();
            ArrayList<CoursePOJO> coursePOJOs = (ArrayList<CoursePOJO>)gson.fromJson(jsonresponse, listType);
            for(CoursePOJO coursePOJO:coursePOJOs){
                if(coursePOJO != null){
                    editor.putString(stored_name+coursePOJO.getId(), gson.toJson(coursePOJO));
                    editor.apply();
                    editor.commit();
                }
            }
        }
    }

    private void saveAssessmentReport(String jsonresponse, Gson gson,SharedPreferences.Editor editor) {
        if(!jsonresponse.equalsIgnoreCase("null")){
            editor.putString(stored_name, jsonresponse);
            editor.apply();
            editor.commit();
        }

        System.out.println("json for assessment report:      " + jsonresponse);
    }
}
