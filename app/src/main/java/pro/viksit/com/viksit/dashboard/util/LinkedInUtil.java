package pro.viksit.com.viksit.dashboard.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import org.json.JSONArray;
import org.json.JSONObject;

import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.home.activity.LoginActivity;

/**
 * Created by Feroz on 05-04-2017.
 */

public class LinkedInUtil {
    private static final String host = "api.linkedin.com";
    private static final String url = "https://" + host
            + "/v1/people/~:" +
            "(email-address,formatted-name,phone-numbers,picture-urls::(original))";

    public void fetchData(final Context context,LoginActivity activity){


        LISessionManager.getInstance(context)
                .init(activity, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        APIHelper apiHelper = APIHelper.getInstance(context);
                        apiHelper.getRequest(context, url, new ApiListener() {
                            @Override
                            public void onApiSuccess(ApiResponse result) {
                                try {
                                    System.out.println("email --> "+result.getResponseDataAsJson().get("emailAddress").toString());
                                    System.out.println("formattedName --> "+result.getResponseDataAsJson().get("formattedName").toString());
                                    System.out.println("pictureUrl --> "+((JSONArray)((JSONObject)result.getResponseDataAsJson().get("pictureUrls")).get("values")).get(0));
                                    Intent i = new Intent(context, DashboardActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(i);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onApiError(LIApiError LIApiError) {
                            }
                        });




                    }

                    @Override
                    public void onAuthError(LIAuthError error) {
                        Toast.makeText(context, "failed "
                                        + error.toString(),
                                Toast.LENGTH_LONG).show();
                        System.out.println(" error -->"+error.toString());
                    }
                }, true);
    }
    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS);
    }


}
