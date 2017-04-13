package pro.viksit.com.viksit.home.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.IstarUserPOJO;
import pro.viksit.com.viksit.home.activity.OTPActivity;

/**
 * Created by Feroz on 13-04-2017.
 */

public class OTPAsync extends AsyncTask<String, Integer, String> {
    private Context context;
    private String jsonreponse;
    private String phonenos;
    private final Gson gson = new Gson();
    private ProgressBar progress;
    private LinearLayout main_layout;
    private TextView error_text;

    public OTPAsync(Context context, String jsonreponse, String phonenos, ProgressBar progress, LinearLayout main_layout, TextView error_text) {
        this.context = context;
        this.jsonreponse = jsonreponse;
        this.phonenos = phonenos;
        this.progress = progress;
        this.main_layout = main_layout;
        this.error_text = error_text;
    }

    @Override
    protected void onPreExecute() {

        progress.setVisibility(View.VISIBLE);
        main_layout.setVisibility(View.GONE);
        error_text.setVisibility(View.GONE);
    }

    @Override
    protected String doInBackground(String... params) {
        HttpClient httpclient = new DefaultHttpClient();
        String httpresponse = "";

        try {
            IstarUserPOJO istarUserPOJO = gson.fromJson(jsonreponse, IstarUserPOJO.class);

            HttpGet httppost = new HttpGet(context.getResources().getString(R.string.serverip) + context.getResources().getString(R.string.otpurl).replace("user_id", istarUserPOJO.getIstarUserId() + "") + "?mobile=" + phonenos);
            System.out.println("calling otp service url -> " + context.getResources().getString(R.string.serverip) + context.getResources().getString(R.string.otpurl).replace("user_id", istarUserPOJO.getIstarUserId() + "") + "?mobile=" + phonenos);
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();
            httpresponse = EntityUtils.toString(httpEntity);

            System.out.println("jsonresponse " + httpresponse);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "null";
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            return "null";
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
        return httpresponse;
    }


    @Override
    protected void onPostExecute(String httpresponse) {


        if (httpresponse != null && !httpresponse.equalsIgnoreCase("null")
                && !httpresponse.equalsIgnoreCase("") && !httpresponse.contains("HTTP Status")
                ) {
            Intent otpintent = new Intent(context, OTPActivity.class);
            otpintent.putExtra("otp", httpresponse);
            otpintent.putExtra("phonenos", phonenos);
            otpintent.putExtra("jsonresponse", jsonreponse);
            context.startActivity(otpintent);
        } else {
            error_text.setText("Network Connectvity issue. Please try again.");
            error_text.setVisibility(View.VISIBLE);
        }
        progress.setVisibility(View.GONE);
        main_layout.setVisibility(View.VISIBLE);

    }
}
