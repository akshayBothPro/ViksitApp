package pro.viksit.com.viksit.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pro.viksit.com.viksit.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private TextView welcome;
    private AppCompatEditText email;
    private AppCompatEditText password;
    private Button loginButton;
    private TextView forgotPassword;
    private TextView registerInstead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        welcome = (TextView) findViewById(R.id.tv_login_welcome);
        email = (AppCompatEditText) findViewById(R.id.apet_login_email);
        password = (AppCompatEditText) findViewById(R.id.apet_login_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        forgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        registerInstead = (TextView) findViewById(R.id.tv_register_instead);

        implementListeners();

    }

    private void implementListeners(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"login button clicked");
                loginButton.setBackgroundColor(getResources().getColor(R.color.theme_color));
                loginButton.setTextColor(getResources().getColor(R.color.white_color));
                login();
            }
        });
        forgotPassword.setText(Html.fromHtml("<u>Forgot Password</u>"));
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"forgot password clicked");
            }
        });

        registerInstead.setText(Html.fromHtml("<u>Not a member yet? Register instead</u>"));
        registerInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"register instead clicked");
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
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
            email.setError("Enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (pasword.isEmpty() || pasword.length() < 4 || pasword.length() > 10) {
            password.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}
