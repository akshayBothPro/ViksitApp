package pro.viksit.com.viksit.assessment.async;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import pro.viksit.com.viksit.assessment.pojo.AssessmentPOJO;
import pro.viksit.com.viksit.assessment.pojo.AssessmentResultPojo;
import pro.viksit.com.viksit.assessment.pojo.QuestionPOJO;
import pro.viksit.com.viksit.assessment.pojo.QuestionResult;

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
                assessmentResultPojo.getOptions().put(questionPOJO.getId(),new QuestionResult(questionPOJO.getId(),-1,null));
            }
        }
        System.out.println(gson.toJson(assessmentResultPojo));
        if(isNetworkConnected()){

        }else{

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
    }
