package pro.viksit.com.viksit.home.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.IstarUserPOJO;
import pro.viksit.com.viksit.home.activity.ChangedPasswordActivity;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Feroz on 13-04-2017.
 */

public class ResetAsync extends AsyncTask<String, Integer, String> {
    private Context context;
    private String otp,jsonresponse,phonenos,password;
    private ProgressBar progress;
    private LinearLayout main_layout;
    private TextView error_text;
    private final Gson gson = new Gson();

    public ResetAsync(Context context, String jsonresponse,String otp,String phonenos, ProgressBar progress, LinearLayout main_layout, TextView error_text,String password){
        this.context = context;
        this.jsonresponse = jsonresponse;
        this.main_layout = main_layout;
        this.error_text = error_text;
        this.progress = progress;
        this.password = password;
        this.otp = otp;
        this.phonenos= phonenos;

    }

    @Override
    protected void onPreExecute() {
        main_layout.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        error_text.setVisibility(View.GONE);
    }
    @Override
    protected String doInBackground(String... params) {
        String httpresponse = "";
        try {
        IstarUserPOJO istarUserPOJO = gson.fromJson(jsonresponse, IstarUserPOJO.class);
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.setUrl(context.getResources().getString(R.string.serverip) + context.getResources().getString(R.string.reseturl).replace("user_id", istarUserPOJO.getIstarUserId() + ""));
        HashMap<String, String> param = new HashMap<>();
        param.put("userId", istarUserPOJO.getIstarUserId()+"");
        param.put("password", password);
        httpUtil.setParam(param);
        httpUtil.setType("GET");
        httpresponse = httpUtil.getStringResponse();
        }  catch (JsonSyntaxException e) {
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
            Intent changepassword = new Intent(context, ChangedPasswordActivity.class);
            changepassword.putExtra("otp", httpresponse);
            changepassword.putExtra("phonenos", phonenos);
            changepassword.putExtra("jsonresponse", jsonresponse);
            context.startActivity(changepassword);


        }else{
            error_text.setText("Network Connectvity issue. Please try again.");

            error_text.setVisibility(View.VISIBLE);

        }
        progress.setVisibility(View.GONE);
        main_layout.setVisibility(View.VISIBLE);

    }
}
