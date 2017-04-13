package pro.viksit.com.viksit.home.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.challenge.activity.LeaderBoardActivity;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.role.activity.ModuleActivity;
import pro.viksit.com.viksit.role.activity.RoleActivity;
import pro.viksit.com.viksit.role.activity.RoleDepthActivity;
import pro.viksit.com.viksit.role.activity.RoleDetailActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = HomeActivity.class.getSimpleName();

    private ImageView icon;
    private TextView talentify;
    private Button getStarted;
    private Button member;
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        icon = (ImageView) findViewById(R.id.iv_app_icon);
        talentify = (TextView) findViewById(R.id.tv_talentify);
        getStarted = (Button) findViewById(R.id.btn_get_started);
        member = (Button) findViewById(R.id.btn_member);
        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        if(!sharedpreferences.getString(getResources().getString(R.string.user_profile),"").equalsIgnoreCase("")){
            //Intent i = new Intent(HomeActivity.this,SplashScreenActivity.class);
            //startActivity(i);
        }
        implementListeners();

    }

    private void implementListeners(){
        getStarted.setOnClickListener(this);
        member.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_get_started){
            Log.i(TAG,"get started clicked");
            startActivity(new Intent(HomeActivity.this, SignupActivity.class));
        } else  if(v.getId() == R.id.btn_member){
            Log.i(TAG,"already a member clicked");
            startActivity(new Intent(HomeActivity.this, LeaderBoardActivity.class));
        }

    }
}
