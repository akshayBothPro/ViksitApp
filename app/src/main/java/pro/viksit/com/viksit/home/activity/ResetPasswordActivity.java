package pro.viksit.com.viksit.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextView title;
    private AppCompatEditText newPassword;
    private AppCompatEditText confirmPassword;
    private Button submit;
    private Button signindifferent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        title = (TextView) findViewById(R.id.tc_reset_title);
        newPassword = (AppCompatEditText) findViewById(R.id.apet_new_password);
        confirmPassword = (AppCompatEditText) findViewById(R.id.apet_confirm_password);
        submit = (Button) findViewById(R.id.btn_reset_submit);
        signindifferent = (Button) findViewById(R.id.btn_sign_in_different);

    }
}
