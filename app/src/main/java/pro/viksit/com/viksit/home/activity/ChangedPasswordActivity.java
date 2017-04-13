package pro.viksit.com.viksit.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class ChangedPasswordActivity extends AppCompatActivity {
    private TextView title;
    private TextView info;
    private Button gotologin;
    private String otp,jsonresponse,phonenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changed_password);

        title = (TextView) findViewById(R.id.tv_password_changed);
        info = (TextView) findViewById(R.id.tv_password_info);
        gotologin = (Button) findViewById(R.id.btn_goto_login);

        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changepassword = new Intent(ChangedPasswordActivity.this, LoginActivity.class);
                changepassword.putExtra("otp", otp);
                changepassword.putExtra("phonenos", phonenos);
                changepassword.putExtra("jsonresponse", jsonresponse);
                startActivity(changepassword);
            }
        });
        if(getIntent()!= null){
            if(getIntent().getStringExtra("otp") != null){
                otp = getIntent().getStringExtra("otp");
            }
            if(getIntent().getStringExtra("phonenos") != null){
                phonenos = getIntent().getStringExtra("phonenos");
            }
            if(getIntent().getStringExtra("jsonresponse") != null){
                jsonresponse = getIntent().getStringExtra("jsonresponse");
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent changepassword = new Intent(ChangedPasswordActivity.this, ResetPasswordActivity.class);
        changepassword.putExtra("otp", otp);
        changepassword.putExtra("phonenos", phonenos);
        changepassword.putExtra("jsonresponse", jsonresponse);
        startActivity(changepassword);
    }

}
