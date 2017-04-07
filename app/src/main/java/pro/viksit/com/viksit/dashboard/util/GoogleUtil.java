package pro.viksit.com.viksit.dashboard.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pro.viksit.com.viksit.R;

/**
 * Created by Feroz on 06-04-2017.
 */

public class GoogleUtil {
    public void signIn(GoogleApiClient mGoogleApiClient, int RC_SIGN_IN, Activity context) {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        context.startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    public void handleSignInResult(GoogleSignInResult result,Context context,MaterialDialog dialog, final MaterialDialog progressdialog,final SharedPreferences sharedpreferences) {
        Log.d("LOGIN_GOOGLE", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            System.out.println("getDisplayName "+acct.getDisplayName());
            System.out.println("getEmail "+acct.getEmail());
            System.out.println("getPhotoUrl "+acct.getPhotoUrl());

            if(acct != null ){
                HashMap<String, String> params = new HashMap<String, String>();
                params.put(context.getResources().getString(R.string.socialmedia), "GOOGLE");
                params.put(context.getResources().getString(R.string.email), acct.getEmail());
                params.put(context.getResources().getString(R.string.username), acct.getDisplayName());
                if (acct.getPhotoUrl() != null) {
                    params.put(context.getResources().getString(R.string.profilepic), acct.getPhotoUrl().toString());
                    ExecutorService executor = Executors.newFixedThreadPool(2);
                    Runnable worker = new ProfileImageThread(context,acct.getPhotoUrl().toString());
                    executor.execute(worker);
                    Runnable worker1 = new LoginThread(context,params,dialog,progressdialog,sharedpreferences);
                    executor.execute(worker1);
                    executor.shutdown();
                    while (!executor.isTerminated()) {   }
                }else {
                    new LoginAsync(params, context, dialog, progressdialog, sharedpreferences).execute(context.getResources().getString(R.string.socialloginurl));
                }
            }else{
                dialog.show();
            }



        } else {
            dialog.show();


        }
    }
}
