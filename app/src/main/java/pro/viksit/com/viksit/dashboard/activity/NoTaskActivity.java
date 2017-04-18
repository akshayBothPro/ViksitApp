package pro.viksit.com.viksit.dashboard.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.HexagonImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.challenge.activity.LeaderBoardActivity;
import pro.viksit.com.viksit.util.DisplayUtil;
import pro.viksit.com.viksit.util.ImageSaver;
import pro.viksit.com.viksit.util.SaveImageAsync;
import pro.viksit.com.viksit.dashboard.pojo.StudentProfile;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;

public class NoTaskActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private TextView points,coins;
    private SharedPreferences sharedpreferences;
    private StudentProfile studentProfile;
    private HexagonImageView profile_pic;
    private ImageView notification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_tasks_layout);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,NoTaskActivity.this,R.id.task);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        points = (TextView) findViewById(R.id.points);
        coins = (TextView) findViewById(R.id.coins);
        profile_pic = (HexagonImageView) findViewById(R.id.profile_pic);
        notification = (ImageView) findViewById(R.id.notfication);

        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        String profile_date= sharedpreferences.getString(getResources().getString(R.string.user_profile),"");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        studentProfile = gson.fromJson(profile_date,StudentProfile.class);
        setSupportActionBar(toolbar);
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
        notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.notfication){
            startActivity(new Intent(NoTaskActivity.this, LeaderBoardActivity.class));
        }
    }
}
