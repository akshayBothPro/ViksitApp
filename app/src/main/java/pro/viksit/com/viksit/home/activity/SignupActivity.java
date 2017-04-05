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
    private TextView errorPhone;
    private AppCompatEditText password;
    private TextView errorPassword;
    private AppCompatEditText confirmPassword;
    private Button signup;
    private TextView viaSocial;
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
        errorPhone =(TextView) findViewById(R.id.tv_error_phone);
        password = (AppCompatEditText) findViewById(R.id.apet_password);
        errorPassword =(TextView) findViewById(R.id.tv_error_password);
        signup = (Button) findViewById(R.id.btn_signup);
        viaSocial = (TextView) findViewById(R.id.tv_via_social);
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

        //else do ur thing
    }

    public boolean validate() {
        boolean valid = true;
        String emailid = email.getText().toString();
        String phoneNo = phoneNumber.getText().toString();
        String pasword = password.getText().toString();
        ArrayList <String> errorlist = new ArrayList<>();

        if (emailid.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            errorEmail.setVisibility(View.VISIBLE);
            errorlist.add("Email");
            valid = false;

        } else {
            errorEmail.setVisibility(View.GONE);
        }

        if (phoneNo.isEmpty() || !Patterns.PHONE.matcher(phoneNo).matches() || phoneNo.length() != 10) {
            errorPhone.setVisibility(View.VISIBLE);
            errorlist.add("Phone Number");
            valid = false;
        } else {
            errorPhone.setVisibility(View.GONE);
        }

        if (pasword.isEmpty() || pasword.length() < 4 || pasword.length() > 10) {
            errorPassword.setVisibility(View.VISIBLE);
            errorlist.add("Password");
            valid = false;
        } else {
            errorPassword.setVisibility(View.GONE);
        }
        /*String errorr = "";
        for(int i = 0; i < errorlist.size();i++){
            if(i == errorlist.size()-1 && i != 0) {
                errorr = errorlist.get(i) + "are incorrect";
                errorPassword.setText(errorr);
                errorPassword.setVisibility(View.VISIBLE);
            } else if(i == errorlist.size()-1 && i == 0) {
                errorr = errorlist.get(i) + "is incorrect";
                errorPassword.setText(errorr);
                errorPassword.setVisibility(View.VISIBLE);
            } else {
                errorr = errorr + errorlist.get(i) + "/";
            }
        }*/
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
