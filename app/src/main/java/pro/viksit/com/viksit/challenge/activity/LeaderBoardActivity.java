package pro.viksit.com.viksit.challenge.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.CircleTransform;
import pro.viksit.com.viksit.challenge.adapter.LeaderBoardRecyclerAdapter;
import pro.viksit.com.viksit.challenge.pojo.LeaderBoardProfile;
import pro.viksit.com.viksit.role.activity.CheckoutActivity;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class LeaderBoardActivity extends AppCompatActivity {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private RelativeLayout headerContainer;
    private AppCompatSpinner dropdown;
    private ArrayList<String> spinnerList;
    private ArrayAdapter<String> spinnerAdapter;
    private Toolbar toolbar;
    private ImageButton goBack;
    private TextView barTitle;
    private ArrayList<LeaderBoardProfile> profileList;
    private ImageView first, second,third;
    private Button firstRank, secondRank, thirdRank;
    private TextView firstName, secondName, thirdName;
    private TextView firstXP, secondXP, thirdXP;
    private RecyclerView verticalRecycler;
    private LeaderBoardRecyclerAdapter adapter;

    private int screenWidth,screenHeight;
    private double diagonalInches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        headerContainer = (RelativeLayout) findViewById(R.id.rl_header_con);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        goBack = (ImageButton) findViewById(R.id.btn_back);
        barTitle = (TextView) findViewById(R.id.tv_notification_toolbar_title);
        dropdown = (AppCompatSpinner) findViewById(R.id.spin_dropdown);
        first = (ImageView) findViewById(R.id.iv_1st);
        firstRank = (Button) findViewById(R.id.btn_1st_rank);
        firstName = (TextView) findViewById(R.id.tv_1st_name);
        firstXP = (TextView) findViewById(R.id.tv_1st_xp);
        second = (ImageView) findViewById(R.id.iv_2nd);
        secondRank = (Button) findViewById(R.id.btn_2nd_rank);
        secondName = (TextView) findViewById(R.id.tv_2nd_name);
        secondXP = (TextView) findViewById(R.id.tv_2nd_xp);
        third = (ImageView) findViewById(R.id.iv_3rd);
        thirdRank = (Button) findViewById(R.id.btn_3rd_rank);
        thirdName = (TextView) findViewById(R.id.tv_3rd_name);
        thirdXP = (TextView) findViewById(R.id.tv_3rd_xp);
        verticalRecycler = (RecyclerView) findViewById(R.id.rv_leaderboard);


        setSupportActionBar(toolbar);
        getWidthAndHeight();
        implementActions();

    }

    private void implementActions(){
        //setting dropdown
        spinnerList = new ArrayList<String>();
        spinnerList.add("All Roles");
        spinnerList.add("Business");
        spinnerList.add("Computers");
        spinnerList.add("Education");
        spinnerList.add("Personal");
        spinnerList.add("Travel");
        spinnerAdapter = new ArrayAdapter<>(this, R.layout.dropdown_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(spinnerAdapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // setting vertical recycler view
        profileList = setDummyData(profileList);
        verticalRecycler.setHasFixedSize(true);
        adapter = new LeaderBoardRecyclerAdapter(profileList,getBaseContext(),screenWidth,screenHeight,diagonalInches);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        verticalRecycler.setLayoutManager(linearLayoutManager);
        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(adapter);
        verticalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), verticalRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Vposition: " + position);
                        Intent intent = new Intent(LeaderBoardActivity.this, CheckoutActivity.class);
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );


        Picasso.with(this).load(R.drawable.ic_urvashi).transform(new CircleTransform()).into(first);
        Picasso.with(this).load(R.drawable.ic_sunny).transform(new CircleTransform()).into(second);
        Picasso.with(this).load(R.drawable.ic_deepika).transform(new CircleTransform()).into(third);

        if (diagonalInches>=6.5){
            ViewGroup.LayoutParams params = first.getLayoutParams();
            params.height = screenHeight/6;
            params.width = screenHeight/6;
            first.setLayoutParams(params);

            params = second.getLayoutParams();
            params.height = screenHeight/7;
            params.width = screenHeight/7;
            second.setLayoutParams(params);

            params = third.getLayoutParams();
            params.height = screenHeight/8;
            params.width = screenHeight/8;
            third.setLayoutParams(params);

            params = firstRank.getLayoutParams();
            params.height = 40;
            params.width = 40;
            firstRank.setLayoutParams(params);

            params = secondRank.getLayoutParams();
            params.height = 40;
            params.width = 40;
            secondRank.setLayoutParams(params);

            params = thirdRank.getLayoutParams();
            params.height = 40;
            params.width = 40;
            thirdRank.setLayoutParams(params);
        }
    }

    public ArrayList<LeaderBoardProfile> setDummyData(ArrayList<LeaderBoardProfile> list){
        LeaderBoardProfile profile;
        list = new ArrayList<>();

        for(int i = 0 ; i < 4; i++) {
            profile = new LeaderBoardProfile(R.drawable.ic_urvashi, "Urvashi", 4, 2596);
            list.add(profile);

            profile = new LeaderBoardProfile(R.drawable.ic_sunny, "Sunny", 5, 4796);
            list.add(profile);

            profile = new LeaderBoardProfile(R.drawable.ic_deepika, "Deepika", 6, 4896);
            list.add(profile);

            profile = new LeaderBoardProfile(R.drawable.ic_disha, "Disha", 7, 4996);
            list.add(profile);
        }

        return list;
    }

    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;

        float yInches= displaymetrics.heightPixels/displaymetrics.ydpi;
        float xInches= displaymetrics.widthPixels/displaymetrics.xdpi;
        diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
    }
}
