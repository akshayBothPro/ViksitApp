package pro.viksit.com.viksit.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.dashboard.util.LinkedInUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = LoginActivity.class.getSimpleName();
    public static final String PACKAGE = "pro.viksit.com.viksit";
    private TextView welcome;
    private AppCompatEditText email;
    private TextView errorEmail;
    private AppCompatEditText password;
    private TextView errorPassword;
    private Button loginButton;
    private TextView viaSocial;
    private ImageButton linkedInBtn;
    private ImageButton googleBtn;
    private ImageButton fbBtn;
    private Button forgotPassword;
    private Button registerInstead;
    private GradientDrawable drawable;

    private int screenWidth;
    private int screenHeight;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWidthAndHeight();

        welcome = (TextView) findViewById(R.id.tv_login_welcome);
        email = (AppCompatEditText) findViewById(R.id.apet_login_email);
        errorEmail = (TextView) findViewById(R.id.tv_error_email);
        password = (AppCompatEditText) findViewById(R.id.apet_login_password);
        errorPassword = (TextView) findViewById(R.id.tv_error_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        viaSocial = (TextView) findViewById(R.id.tv_via_social);
        googleBtn = (ImageButton) findViewById(R.id.btn_signup_google);
        linkedInBtn = (ImageButton) findViewById(R.id.btn_signup_linkedIn);
        fbBtn = (ImageButton) findViewById(R.id.btn_signup_fb);
        forgotPassword = (Button) findViewById(R.id.btn_forgot_password);
        registerInstead = (Button) findViewById(R.id.btn_register_instead);
        generateHashkey();
        implementActionsListeners();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)

                .build();
    }

    private void implementActionsListeners(){
        loginButton.setOnClickListener(this);
        viaSocial.setText("Log In via social media");
        forgotPassword.setText("Forgot Password?");
        forgotPassword.setOnClickListener(this);
        registerInstead.setText("Not a member? Register instead");
        registerInstead.setOnClickListener(this);
        linkedInBtn.setOnClickListener(this);
        googleBtn.setOnClickListener(this);
       /* email.setMinHeight(screenHeight/5);
        email.setMaxHeight(screenHeight/5);
        password.setMinHeight(screenHeight/5);
        password.setMaxHeight(screenHeight/5);*/


    }

    public void login() {
        Log.d(TAG, "going for Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(getBaseContext(), R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        // TODO: Implement your own authentication logic here.

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onLoginSuccess() {
        //do something
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        //do something
    }

    private boolean validate() {
        boolean valid = true;

        String emailid = email.getText().toString();
        String pasword = password.getText().toString();

        if (emailid.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            errorEmail.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            errorEmail.setVisibility(View.GONE);
        }

        if (pasword.isEmpty() || pasword.length() < 6 || pasword.length() > 10) {
            /*password.setError("Between 6 and 10 alphanumeric characters");*/
            errorPassword.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            errorPassword.setVisibility(View.GONE);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_login) {

            System.out.println("login clicked");
            LISessionManager.getInstance(getApplicationContext()).clearSession();

            //login();
        }else if(id == R.id.btn_register_instead) {
            System.out.println("register instead clicked");
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        } else if(id == R.id.btn_forgot_password) {
            System.out.println("forgot password clicked");
            /*Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);*/
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        }else if(id == R.id.btn_signup_linkedIn){
           new LinkedInUtil().fetchData(getApplicationContext(),this);
        }else if(id == R.id.btn_signup_google){

            signIn();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }








    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result != null) {
                // System.out.println(".. \" result.getStatus().getStatusCode() \"+result.getStatus().getStatusCode() .. " + result.getStatus().getStatusCode());
                    handleSignInResult(result);
            } else {
                //createErrorDialog("Invalid Google Account ");
            }
        }else {
            LISessionManager.getInstance(getApplicationContext())
                    .onActivityResult(this,
                            requestCode, resultCode, data);
        }

    }

    public void generateHashkey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    PACKAGE,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                System.out.println("HAsh is ---------> "+Base64.encodeToString(md.digest(),
                        Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("Name not found", e.getMessage(), e);

        } catch (NoSuchAlgorithmException e) {
            Log.d("Error", e.getMessage(), e);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void handleSignInResult(GoogleSignInResult result) {
            Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            System.out.println("SuccessSuccessSuccessSuccess"+acct.getDisplayName());

            // updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }
}
