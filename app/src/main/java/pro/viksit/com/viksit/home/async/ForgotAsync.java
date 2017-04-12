package pro.viksit.com.viksit.home.async;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.StudentProfile;

/**
 * Created by Feroz on 12-04-2017.
 */

public class ForgotAsync extends AsyncTask<String, Integer, String> {

    private Context context;
    private String mobile;
    private TextView tv_error_email;
    public ForgotAsync(Context context,String mobile,TextView tv_error_email){
        this.context = context;
        this.mobile = mobile;
        this.tv_error_email = tv_error_email;
    }

    @Override
    protected void onPreExecute() {
        tv_error_email.setVisibility(View.GONE);

    }

    @Override
    protected String doInBackground(String... params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httppost = new HttpGet(context.getResources().getString(R.string.serverip) + context.getResources().getString(R.string.forgotpassword)+"?mobile="+mobile);
        String jsonresponse = "";
        try {


            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();
            jsonresponse = EntityUtils.toString(httpEntity);

            System.out.println("jsonresponse " + jsonresponse);

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
        return jsonresponse;
    }


    @Override
    protected void onPostExecute(String jsonresponse) {
        if (jsonresponse != null && !jsonresponse.equalsIgnoreCase("null")
                && !jsonresponse.equalsIgnoreCase("") && !jsonresponse.contains("HTTP Status")
                ) {

        }else{
            tv_error_email.setText("Oops. No account registered to this number");
            tv_error_email.setVisibility(View.VISIBLE);
        }

    }
}
