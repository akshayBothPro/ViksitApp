package pro.viksit.com.viksit.dashboard.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.adapter.CardAdapter.CarouselPagerAdapter;
import pro.viksit.com.viksit.dashboard.adapter.ViewPagerAdapter;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;

public class DashboardActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPagerAdapter viewPagerAdapter;
    private CarouselPagerAdapter carouselPagerAdapter;
    public ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager) findViewById(R.id.viewpager);

        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,DashboardActivity.this,R.id.task);
        ArrayList<String> list = new ArrayList<>();
        for(int i=0 ; i<30 ; i++){
            list.add("champu"+i);
        }
        carouselPagerAdapter = new CarouselPagerAdapter(this,getSupportFragmentManager(),list);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 12) );
        System.out.println("pageMargin "+pageMargin);
        int pagerPadding = 40;
        pager.setClipToPadding(false);

        pager.setPadding(pageMargin, pageMargin/2, pageMargin, 0);
        // pager.setPageMargin(-pageMargin);
      /*  viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),list,dpToPixels(5, this));
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, viewPagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);*/

        pager.setAdapter(carouselPagerAdapter);
        carouselPagerAdapter.notifyDataSetChanged();

        pager.addOnPageChangeListener(carouselPagerAdapter);
        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(1);
        pager.setOffscreenPageLimit(3);


    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}