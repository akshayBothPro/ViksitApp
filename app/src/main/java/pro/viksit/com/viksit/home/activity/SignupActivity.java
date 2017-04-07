package pro.viksit.com.viksit.home.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = SignupActivity.class.getSimpleName();

    private TextView welcome;
    private AppCompatEditText email;
    private TextView errorEmail;
    private AppCompatEditText phoneNumber;
    private AppCompatEditText password;
    private Button signup;
    private Button viaSocial;
    private ImageButton linkedBtn;
    private ImageButton googleBtn;
    private ImageButton fbBtn;
    private Button loginInstead;

    private int screenWidth;
    private int screenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        welcome = (TextView) findViewById(R.id.tv_welcome);
        email = (AppCompatEditText) findViewById(R.id.apet_email);
        errorEmail =(TextView) findViewById(R.id.tv_error_email);
        phoneNumber = (AppCompatEditText) findViewById(R.id.apet_phone_number);
        password = (AppCompatEditText) findViewById(R.id.apet_password);
        signup = (Button) findViewById(R.id.btn_signup);
        viaSocial = (Button) findViewById(R.id.tv_via_social);
        loginInstead = (Button) findViewById(R.id.btn_login_instead);
        linkedBtn = (ImageButton) findViewById(R.id.btn_signup_linkedIn);
        googleBtn = (ImageButton) findViewById(R.id.btn_signup_google);
        fbBtn = (ImageButton) findViewById(R.id.btn_signup_fb);

        implementActions();
    }

    private void implementActions(){

        viaSocial.setText("Sign Up via social media");
        signup.setOnClickListener(this);

        loginInstead.setText("Already a member? Login instead");
        loginInstead.setOnClickListener(this);

        Drawable sourceDrawable = getResources().getDrawable(R.mipmap.ic_remove_red_eye_white_24dp);
        sourceDrawable.setColorFilter(getResources().getColor(R.color.eye_icon_color), PorterDuff.Mode.SRC_IN);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_signup) {
            System.out.println("sign up clicked");
            signup();
        } else if(id == R.id.btn_login_instead){
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(SignupActivity.this,HomeActivity.class);
        startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

    public void signup(){
        if (!validate()) {
            onSignupFailed();
            return;
        }

        startActivity(new Intent(SignupActivity.this,OTPActivity.class));

        //else do ur thing
    }

    public boolean validate() {
        boolean valid = true;
        String emailid = email.getText().toString();
        String pasword = password.getText().toString();
        String phone = phoneNumber.getText().toString();

        if (pasword.isEmpty() || pasword.length() < 4 || pasword.length() > 10 ||emailid.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailid).matches() || phone.isEmpty()) {

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

}
