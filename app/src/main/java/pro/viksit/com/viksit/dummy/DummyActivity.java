package pro.viksit.com.viksit.dummy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dummy.pojo.AssessmentPOJO;
import pro.viksit.com.viksit.dummy.pojo.AssessmentReportPOJO;
import pro.viksit.com.viksit.dummy.pojo.AssessmentResponsePOJO;
import pro.viksit.com.viksit.dummy.pojo.ComplexObject;
import pro.viksit.com.viksit.dummy.pojo.CourseRankPOJO;
import pro.viksit.com.viksit.dummy.pojo.DailyTaskPOJO;
import pro.viksit.com.viksit.dummy.pojo.NotificationPOJO;
import pro.viksit.com.viksit.dummy.pojo.SkillReportPOJO;
import pro.viksit.com.viksit.dummy.pojo.TaskSummaryPOJO;

public class DummyActivity extends AppCompatActivity {


    private int user_id;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        editor  = sharedpreferences.edit();
        String jsonresponse = getDatafromAssetsFile("19.txt");
        System.out.println("<--->          " + jsonresponse);

        Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();//"2016-09-13 12:15:00",
        ComplexObject complexObject = new ComplexObject();
        complexObject = gson.fromJson(jsonresponse, ComplexObject.class);

        gson = new Gson();
        setSharedpreferences("STUDENTPROFILE",gson.toJson(complexObject.getStudentProfile()));

        //SkillReportPOJO
        gson = new Gson();
        for(SkillReportPOJO skillReportPOJO : complexObject.getSkills()){
            System.out.println("skillReportPOJO -> "+gson.toJson(skillReportPOJO));
            if(skillReportPOJO != null){
                setSharedpreferences("SKILLREPORT_" /*+ Integer.toString(skillReportPOJO.getId())*/, gson.toJson(skillReportPOJO));
            }
        }

        System.out.println("\n\n");
        //TaskSummaryPOJO
        gson = new Gson();
        for(TaskSummaryPOJO taskSummaryPOJO : complexObject.getTasks()){
            System.out.println("TaskSummaryPOJO -> "+gson.toJson(taskSummaryPOJO));
            if(taskSummaryPOJO != null){
                setSharedpreferences(getResources().getString(R.string.task_store) + Integer.toString(taskSummaryPOJO.getId()), gson.toJson(taskSummaryPOJO));
            }
        }
        System.out.println("\n\n");

        //AssessmentPOJO
        gson = new Gson();
        for(AssessmentPOJO assessmentPOJO : complexObject.getAssessments()){
            System.out.println("AssessmentPOJO -> "+gson.toJson(assessmentPOJO));
            if(assessmentPOJO != null){
                setSharedpreferences(getResources().getString(R.string.assessment) + Integer.toString(assessmentPOJO.getId()), gson.toJson(assessmentPOJO));
            }
        }
        System.out.println("\n\n");

        //AssessmentReportPOJO
        gson = new Gson();
        for(AssessmentReportPOJO assessmentReportPOJO : complexObject.getAssessmentReports()){
            System.out.println("AssessmentReportPOJO -> "+gson.toJson(assessmentReportPOJO));
            if(assessmentReportPOJO != null){
                setSharedpreferences(getResources().getString(R.string.assessmentreport) + Integer.toString(assessmentReportPOJO.getId()), gson.toJson(assessmentReportPOJO));
            }
        }
        System.out.println("\n\n");

        //AssessmentResponsePOJO
        gson = new Gson();
        for(AssessmentResponsePOJO assessmentResponsePOJO : complexObject.getAssessmentResponses()){
            System.out.println("AssessmentResponsePOJO -> "+gson.toJson(assessmentResponsePOJO));
            if(assessmentResponsePOJO != null){
               /* editor.putString(getResources().getString(R.string.assessmentresponse) + Integer.toString(assessmentResponsePOJO.getId()), gson.toJson(assessmentResponsePOJO));
                editor.apply();
                editor.commit();*/
                setSharedpreferences(getResources().getString(R.string.assessmentresponse) + Integer.toString(assessmentResponsePOJO.getId()), gson.toJson(assessmentResponsePOJO));
            }
        }
        System.out.println("\n\n");

        //CourseRankPOJO(leaderboard)
        gson = new Gson();
        for(CourseRankPOJO courseRankPOJO : complexObject.getLeaderboards()){
            System.out.println("CourseRankPOJO(leaderboard) -> "+gson.toJson(courseRankPOJO));
            if(courseRankPOJO != null){
                setSharedpreferences(getResources().getString(R.string.leaderboardstore) + Integer.toString(courseRankPOJO.getId()), gson.toJson(courseRankPOJO));
            }
        }
        System.out.println("\n\n");

        //DailyTaskPOJO(events)
        gson = new Gson();
        for(DailyTaskPOJO dailyTaskPOJO : complexObject.getEvents()){
            System.out.println("DailyTaskPOJO(events) -> "+gson.toJson(dailyTaskPOJO));
            if(dailyTaskPOJO != null){
                setSharedpreferences(getResources().getString(R.string.eventstore) + Integer.toString(dailyTaskPOJO.getId()), gson.toJson(dailyTaskPOJO));
            }
        }
        System.out.println("\n\n");

        /*//NotificationPOJO(notifications)
        gson = new Gson();
        for(NotificationPOJO notificationPOJO : complexObject.getNotifications()){
            System.out.println("NotificationPOJO(notifications) -> "+gson.toJson(notificationPOJO));
            if(notificationPOJO != null){
                setSharedpreferences(getResources().getString(R.string.notificationstore)*//* + Integer.toString(notificationPOJO.getId())*//*, gson.toJson(notificationPOJO));
            }
        }*/

    }

    public void setSharedpreferences(String stored_name, String jsonresponse){
        editor.putString(stored_name, jsonresponse);
        editor.apply();
        editor.commit();
    }

    public String getDatafromAssetsFile(String filename){

        StringBuilder buf=new StringBuilder();
        InputStream json= null;
        try {
            json = getAssets().open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in= null;
        try {
            in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str;

        try {
            while ((str=in.readLine()) != null) {
                buf.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("<--->          " + buf);

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
      return buf.toString();
    }
}
