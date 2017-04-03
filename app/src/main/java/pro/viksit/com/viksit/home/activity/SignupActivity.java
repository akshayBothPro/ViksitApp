package pro.viksit.com.viksit.home.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = SignupActivity.class.getSimpleName();

    private TextView welcome;
    private AppCompatEditText email;
    private AppCompatEditText phoneNumber;
    private AppCompatEditText password;
    private AppCompatEditText confirmPassword;
    private Button signup;
    private TextView viaSocial;
    private ImageButton linkedBtn;
    private ImageButton googleBtn;
    private ImageButton fbBtn;
    private Button loginInstead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        welcome = (TextView) findViewById(R.id.tv_welcome);
        email = (AppCompatEditText) findViewById(R.id.apet_email);
        phoneNumber = (AppCompatEditText) findViewById(R.id.apet_phone_number);
        password = (AppCompatEditText) findViewById(R.id.apet_password);
        signup = (Button) findViewById(R.id.btn_signup);
        viaSocial = (TextView) findViewById(R.id.tv_via_social);
        loginInstead = (Button) findViewById(R.id.btn_login_instead);
        linkedBtn = (ImageButton) findViewById(R.id.btn_signup_linkedIn);
        googleBtn = (ImageButton) findViewById(R.id.btn_signup_google);
        fbBtn = (ImageButton) findViewById(R.id.btn_signup_fb);

        implementListeners();
    }

    private void implementListeners(){

        viaSocial.setText("Sign Up via social media");
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"sign up clicked");
                Intent k = new Intent(SignupActivity.this, DashboardActivity.class);
                startActivity(k);
            }
        });

        loginInstead.setText("Already a member? Login instead");
        loginInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"login instead clicked");
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));

            }
        });

        Drawable sourceDrawable = getResources().getDrawable(R.mipmap.ic_remove_red_eye_white_24dp);
        sourceDrawable.setColorFilter(getResources().getColor(R.color.eye_icon_color), PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(SignupActivity.this,HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

    /*public boolean isValidEmail() {
        String emailid = email.getText().toString();
        String pasword = password.getText().toString();

        if (emailid.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            email.setError("Enter a valid email address");

        } else {
            email.setError(null);
        }

        if (pasword.isEmpty() || pasword.length() < 4 || pasword.length() > 10) {
            password.setError("Between 4 and 10 alphanumeric characters");
            *//*valid = false;*//*
        } else {
            password.setError(null);
        }
    }*/



}
