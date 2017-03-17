package pro.viksit.com.viksit.role.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;

public class RoleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
         BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,RoleActivity.this,R.id.role);


                /*(BottomNavigationView)
                findViewById(R.id.bottom_navigation);*/

    }


}
