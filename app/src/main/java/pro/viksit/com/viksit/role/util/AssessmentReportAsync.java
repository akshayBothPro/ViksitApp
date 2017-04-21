package pro.viksit.com.viksit.role.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Akshay on 20/04/2017.
 */

public class AssessmentReportAsync extends AsyncTask<String, Integer, String> {

    private static final String TAG = "AssessmentReportAsync";
    private Context context;

    public AssessmentReportAsync(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        HttpUtil httpUtil = new HttpUtil();
        httpUtil.setUrl(context.getResources().getString(R.string.serverip) + "assessments/user/441/10157/report");
        httpUtil.setType("GET");
        httpUtil.setConnectionTimeOut(100000);
        httpUtil.setSocketTimeOut(100000);
        String jsonresponse = httpUtil.getStringResponse();
       /* Gson gson = new Gson();*/

        if(!jsonresponse.equalsIgnoreCase("null")){
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(context.getResources().getString(R.string.assessment_report), jsonresponse);
            editor.apply();
            editor.commit();
        }
        Log.v(TAG,jsonresponse);
        System.out.println("this is url " + httpUtil.getUrl());

        return jsonresponse;
    }

}
