package pro.viksit.com.viksit.home.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.linkedin.platform.LISessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.dashboard.util.FacebookUtil;
import pro.viksit.com.viksit.dashboard.util.GoogleUtil;
import pro.viksit.com.viksit.dashboard.util.LinkedInUtil;
import pro.viksit.com.viksit.dashboard.util.LoginAsync;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = SignupActivity.class.getSimpleName();

    private TextView welcome;
    private AppCompatEditText email;
    private TextView errorEmail;
    private AppCompatEditText phoneNumber;
    private AppCompatEditText password;
    private ImageView showPassword;
    private Button signup, btn_signup_linkedIn, btn_signup_google, fb;
    private Button viaSocial;

    private LoginButton fbBtn;
    private Button loginInstead, ok;
    private int screenWidth;
    private int screenHeight;
    private MaterialDialog dialog;
    private MaterialDialog progressdialog;
    CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private SharedPreferences sharedpreferences;
    private final GoogleUtil googleUtil = new GoogleUtil();
    private static final int RC_SIGN_IN = 9001;
    private static final int FB_SIGN_IN = 64206;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_signup);
        dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_error, false)
                .build();
        progressdialog = new MaterialDialog.Builder(this)
                .content("Please wait ..")
                .progress(true, 0)
                .build();
        callbackManager = CallbackManager.Factory.create();
        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        ok = (Button) dialog.getCustomView().findViewById(R.id.ok);

        welcome = (TextView) findViewById(R.id.tv_welcome);
        email = (AppCompatEditText) findViewById(R.id.apet_email);
        errorEmail = (TextView) findViewById(R.id.tv_error_email);
        phoneNumber = (AppCompatEditText) findViewById(R.id.apet_phone_number);
        password = (AppCompatEditText) findViewById(R.id.apet_password);
        showPassword = (ImageView) findViewById(R.id.iv_show_password);
        signup = (Button) findViewById(R.id.btn_signup);
        viaSocial = (Button) findViewById(R.id.tv_via_social);
        loginInstead = (Button) findViewById(R.id.btn_login_instead);

        fbBtn = (LoginButton) findViewById(R.id.btn_signup_fb);
//btn_signup_linkedIn,btn_signup_google,fb,btn_signup_fb
        btn_signup_linkedIn = (Button) findViewById(R.id.btn_signup_linkedIn);
        btn_signup_google = (Button) findViewById(R.id.btn_signup_google);
        fb = (Button) findViewById(R.id.fb);
        fbBtn = (LoginButton) findViewById(R.id.btn_signup_fb);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        implementActions();
    }

    private void implementActions() {

        viaSocial.setText("Sign Up via social media");
        signup.setOnClickListener(this);
        showPassword.setOnClickListener(this);

        loginInstead.setText("Already a member? Login instead");
        loginInstead.setOnClickListener(this);
        btn_signup_linkedIn.setOnClickListener(this);
        btn_signup_google.setOnClickListener(this);
        fb.setOnClickListener(this);
        ok.setOnClickListener(this);

        Drawable sourceDrawable = getResources().getDrawable(R.mipmap.ic_remove_red_eye_white_24dp);
        sourceDrawable.setColorFilter(getResources().getColor(R.color.eye_icon_color), PorterDuff.Mode.SRC_IN);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_signup:
                System.out.println("sign up clicked");
                signup();
                break;
            case R.id.btn_login_instead:
                break;
            case R.id.btn_signup_linkedIn:
                new LinkedInUtil().fetchData(getApplicationContext(), this, dialog, progressdialog, sharedpreferences);

                break;
            case R.id.btn_signup_google:
                googleUtil.signIn(mGoogleApiClient, RC_SIGN_IN, this);

                break;
            case R.id.fb:
                getLoginDetails();
                fbBtn.performClick();
                break;
            case R.id.ok:
                dialog.dismiss();
                break;
            case R.id.iv_show_password:
                if(password.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
        }


    }

    private void getLoginDetails() {
        fbBtn.setReadPermissions("user_friends", "public_profile", "email");
        fbBtn.registerCallback(callbackManager, new FacebookUtil().getFaceBookCallBack(this, dialog, progressdialog, sharedpreferences));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SignupActivity.this, HomeActivity.class);
        startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

    public void signup() {
        if (!validate()) {
            onSignupFailed();
            return;
        }
        String emailid = email.getText().toString();
        String pasword = password.getText().toString();
        String phone = phoneNumber.getText().toString();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(getResources().getString(R.string.email), emailid);
        params.put(getResources().getString(R.string.password), pasword);
        params.put(getResources().getString(R.string.phone), phone);
        new LoginAsync(params, this, dialog, progressdialog, sharedpreferences).execute(getResources().getString(R.string.signupurl));


        //else do ur thing
    }

    public boolean validate() {
        boolean valid = true;
        String emailid = email.getText().toString();
        String pasword = password.getText().toString();
        String phone = phoneNumber.getText().toString();

        if (pasword.isEmpty() || pasword.length() < 4 || pasword.length() > 10 || emailid.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailid).matches() || phone.isEmpty()) {

            if (pasword.isEmpty() || pasword.length() < 4 || pasword.length() > 10) {
                password.setError("Type 4 and 10 alphanumeric characters");
            }

            if (phone.length() != 10) {
                phoneNumber.setError("Type 10 characters");
            }
            errorEmail.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            errorEmail.setVisibility(View.GONE);
        }

        return valid;
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();
        //do something
    }

    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
}