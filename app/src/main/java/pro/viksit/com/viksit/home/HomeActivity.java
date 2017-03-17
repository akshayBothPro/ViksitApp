package pro.viksit.com.viksit.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class HomeActivity extends Activity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    private ImageView icon;
    private TextView talentify;
    private Button getStarted;
    private Button member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        icon = (ImageView) findViewById(R.id.iv_app_icon);
        talentify = (TextView) findViewById(R.id.tv_talentify);
        getStarted = (Button) findViewById(R.id.btn_get_started);
        member = (Button) findViewById(R.id.btn_member);

        implementListeners();

    }

    private void implementListeners(){
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"get started clicked");
                startActivity(new Intent(HomeActivity.this, SignupActivity.class));
            }
        });

        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"already a member clicked");
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });
    }
}