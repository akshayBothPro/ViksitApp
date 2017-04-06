package pro.viksit.com.viksit.dashboard.util;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import pro.viksit.com.viksit.R;

/**
 * Created by Feroz on 06-04-2017.
 */

public class FacebookUtil {



    public FacebookCallback<LoginResult> getFaceBookCallBack(final Context context,final MaterialDialog dialog){
         FacebookCallback<LoginResult> callback =  new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            if(response.getJSONObject() != null){
                                HashMap<String, String> params = new HashMap<String, String>();
                                params.put(context.getResources().getString(R.string.socialmedia), "FACEBOOK");
                                params.put(context.getResources().getString(R.string.email), response.getJSONObject().get("email").toString());
                                params.put(context.getResources().getString(R.string.username), response.getJSONObject().get("first_name").toString()+" "+response.getJSONObject().get("last_name").toString());
                                params.put(context.getResources().getString(R.string.profilepic), "https://graph.facebook.com/"+response.getJSONObject().get("id").toString()+"/picture?type=large");
                                new LoginAsync(params,context).execute(context.getResources().getString(R.string.socialloginurl));

                            }else{
                                dialog.show();
                            }

                            Log.i("LoginActivity", "https://graph.facebook.com/"+response.getJSONObject().get("id").toString()+"/picture?type=large");
                        } catch (JSONException e) {
                            dialog.show();
                            e.printStackTrace();
                        }
                        // Get facebook data from login response.getJSONObject().get("id")
                        //profile iamge https://graph.facebook.com/289692161469735/picture?type=large
                    }
                });
                Bundle parameters = new Bundle();

                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
                Toast.makeText(context, "Logging in...", Toast.LENGTH_SHORT).show();    }
            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
                dialog.show();
            }
        };
        return callback;
    }

}
