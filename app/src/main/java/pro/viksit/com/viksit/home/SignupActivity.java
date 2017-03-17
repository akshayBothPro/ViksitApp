package pro.viksit.com.viksit.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = SignupActivity.class.getSimpleName();

    private TextView welcome;
    private TextView subtitle;
    private EditText email;
    private EditText phoneNumber;
    private EditText password;
    private EditText confirmPassword;
    private Button signup;
    private TextView loginInstead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        welcome = (TextView) findViewById(R.id.tv_welcome);
        subtitle = (TextView) findViewById(R.id.tv_journey_begins);
        email = (EditText) findViewById(R.id.et_email);
        phoneNumber = (EditText) findViewById(R.id.et_phone_number);
        password = (EditText) findViewById(R.id.et_password);
        confirmPassword = (EditText) findViewById(R.id.et_confirm_passsword);
        signup = (Button) findViewById(R.id.btn_signup);
        loginInstead = (TextView) findViewById(R.id.tv_login_instead);

        implementListeners();
    }

    private void implementListeners(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"sign up clicked");
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
