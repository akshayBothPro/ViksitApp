package pro.viksit.com.viksit.home.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Feroz on 20-04-2017.
 */

public class SplashScreenTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    private SplashScreenTaskCallback listener = null;
    private SharedPreferences.Editor editor;
    private int user_id;
    public SplashScreenTask(Context context, SharedPreferences.Editor editor,int user_id) {
        this.context = context;
        this.editor = editor;
        this.user_id=user_id;
    }

    @Override
    protected Void doInBackground (Void... params) {
        HttpUtil httpUtil = new HttpUtil();

        httpUtil.setUrl(context.getResources().getString(R.string.serverip) + (context.getResources().getString(R.string.dashboardcardurl).replaceAll("user_id", user_id + "")));
        httpUtil.setType("GET");
        editor.putString(context.getResources().getString(R.string.dashboardcards), httpUtil.getStringResponse());
        editor.apply();
        editor.commit();
        // Do your tasks


        return null;
    }

    @Override
    public void onPreExecute () {
    }

    @Override
    public void onPostExecute (Void v) {
        if (listener != null) {
            listener.OnSplashScreenTaskCompleted ();
        }
    }

    public void setListener (SplashScreenTaskCallback listener) {
        this.listener = listener;
    }


    public interface SplashScreenTaskCallback {
        void OnSplashScreenTaskCompleted ();
    }
}