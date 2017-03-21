package pro.viksit.com.viksit.job.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ViewParent;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;
import pro.viksit.com.viksit.job.adapter.JobTabPagerAdapter;
import pro.viksit.com.viksit.role.activity.RoleActivity;

public class JobActivity extends AppCompatActivity {
    private static final String TAG = JobActivity.class.getSimpleName();

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private JobTabPagerAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        viewPager = (ViewPager) findViewById(R.id.vp_job_pager);
        tabLayout = (TabLayout) findViewById(R.id.tl_job_tabs);

        setSupportActionBar(toolbar);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,JobActivity.this,R.id.job);//setting bottom navigation bar
        setTabs();
    }

    private void setTabs() {
        String[] tabTitles = new String[]{"OPENINGS", "APPLIED"};

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorHeight(0);

        adapter = new JobTabPagerAdapter(getSupportFragmentManager(), tabTitles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
