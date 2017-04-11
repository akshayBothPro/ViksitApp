package pro.viksit.com.viksit.dashboard.async;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.dashboard.adapter.CardAdapter.CarouselPagerAdapter;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.dashboard.pojo.StudentProfile;

/**
 * Created by Feroz on 10-04-2017.
 */

public class DashboardCardAsync extends AsyncTask<String, Integer, String> {

    private Context context;
    private FragmentManager fm;
    private Gson gson = new Gson();
    private int userid;
    private SharedPreferences sharedPreferences;
    public int loop;
    public int lastposition;
    public ImageView[] dots;
    private LinearLayout pager_indicator;
    private DashboardActivity activity;
    private ViewPager pager;
    private ProgressBar progressBar;
    private RelativeLayout error_layout;
    public DashboardCardAsync(Context context,
                              FragmentManager fm, int userid,
                              SharedPreferences sharedPreferences,
                              LinearLayout pager_indicator, DashboardActivity activity,ViewPager pager,int loop,ProgressBar progressBar,RelativeLayout error_layout){
    this.context = context;
        this.fm = fm;
        this.userid = userid;
        this.sharedPreferences = sharedPreferences;
        this.pager_indicator = pager_indicator;
        this.activity = activity;
        this.pager =pager;
        this.loop = loop;
        this.progressBar = progressBar;
        this.error_layout = error_layout;
    }

    @Override
    protected String doInBackground(String... params) {
        return  postData(context,fm,sharedPreferences);
    }



    @Override
    protected  void onPreExecute()
    {
        progressBar.setVisibility(View.VISIBLE);
        error_layout.setVisibility(View.GONE);

    }

    @Override
    protected void onPostExecute(String jsonresponse) {


        if(jsonresponse != null && !jsonresponse.equalsIgnoreCase("null") && !jsonresponse.equalsIgnoreCase("")) {
            System.out.println("jsonresponse " + jsonresponse);
            Type listType = new TypeToken<List<DashboardCard>>() {
            }.getType();
            ArrayList<DashboardCard> dashboardCards = (ArrayList<DashboardCard>) gson.fromJson(jsonresponse, listType);


            if (dashboardCards.size() < 7) {
                loop = dashboardCards.size();
            } else {
                loop = 7;
                lastposition = (7 * (dashboardCards.size() / 7));
            }
            dots = new ImageView[dashboardCards.size()];
            for (int i = 0; i < loop; i++) {
                dots[i] = new ImageView(context);
                dots[i].setImageDrawable(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(4, 0, 4, 0);
                pager_indicator.addView(dots[i], params);
            }
            dots[0].setImageDrawable(context.getResources().getDrawable(R.drawable.selecteditem_dot));
            CarouselPagerAdapter carouselPagerAdapter = new CarouselPagerAdapter(activity, fm, dashboardCards, loop);
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int pageMargin = ((metrics.widthPixels / 12));
            System.out.println("pageMargin " + pageMargin);
            pager.setClipToPadding(false);
            pager.setPadding(pageMargin, pageMargin / 2, pageMargin, 0);
            pager.setAdapter(carouselPagerAdapter);
            carouselPagerAdapter.notifyDataSetChanged();
            pager.addOnPageChangeListener(carouselPagerAdapter);
            pager.setCurrentItem(1);
            pager.setOffscreenPageLimit(3);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(context.getResources().getString(R.string.dashboardcards), jsonresponse);
            editor.apply();
            editor.commit();
        }else{
            error_layout.setVisibility(View.VISIBLE);
        }
        progressBar.setVisibility(View.GONE);

    }


    private String postData(Context context,
                            FragmentManager fm,SharedPreferences sharedPreferences) {
        HttpClient httpclient = new DefaultHttpClient();
        System.out.println(context.getResources().getString(R.string.serverip)+(context.getResources().getString(R.string.dashboardcardurl).replaceAll("user_id",463+"")));
        HttpGet httppost = new HttpGet(context.getResources().getString(R.string.serverip)+(context.getResources().getString(R.string.dashboardcardurl).replaceAll("user_id",463+"")));
        int timeoutConnection = 6000;
        final HttpParams httpParameters = httpclient.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpConnectionParams.setSoTimeout        (httpParameters, 10000);

        String jsonresponse= "";
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
        }catch (Exception e){
            e.printStackTrace();
            return "null";
        }
        return  jsonresponse;
    }

}
