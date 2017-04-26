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
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dummy.pojo.AssessmentPOJO;
import pro.viksit.com.viksit.dummy.pojo.AssessmentReportPOJO;
import pro.viksit.com.viksit.dummy.pojo.ComplexObject;
import pro.viksit.com.viksit.dummy.pojo.SkillReportPOJO;
import pro.viksit.com.viksit.dummy.pojo.StudentProfile;
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

        Gson gson = new Gson();
        ComplexObject complexObject = (ComplexObject) gson.fromJson(jsonresponse,ComplexObject.class);

        gson = new Gson();
        setSharedpreferences("STUDENTPROFILE",gson.toJson(complexObject.getStudentProfile()));


        //SkillReportPOJO
        gson = new Gson();
        for(SkillReportPOJO skillReportPOJO : complexObject.getSkills()){
            System.out.println("skillReportPOJO -> "+skillReportPOJO.getName());
            if(skillReportPOJO != null){
                editor.putString("SKILLREPORT_"+skillReportPOJO.getId(), gson.toJson(skillReportPOJO));
                editor.apply();
                editor.commit();
            }
        }

        //TaskSummaryPOJO
        gson = new Gson();
        for(TaskSummaryPOJO taskSummaryPOJO : complexObject.getTasks()){
            if(taskSummaryPOJO != null){
                editor.putString(getResources().getString(R.string.task_store) + taskSummaryPOJO.getId(), gson.toJson(taskSummaryPOJO));
                editor.apply();
                editor.commit();
            }
        }

        //AssessmentPOJO
        gson = new Gson();
        for(AssessmentPOJO assessmentPOJO : complexObject.getAssessments()){
            if(assessmentPOJO != null){
                editor.putString(getResources().getString(R.string.assessment) + assessmentPOJO.getId(), gson.toJson(assessmentPOJO));
                editor.apply();
                editor.commit();
            }
        }

        //AssessmentReportPOJO
        gson = new Gson();
        for(AssessmentReportPOJO assessmentReportPOJO : complexObject.getAssessmentReports()){
            if(assessmentReportPOJO != null){
                editor.putString(getResources().getString(R.string.assessment) + assessmentReportPOJO.getId(), gson.toJson(assessmentReportPOJO));
                editor.apply();
                editor.commit();
            }
        }


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
