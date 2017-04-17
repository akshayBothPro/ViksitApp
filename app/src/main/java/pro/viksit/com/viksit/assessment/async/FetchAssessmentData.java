package pro.viksit.com.viksit.assessment.async;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Feroz on 14-04-2017.
 */

public class FetchAssessmentData extends AsyncTask<String, Integer, String> {
    private Context context;
    private ProgressBar progress;
    private int user_id,assessment_id;

    public FetchAssessmentData(Context context, ProgressBar progress, int user_id, int assessment_id

                                ){
        this.context = context;
        this.progress = progress;
        this.user_id = user_id;
        this.assessment_id = assessment_id;

    }

    @Override
    protected void onPreExecute() {
        progress.setVisibility(View.VISIBLE);

    }
    @Override
    protected String doInBackground(String... params) {

        return postData();
    }



    @Override
    protected void onPostExecute(String jsonresponse) {

        progress.setVisibility(View.GONE);

    }

    private String postData() {
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.setType("GET");
        httpUtil.setUrl(context.getResources().getString(R.string.serverip) + (context.getResources().getString(R.string.assessmenturl)+"/"+user_id+"/"+assessment_id));
        String jsonresponse = httpUtil.getStringResponse();
        return jsonresponse;
    }
}
