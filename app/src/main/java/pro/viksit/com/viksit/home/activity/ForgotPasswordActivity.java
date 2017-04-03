package pro.viksit.com.viksit.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener{

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

        implementActions();

    }

    private void implementActions(){
        signInDifferent.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(ForgotPasswordActivity.this,HomeActivity.class);
        startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.btn_sign_in_different) {
            System.out.println("sign in different clicked");
            startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
        }
    }
}
