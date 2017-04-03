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
                startActivity(new Intent(ChangedPasswordActivity.this, LoginActivity.class));

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(ChangedPasswordActivity.this,LoginActivity.class);
        startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

}
