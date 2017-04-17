package pro.viksit.com.viksit.dashboard.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.HexagonImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.util.DisplayUtil;
import pro.viksit.com.viksit.util.ImageSaver;
import pro.viksit.com.viksit.util.SaveImageAsync;
import pro.viksit.com.viksit.dashboard.adapter.CardAdapter.CarouselPagerAdapter;
import pro.viksit.com.viksit.dashboard.async.DashboardCardAsync;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.dashboard.pojo.StudentProfile;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private ImageView notification;
    private CarouselPagerAdapter carouselPagerAdapter;
    public ViewPager pager;
    public LinearLayout pager_indicator;
    public ImageView[] dots;
    public HorizontalScrollView horizontalScrollView;
    public int loop=0;
    public int lastposition;
    HexagonImageView profile_pic;
    TextView points,coins;
    private SharedPreferences sharedpreferences;
    private ProgressBar progress;
    private RelativeLayout error_layout;
    private Button button_layout;
    private StudentProfile studentProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        notification = (ImageView) findViewById(R.id.notfication);
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.dots);
        profile_pic = (HexagonImageView) findViewById(R.id.profile_pic);
        points = (TextView) findViewById(R.id.points);
        coins = (TextView) findViewById(R.id.coins);
        progress = (ProgressBar) findViewById(R.id.progress);
        error_layout = (RelativeLayout) findViewById(R.id.error_layout);
        button_layout = (Button) findViewById(R.id.button_layout);
        profile_pic = (HexagonImageView) findViewById(R.id.profile_pic);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,DashboardActivity.this,R.id.task);

        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        String profile_date= sharedpreferences.getString(getResources().getString(R.string.user_profile),"");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        studentProfile = gson.fromJson(profile_date,StudentProfile.class);
        setSupportActionBar(toolbar);
        /*doforStatic();*/

        new DashboardCardAsync(this,getSupportFragmentManager(),studentProfile.getId(),sharedpreferences,pager_indicator,this,pager,loop,progress,error_layout).execute();
        button_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DashboardCardAsync(DashboardActivity.this,getSupportFragmentManager(),studentProfile.getId(),sharedpreferences,pager_indicator,DashboardActivity.this,pager,loop,progress,error_layout).execute();

            }
        });

        ImageSaver local_profile = new ImageSaver(this).
                setParentDirectoryName("profile_pic").
                setFileName(new DisplayUtil().getFileNameReplaced("profile_pic.jpg")).
                setExternal(ImageSaver.isExternalStorageReadable());

        if(local_profile.checkFile()){
            profile_pic.setImageBitmap(local_profile.load());
        }else{
            if(studentProfile.getProfileImage() != null && !studentProfile.getProfileImage().equalsIgnoreCase("")){
                if(studentProfile.getProfileImage().contains("http")) {
                    Picasso.with(this)
                            .load(studentProfile.getProfileImage()).into(profile_pic);
                    new SaveImageAsync(local_profile).execute(studentProfile.getProfileImage());
                }else{
                    Picasso.with(this)
                            .load(getResources().getString(R.string.resourceserverip)+studentProfile.getProfileImage()).into(profile_pic);
                    new SaveImageAsync(local_profile).execute(getResources().getString(R.string.resourceserverip)+studentProfile.getProfileImage());

                }

            }else{
                profile_pic.setBackground(getResources().getDrawable(R.drawable.profile_default));
            }
        }

        coins.setText(studentProfile.getCoins()+"");
        points.setText(studentProfile.getExperiencePoints()+"");

        //
        notification.setOnClickListener(this);

    }

    public void convertToJSON(DashboardCard dashboardCard){
        Gson gson = new Gson();
        String jsonInString = gson.toJson(dashboardCard);
        System.out.println("this is json "+jsonInString);
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.notfication){
            startActivity(new Intent(DashboardActivity.this, NotificationActivity.class));
        }
    }
}