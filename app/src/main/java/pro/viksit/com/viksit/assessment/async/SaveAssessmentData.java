package pro.viksit.com.viksit.assessment.async;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.database.AssessmentDataHandler;
import pro.viksit.com.viksit.assessment.fragment.QuestionResultFragment;
import pro.viksit.com.viksit.assessment.pojo.AssessmentPOJO;
import pro.viksit.com.viksit.assessment.pojo.AssessmentResultPojo;
import pro.viksit.com.viksit.assessment.pojo.QuestionPOJO;
import pro.viksit.com.viksit.assessment.pojo.QuestionResult;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Feroz on 17-04-2017.
 */

public class SaveAssessmentData extends AsyncTask<String, Integer, String> {
    private Context context;
    private ProgressBar progressBar;
    private AssessmentPOJO assessmentPOJO;
    private AssessmentResultPojo assessmentResultPojo;
    private final Gson gson = new Gson();

    public SaveAssessmentData(Context context, ProgressBar progressBar, AssessmentPOJO assessmentPOJO, AssessmentResultPojo assessmentResultPojo){
        this.context = context;
        this.progressBar = progressBar;
        this.assessmentPOJO = assessmentPOJO;
        this.assessmentResultPojo = assessmentResultPojo;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... params) {

        for(QuestionPOJO questionPOJO:assessmentPOJO.getQuestions() ){
            if(!assessmentResultPojo.getOptions().containsKey(questionPOJO.getId())){
                assessmentResultPojo.getOptions().put(questionPOJO.getId(),new QuestionResult(questionPOJO.getId(),null,null));
            }
        }
        if(isNetworkConnected()){
            try{
                HttpUtil httpUtil = new HttpUtil();
                httpUtil.setUrl(context.getResources().getString(R.string.resourceserverip)+"/AndroidTest/JsonServlet");
                httpUtil.setType("PUT");
                HashMap<Integer, QuestionResult> map = assessmentResultPojo.getOptions();
                ArrayList<QuestionResult> list = new ArrayList<>();
                for(Integer key:map.keySet()){
                    list.add(map.get(key));
                }
                System.out.println(gson.toJson(list));

                httpUtil.setPostrequest(gson.toJson(list).toString());
                String jsonresponse = httpUtil.getStringResponse();

            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                saveAssessment(context);
            }

        }else{
        saveAssessment(context);
         }
        return null;
    }



    @Override
    protected void onPostExecute(String jsonresponse) {
        progressBar.setVisibility(View.GONE);

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void saveAssessment(Context context){
        AssessmentDataHandler assessmentDataHandler = new AssessmentDataHandler(context);
        assessmentDataHandler.saveContent(assessmentResultPojo.getAssessment_id()+"",gson.toJson(assessmentResultPojo));

    }

    }
