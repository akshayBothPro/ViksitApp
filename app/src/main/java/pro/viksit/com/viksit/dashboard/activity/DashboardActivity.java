package pro.viksit.com.viksit.dashboard.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    public LinearLayout pager_indicator;
    public ImageView[] dots;
    public HorizontalScrollView horizontalScrollView;
    public int loop=0;
    public int lastposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.dots);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,DashboardActivity.this,R.id.task);
        ArrayList<String> list = new ArrayList<>();
        for(int i=0 ; i<10 ; i++){
            list.add("champu"+i);
        }
        if(list.size() <7){
            loop = list.size();
        }else{
            loop=7;
            lastposition =(7*(list.size()/7)) ;
        }
        dots = new ImageView[list.size()];
        for (int i = 0; i < loop; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(4, 0, 4, 0);
            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
        carouselPagerAdapter = new CarouselPagerAdapter(this,getSupportFragmentManager(),list);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 12) );
        System.out.println("pageMargin "+pageMargin);
        pager.setClipToPadding(false);
        pager.setPadding(pageMargin, pageMargin/2, pageMargin, 0);
        pager.setAdapter(carouselPagerAdapter);
        carouselPagerAdapter.notifyDataSetChanged();
        pager.addOnPageChangeListener(carouselPagerAdapter);
        pager.setCurrentItem(1);
        pager.setOffscreenPageLimit(3);
        switch (getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                Toast.makeText(DashboardActivity.this, "LDPI", Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                Toast.makeText(DashboardActivity.this, "MDPI", Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_HIGH:
                Toast.makeText(DashboardActivity.this, "HDPI", Toast.LENGTH_LONG).show();

                break;
            case DisplayMetrics.DENSITY_XHIGH:
                Toast.makeText(DashboardActivity.this, "XHDPI", Toast.LENGTH_LONG).show();

                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                Toast.makeText(DashboardActivity.this, "XXHDPI", Toast.LENGTH_LONG).show();

                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                Toast.makeText(DashboardActivity.this, "XXXHDPI", Toast.LENGTH_LONG).show();

                break;
        }

    }
}