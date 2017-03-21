package pro.viksit.com.viksit.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = SignupActivity.class.getSimpleName();

    private TextView welcome;
    private TextView subtitle;
    private AppCompatEditText email;
    private AppCompatEditText phoneNumber;
    private AppCompatEditText password;
    private AppCompatEditText confirmPassword;
    private Button signup;
    private TextView loginInstead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        welcome = (TextView) findViewById(R.id.tv_welcome);
        subtitle = (TextView) findViewById(R.id.tv_journey_begins);
        email = (AppCompatEditText) findViewById(R.id.apet_email);
        phoneNumber = (AppCompatEditText) findViewById(R.id.apet_phone_number);
        password = (AppCompatEditText) findViewById(R.id.apet_password);
        signup = (Button) findViewById(R.id.btn_signup);
        loginInstead = (TextView) findViewById(R.id.tv_login_instead);

        implementListeners();
    }

    private void implementListeners(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"sign up clicked");
                Intent k = new Intent(SignupActivity.this, DashboardActivity.class);
                startActivity(k);
            }
        });

        loginInstead.setText(Html.fromHtml("<u>Already a member? Login instead</u>"));
        loginInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"login instead clicked");
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }
}
