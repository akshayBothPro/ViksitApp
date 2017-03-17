package pro.viksit.com.viksit.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private TextView welcome;
    private EditText email;
    private EditText password;
    private Button loginButton;
    private TextView forgotPassword;
    private TextView registerInstead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        welcome = (TextView) findViewById(R.id.tv_login_welcome);
        email = (EditText) findViewById(R.id.et_login_email);
        password = (EditText) findViewById(R.id.et_login_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        forgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        registerInstead = (TextView) findViewById(R.id.tv_register_instead);


    }

    private void implementListeners(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"login button clicked");
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"forgot password clicked");
            }
        });

        registerInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"register instead clicked");
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }
}
