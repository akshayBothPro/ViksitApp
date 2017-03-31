package pro.viksit.com.viksit.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextView title;
    private TextView info;
    private AppCompatEditText input;
    private Button submit;
    private Button signInDifferent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        title = (TextView) findViewById(R.id.tc_forgot_title);
        info = (TextView) findViewById(R.id.tv_forgot_info);
        input = (AppCompatEditText) findViewById(R.id.apet_email);
        submit = (Button) findViewById(R.id.btn_forgot_submit);
        signInDifferent = (Button) findViewById(R.id.btn_sign_in_different);

    }
}
