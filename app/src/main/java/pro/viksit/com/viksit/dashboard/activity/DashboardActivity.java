package pro.viksit.com.viksit.dashboard.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.adapter.ShadowTransformer;
import pro.viksit.com.viksit.dashboard.adapter.ViewPagerAdapter;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;

public class DashboardActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,DashboardActivity.this,R.id.task);
        ArrayList<String> list = new ArrayList<>();
        for(int i=0 ; i<30 ; i++){
            list.add("champu"+i);
        }
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),list,dpToPixels(5, this));
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, viewPagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);


    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}