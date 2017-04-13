package pro.viksit.com.viksit.home.async;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import pro.viksit.com.viksit.R;

/**
 * Created by Feroz on 12-04-2017.
 */

public class ForgotAsync extends AsyncTask<String, Integer, String> {

    private Context context;
    private String mobile;
    private TextView tv_error_email;
    private String phonenos;
    private ProgressBar progress;
    private LinearLayout main_layout;
    public ForgotAsync(Context context, String mobile, TextView tv_error_email, String phonenos, ProgressBar progress, LinearLayout main_layout){
        this.context = context;
        this.mobile = mobile;
        this.tv_error_email = tv_error_email;
        this.phonenos = phonenos;
        this.progress = progress;
        this.main_layout = main_layout;
    }

    @Override
    protected void onPreExecute() {
        tv_error_email.setVisibility(View.GONE);
        main_layout.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
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
            new OTPAsync(context,jsonresponse,phonenos,progress,main_layout,tv_error_email).execute();

        }else{
            tv_error_email.setText("Oops. No account registered to this number");
            tv_error_email.setVisibility(View.VISIBLE);
        }
        progress.setVisibility(View.GONE);
        main_layout.setVisibility(View.VISIBLE);

    }
}
