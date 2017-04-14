package pro.viksit.com.viksit.home.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.linkedin.platform.LISessionManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import com.facebook.CallbackManager;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.dashboard.pojo.IstarUserPOJO;
import pro.viksit.com.viksit.dashboard.util.FacebookUtil;
import pro.viksit.com.viksit.dashboard.util.GoogleUtil;
import pro.viksit.com.viksit.dashboard.util.LinkedInUtil;
import pro.viksit.com.viksit.dashboard.async.LoginAsync;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
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
    private ImageButton googleBtn, fb;
    private LoginButton fbBtn;
    private Button forgotPassword;
    private Button registerInstead, ok;
    private int screenWidth;
    private int screenHeight;
    private static final int RC_SIGN_IN = 9001;
    private static final int FB_SIGN_IN = 64206;
    private final GoogleUtil googleUtil = new GoogleUtil();
    private GoogleApiClient mGoogleApiClient;
    CallbackManager callbackManager;
    private MaterialDialog dialog;
    private MaterialDialog progressdialog;
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        getWidthAndHeight();
        dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_error, false)
                .build();
        progressdialog = new MaterialDialog.Builder(this)
                .content("Please wait ..")
                .progress(true, 0)
                .build();
        callbackManager = CallbackManager.Factory.create();
        welcome = (TextView) findViewById(R.id.tv_login_welcome);
        email = (AppCompatEditText) findViewById(R.id.apet_login_email);
        password = (AppCompatEditText) findViewById(R.id.apet_login_password);
        errorPassword = (TextView) findViewById(R.id.tv_error_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        viaSocial = (TextView) findViewById(R.id.tv_via_social);
        googleBtn = (ImageButton) findViewById(R.id.btn_signup_google);
        linkedInBtn = (ImageButton) findViewById(R.id.btn_signup_linkedIn);
        fbBtn = (LoginButton) findViewById(R.id.btn_signup_fb);
        fb = (ImageButton) findViewById(R.id.fb);
        forgotPassword = (Button) findViewById(R.id.btn_forgot_password);
        registerInstead = (Button) findViewById(R.id.btn_register_instead);
        ok = (Button) dialog.getCustomView().findViewById(R.id.ok);

        generateHashkey();
        implementActionsListeners();
        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                errorPassword.setVisibility(View.GONE);

            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                errorPassword.setVisibility(View.GONE);

            }
        });


        if(getIntent() != null ){
            if(getIntent().getStringExtra("jsonresponse") != null){
               String jsonresponse = getIntent().getStringExtra("jsonresponse");
                Gson gson = new Gson();
                IstarUserPOJO istarUserPOJO = gson.fromJson(jsonresponse, IstarUserPOJO.class);
                email.setText(istarUserPOJO.getEmail());
            }

        }

    }

    private void implementActionsListeners() {
        loginButton.setOnClickListener(this);
        viaSocial.setText("Log In via social media");
        forgotPassword.setText("Forgot Password?");
        forgotPassword.setOnClickListener(this);
        registerInstead.setText("Not a member? Register instead");
        registerInstead.setOnClickListener(this);
        linkedInBtn.setOnClickListener(this);
        googleBtn.setOnClickListener(this);
        fb.setOnClickListener(this);
        ok.setOnClickListener(this);
    }


    public void onLoginSuccess() {
        //do something
        String emailid = email.getText().toString();
        String pasword = password.getText().toString();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(getResources().getString(R.string.email), emailid);
        params.put(getResources().getString(R.string.password), pasword);
        new LoginAsync(params, this, dialog, progressdialog, sharedpreferences).execute(getResources().getString(R.string.loginurl));
    }


    public void login() {
        if (!validate()) {
            return;
        }

        onLoginSuccess();
    }

    public boolean validate() {
        boolean valid = true;
        String emailid = email.getText().toString();
        String pasword = password.getText().toString();

        if (pasword.isEmpty() || pasword.length() < 4 || emailid.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            String error_message = "";
            if (emailid.isEmpty()) {
                error_message = "Email id is required";
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
                error_message = "Please enter a valid email Id ";
            }
            if (pasword.isEmpty()) {
                error_message = error_message + "\n" + "Password is required";
            } else if (password.length() < 4) {
                error_message = error_message + "\n" + "Password is too short";
            }
            errorPassword.setText(error_message);

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
        if (id == R.id.btn_login) {
            System.out.println("login clicked");

            login();
//            LISessionManager.getInstance(getApplicationContext()).clearSession();

        } else if (id == R.id.btn_register_instead) {
            System.out.println("register instead clicked");
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        } else if (id == R.id.btn_forgot_password) {
            System.out.println("forgot password clicked");
            /*Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);*/
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        } else if (id == R.id.btn_signup_linkedIn) {
            new LinkedInUtil().fetchData(getApplicationContext(), this, dialog, progressdialog, sharedpreferences);
        } else if (id == R.id.btn_signup_google) {
            googleUtil.signIn(mGoogleApiClient, RC_SIGN_IN, this);
        } else if (id == R.id.fb) {
            getLoginDetails();
            fbBtn.performClick();
        } else if (id == R.id.ok) {
            dialog.dismiss();
        }

    }

    private void getLoginDetails() {
        fbBtn.setReadPermissions("user_friends", "public_profile", "email");
        fbBtn.registerCallback(callbackManager, new FacebookUtil().getFaceBookCallBack(this, dialog, progressdialog, sharedpreferences));

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
                googleUtil.handleSignInResult(result, this, dialog, progressdialog, sharedpreferences);
            } else {
            }
        } else if (requestCode == FB_SIGN_IN) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else {
            LISessionManager.getInstance(getApplicationContext())
                    .onActivityResult(this, requestCode, resultCode, data);
        }

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void generateHashkey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    PACKAGE,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                System.out.println("HAsh is ---------> " + Base64.encodeToString(md.digest(),
                        Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("Name not found", e.getMessage(), e);

        } catch (NoSuchAlgorithmException e) {
            Log.d("Error", e.getMessage(), e);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

}
