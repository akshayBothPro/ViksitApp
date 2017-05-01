package pro.viksit.com.viksit.dashboard.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.adapter.MyProfilePagerAdapter;

public class MyProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private ImageButton backbtn;
    private Button logout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyProfilePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        backbtn = (ImageButton) findViewById(R.id.ibtn_back);
        logout = (Button) findViewById(R.id.btn_logout);
        tabLayout = (TabLayout) findViewById(R.id.tl_myprofile_tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_myprofile_pager);

        setSupportActionBar(toolbar);
        setTabs();
        implementActions();
    }

    private void setTabs() {
        String[] tabTitles = new String[]{"Performance", "Account Settings"};
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorHeight(0);

        adapter = new MyProfilePagerAdapter(getSupportFragmentManager(), tabTitles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void implementActions(){
        backbtn.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*Intent i=new Intent(MyProfileActivity.this,DashboardActivity.class);
        startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == backbtn.getId()) {
            //dp something
        } else if(v.getId() == logout.getId()) {
            //dp something
        }
    }
}
