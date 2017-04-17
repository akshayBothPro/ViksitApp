package pro.viksit.com.viksit.assessment.async;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
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

import me.itangqi.waveloadingview.WaveLoadingView;
import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.LockableViewPager;
import pro.viksit.com.viksit.assessment.activity.AssessmentActivity;
import pro.viksit.com.viksit.assessment.adapter.AssessmentAdapter;
import pro.viksit.com.viksit.assessment.adapter.AssessmentTotalTimer;
import pro.viksit.com.viksit.assessment.adapter.QuestionsRecyclerViewAdapter;
import pro.viksit.com.viksit.assessment.adapter.Questiontimer;
import pro.viksit.com.viksit.assessment.pojo.AssessmentPOJO;
import pro.viksit.com.viksit.role.adapter.RoleVerticalRecyclerViewAdapter;

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
        HttpClient httpclient = new DefaultHttpClient();
        System.out.println("4567 ->"+context.getResources().getString(R.string.serverip) + (context.getResources().getString(R.string.assessmenturl)+"/"+user_id+"/"+assessment_id));
        HttpGet httppost = new HttpGet(context.getResources().getString(R.string.serverip) + (context.getResources().getString(R.string.assessmenturl)+"/"+user_id+"/"+assessment_id));
        int timeoutConnection = 20000;
        final HttpParams httpParameters = httpclient.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParameters, 20000);

        String jsonresponse = "";
        try {
            // Add your data

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();
            jsonresponse = EntityUtils.toString(httpEntity);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "null";
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            return "null";
        } catch (JsonSyntaxException jse) {
            jse.printStackTrace();
            return "null";
        }catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
        return jsonresponse;
    }
}
