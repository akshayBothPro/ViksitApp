package pro.viksit.com.viksit.home.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.recievers.NetworkChangeReceiver;
import pro.viksit.com.viksit.role.activity.RoleDepthActivity;
import pro.viksit.com.viksit.role.util.AssessmentReportAsync;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = HomeActivity.class.getSimpleName();

    private ImageView icon;
    private TextView talentify;
    private Button getStarted, member;
    private SharedPreferences sharedpreferences;
    int PERMISSION_ALL = 1;

    String[] PERMISSIONS = {Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_SMS, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WAKE_LOCK, Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.SET_ALARM, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.SEND_SMS, Manifest.permission.VIBRATE, Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.BROADCAST_STICKY};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        registerReceiver(new NetworkChangeReceiver(),
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        icon = (ImageView) findViewById(R.id.iv_app_icon);
        talentify = (TextView) findViewById(R.id.tv_talentify);
        getStarted = (Button) findViewById(R.id.btn_get_started);
        member = (Button) findViewById(R.id.btn_member);
        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        /*new AssessmentReportAsync(this).execute();*/

        if(!sharedpreferences.getString(getResources().getString(R.string.user_profile),"").equalsIgnoreCase("")){
            Intent i = new Intent(HomeActivity.this,SplashScreenActivity.class);
            startActivity(i);
        }
        else {
            implementListeners();
        }
    }

    private void implementListeners(){
        getStarted.setOnClickListener(this);
        member.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_get_started){
            Log.i(TAG,"get started clicked");
            startActivity(new Intent(HomeActivity.this, BatchCodeActivity.class));
        } else  if(v.getId() == R.id.btn_member){
            Log.i(TAG,"already a member clicked");
            startActivity(new Intent(HomeActivity.this, OTPActivity.class));
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
