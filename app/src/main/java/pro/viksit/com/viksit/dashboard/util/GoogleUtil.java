package pro.viksit.com.viksit.dashboard.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Feroz on 06-04-2017.
 */

public class GoogleUtil {
    public void signIn(GoogleApiClient mGoogleApiClient, int RC_SIGN_IN, Activity context) {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        context.startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    public void handleSignInResult(GoogleSignInResult result) {
        Log.d("LOGIN_GOOGLE", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            System.out.println("getDisplayName "+acct.getDisplayName());
            System.out.println("getEmail "+acct.getEmail());
            System.out.println("getPhotoUrl "+acct.getPhotoUrl());
        } else {
        }
    }
}
