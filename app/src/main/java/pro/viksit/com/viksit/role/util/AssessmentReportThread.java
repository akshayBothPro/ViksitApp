package pro.viksit.com.viksit.role.util;

import android.content.Context;
import android.content.SharedPreferences;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Akshay on 20/04/2017.
 */

public class AssessmentReportThread implements Runnable {

    private SharedPreferences sharedPreferences;
    private Context context;
    private int user_id;
    private int assessment_id;

    public AssessmentReportThread(Context context,SharedPreferences sharedPreferences,int user_id,int assessment_id){
        this.sharedPreferences =sharedPreferences;
        this.context = context;
        this.user_id = user_id;
        this.assessment_id = assessment_id;
    }
    @Override
    public void run() {

        HttpUtil httpUtil = new HttpUtil();
        //httpUtil.setUrl("http://192.168.0.140:8080/t2c/" + "assessments/user/441/10157/report");
        httpUtil.setUrl(context.getResources().getString(R.string.serverip) + "assessments/user/" + Integer.toString(user_id)+ "/" + Integer.toString(assessment_id) + "/report");
        httpUtil.setType("GET");
        httpUtil.setConnectionTimeOut(5000);
        httpUtil.setSocketTimeOut(5000);
        String jsonresponse = httpUtil.getStringResponse();
        if(!jsonresponse.equalsIgnoreCase("null")){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(context.getResources().getString(R.string.assessment_report), jsonresponse);
            editor.apply();
            editor.commit();
        }
        System.out.println("AssessmentPOJO Report:    " + jsonresponse );

    }
}
