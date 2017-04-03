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
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;

public class DashboardActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CarouselPagerAdapter carouselPagerAdapter;
    public ViewPager pager;
    public LinearLayout pager_indicator;
    public ImageView[] dots;
    public HorizontalScrollView horizontalScrollView;
    public int loop=0;
    public int lastposition;
    public ArrayList<DashboardCard> dashboardCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.dots);
        setSupportActionBar(toolbar);
        dashboardCards = getData();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,DashboardActivity.this,R.id.task);

        if(dashboardCards.size() <7){
            loop = dashboardCards.size();
        }else{
            loop=7;
            lastposition =(7*(dashboardCards.size()/7)) ;
        }
        dots = new ImageView[dashboardCards.size()];
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
        carouselPagerAdapter = new CarouselPagerAdapter(this,getSupportFragmentManager(),dashboardCards);
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
        displayscreen();

    }

    private ArrayList<DashboardCard> getData() {
        ArrayList<DashboardCard> dashboardCards = new ArrayList<>();
        DashboardCard dashboardCard = new DashboardCard("Mutual Fund Planner","The Concept of Risk","Risk management is the identification, assessment, and prioritization of risks (defined in ISO 31000 as the effect of uncertainty on objectives) followed by coordinated and economical application of resources to minimize, monitor, and control the probability and/or impact of unfortunate events","https://static1.squarespace.com/static/53c6abe9e4b050924635b68f/t/5525af73e4b0964bcc72c023/1428533108432/","Course");
        dashboardCards.add(dashboardCard);
        DashboardCard dashboardCard1 = new DashboardCard("Mutual Fund Planner","Mid Term Assement","Life is full of risks, and so is a software project. Anything can go wrong anytime. We are always on our toes to make things right – but what about making sure that nothing goes wrong and that when it does we know exactly what to do","https://sites.google.com/site/petercromptonuk/_/rsrc/1443886923312/software-testing/risk-based-testing/risk%20based%20testing.png",16,50,30,"Assesment");
        dashboardCards.add(dashboardCard1);
        DashboardCard dashboardCard2 = new DashboardCard("Mutual Fund Planner","New Challenge","Siddharth has challenged you Do you have what it takes?","http://3.bp.blogspot.com/_1fayrmhTf24/TR101EgZ-pI/AAAAAAAAAWQ/owExremngg0/s1600/priyamani-hot9-773160.jpg",20,40,20,"Challenge");
        dashboardCards.add(dashboardCard2);
        DashboardCard dashboardCard3 = new DashboardCard("Mutual Fund Planner","The concept of Risk","Siddharth has challenged you Do you have what it takes?","http://3.bp.blogspot.com/_1fayrmhTf24/TR101EgZ-pI/AAAAAAAAAWQ/owExremngg0/s1600/priyamani-hot9-773160.jpg","presentation");
        dashboardCards.add(dashboardCard3);
        DashboardCard dashboardCard4 = new DashboardCard("Mutual Fund Planner","The concept of Risk","Siddharth has challenged you Do you have what it takes?","http://3.bp.blogspot.com/_1fayrmhTf24/TR101EgZ-pI/AAAAAAAAAWQ/owExremngg0/s1600/priyamani-hot9-773160.jpg","video");
        dashboardCards.add(dashboardCard4);

        return  dashboardCards;

    }


    public void displayscreen(){
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