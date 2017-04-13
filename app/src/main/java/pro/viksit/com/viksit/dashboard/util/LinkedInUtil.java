package pro.viksit.com.viksit.dashboard.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.afollestad.materialdialogs.MaterialDialog;
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

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.async.LoginAsync;
import pro.viksit.com.viksit.dashboard.thread.LoginThread;
import pro.viksit.com.viksit.dashboard.thread.ProfileImageThread;

/**
 * Created by Feroz on 05-04-2017.
 */

public class LinkedInUtil {
    private static final String host = "api.linkedin.com";
    private static final String url = "https://" + host
            + "/v1/people/~:" +
            "(email-address,formatted-name,phone-numbers,picture-urls::(original))";

    public void fetchData(final Context context, Activity activity, final MaterialDialog dialog, final MaterialDialog progressdialog,final SharedPreferences sharedpreferences){


        LISessionManager.getInstance(context)
                .init(activity, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        APIHelper apiHelper = APIHelper.getInstance(context);
                        apiHelper.getRequest(context, url, new ApiListener() {
                            @Override
                            public void onApiSuccess(ApiResponse result) {
                                try {
                                    if(result.getResponseDataAsJson() != null) {
                                        HashMap<String, String> params = new HashMap<String, String>();
                                        params.put(context.getResources().getString(R.string.socialmedia), "LINKEDIN");
                                        params.put(context.getResources().getString(R.string.email), result.getResponseDataAsJson().get("emailAddress").toString());
                                        params.put(context.getResources().getString(R.string.username), result.getResponseDataAsJson().get("formattedName").toString());
                                        if ((JSONObject) result.getResponseDataAsJson().get("pictureUrls") != null && ((JSONArray) ((JSONObject) result.getResponseDataAsJson().get("pictureUrls")).get("values")).length() > 0) {
                                            params.put(context.getResources().getString(R.string.profilepic), ((JSONArray) ((JSONObject) result.getResponseDataAsJson().get("pictureUrls")).get("values")).get(0).toString());
                                            ExecutorService executor = Executors.newFixedThreadPool(2);
                                            Runnable worker = new ProfileImageThread(context,((JSONArray) ((JSONObject) result.getResponseDataAsJson().get("pictureUrls")).get("values")).get(0).toString());
                                            executor.execute(worker);
                                            Runnable worker1 = new LoginThread(context,params,dialog,progressdialog,sharedpreferences);
                                            executor.execute(worker1);

                                            executor.shutdown();
                                            while (!executor.isTerminated()) {   }
                                        }else{
                                            new LoginAsync(params,context,dialog,progressdialog,sharedpreferences).execute(context.getResources().getString(R.string.socialloginurl));

                                        }


                                        System.out.println("Finished all threads");

                                        //
                                    }else{
                                        System.out.println("There is an error while signing in with Linked in");
                                        dialog.show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onApiError(LIApiError LIApiError) {
                                dialog.show();

                            }
                        });




                    }

                    @Override
                    public void onAuthError(LIAuthError error) {

                        System.out.println(" error -->"+error.toString());
                        dialog.show();

                    }
                }, true);
    }
    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS);
    }


}
