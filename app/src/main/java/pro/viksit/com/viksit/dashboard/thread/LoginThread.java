package pro.viksit.com.viksit.dashboard.thread;

import android.content.Context;
import android.content.SharedPreferences;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.HashMap;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.async.LoginAsync;

/**
 * Created by Feroz on 07-04-2017.
 */

public class LoginThread implements Runnable {
    private Context context;
    private HashMap<String,String> params;
    private MaterialDialog dialog,progressdialog;
    private SharedPreferences sharedpreferences;

    public LoginThread(Context context,HashMap<String,String> params,MaterialDialog dialog,MaterialDialog progressdialog,SharedPreferences sharedpreferences){
        this.context = context;
        this.params = params;
        this.dialog = dialog;
        this.progressdialog = progressdialog;
        this.sharedpreferences = sharedpreferences;
    }


    @Override
    public void run() {
        new LoginAsync(params,context,dialog,progressdialog,sharedpreferences).execute(context.getResources().getString(R.string.socialloginurl));

    }
}
