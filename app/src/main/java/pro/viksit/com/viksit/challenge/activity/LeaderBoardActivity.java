package pro.viksit.com.viksit.challenge.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.challenge.adapter.LeaderBoardRecyclerAdapter;
import pro.viksit.com.viksit.challenge.async.LeaderBoardAsync;
import pro.viksit.com.viksit.challenge.pojo.LeaderBoardCourse;
import pro.viksit.com.viksit.challenge.pojo.StudentRankPOJO;
import pro.viksit.com.viksit.role.activity.CheckoutActivity;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;
import pro.viksit.com.viksit.util.CircleTransform;
import pro.viksit.com.viksit.util.DisplayUtil;
import pro.viksit.com.viksit.util.HttpUtil;
import pro.viksit.com.viksit.util.ImageSaver;
import pro.viksit.com.viksit.util.SaveImageAsync;

public class LeaderBoardActivity extends AppCompatActivity implements View.OnClickListener{

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private RelativeLayout headerContainer;
    private AppCompatSpinner dropdown;
    private ArrayList<String> spinnerList;
    private ArrayAdapter<String> spinnerAdapter;
    private Toolbar toolbar;
    private ImageButton goBack;
    private TextView barTitle;
    private ArrayList<StudentRankPOJO> profileList = new ArrayList<>();
    /*private ArrayList<StudentRankPOJO> ranklist = new;*/
    private ImageView first, second, third;
    private Button firstRank, secondRank, thirdRank;
    private TextView firstName, secondName, thirdName;
    private TextView firstXP, secondXP, thirdXP;
    public RecyclerView verticalRecycler;
    private LeaderBoardRecyclerAdapter adapter;
    private SharedPreferences sharedpreferences;

    private Picasso picasso;
    private int screenWidth, screenHeight;
    private double diagonalInches;

    ArrayList<LeaderBoardCourse> leaderBoardCourses;

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

        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        String jsonresponse = sharedpreferences.getString(getResources().getString(R.string.leaderboard),"");
        System.out.println("this is response" + jsonresponse);

        Gson gson = new Gson();
        Type listType = new TypeToken<List<LeaderBoardCourse>>() {
        }.getType();
        leaderBoardCourses = (ArrayList<LeaderBoardCourse>) gson.fromJson(jsonresponse, listType);

        if (leaderBoardCourses != null && leaderBoardCourses.size() > 0) {
            implementActions();
        }
    }

    private void implementActions() {
        //setting dropdown
        spinnerList = new ArrayList<String>();
        for (LeaderBoardCourse course : leaderBoardCourses) {
            spinnerList.add(course.getName());
        }
        spinnerAdapter = new ArrayAdapter<>(this, R.layout.dropdown_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(spinnerAdapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String item = parent.getItemAtPosition(position).toString();
                for (LeaderBoardCourse course : leaderBoardCourses) {
                    if (dropdown.getSelectedItem().toString().equalsIgnoreCase(course.getName())) {
                        profileList = course.getAllStudentRanks();
                        ArrayList<StudentRankPOJO> ranklist = new ArrayList<>(profileList.subList(3, profileList.size()));
                        adapter = new LeaderBoardRecyclerAdapter(ranklist, getBaseContext(), screenWidth, screenHeight, diagonalInches);
                        verticalRecycler.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        appBarLayout.setExpanded(true);
                        setToppers();
                        break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        for (LeaderBoardCourse course : leaderBoardCourses) {
            if (dropdown.getSelectedItem().toString().equalsIgnoreCase(course.getName())) {
                profileList = course.getAllStudentRanks();
                break;
            }
        }
        setToppers();//setting 1st, 2nd, 3rd

        // setting vertical recycler view
        verticalRecycler.setHasFixedSize(true);
        final ArrayList<StudentRankPOJO> ranklist = new ArrayList<>(profileList.subList(3, profileList.size()));

        adapter = new LeaderBoardRecyclerAdapter(ranklist, getBaseContext(), screenWidth, screenHeight, diagonalInches);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        verticalRecycler.setLayoutManager(linearLayoutManager);
        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(adapter);
        verticalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), verticalRecycler, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        System.out.println("Vposition: " + position);
                        /*Intent intent = new Intent(LeaderBoardActivity.this, CheckoutActivity.class);
                        startActivity(intent);*/
                        StudentRankPOJO  student = ranklist.get(position);
                        setDialog(student);
                     }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );

        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        /*if (diagonalInches >= 6.5) {
            ViewGroup.LayoutParams params = first.getLayoutParams();
            params.height = screenHeight / 6;
            params.width = screenHeight / 6;
            first.setLayoutParams(params);

            params = second.getLayoutParams();
            params.height = screenHeight / 7;
            params.width = screenHeight / 7;
            second.setLayoutParams(params);

            params = third.getLayoutParams();
            params.height = screenHeight / 8;
            params.width = screenHeight / 8;
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
        }*/

        // fade toggle appbar on collapse/expand
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                headerContainer.setAlpha(1.0f - Math.abs(verticalOffset / (float)appBarLayout.getTotalScrollRange()));//for scroll fade

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbarLayout.setTitle("Title");
                    isShow = true;
                } else if(isShow) {
                    mCollapsingToolbarLayout.setTitle("titles ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    public void setToppers(){
        String url = profileList.get(0).getImageURL();
        if (!url.startsWith("http")) {
            url += getResources().getString(R.string.resourceserverip) + url;
        }
        int videoindex = url.lastIndexOf("/");
        ImageSaver studentImage = new ImageSaver(this).
                setParentDirectoryName("leaderboard").
                setFileName(new DisplayUtil().getFileNameReplaced(url.substring(videoindex + 1, url.length()))).
                setExternal(ImageSaver.isExternalStorageReadable());
        picasso = Picasso.with(this);
        if(profileList!=null && profileList.get(0)!=null){
            if (studentImage.checkFile()) {
                picasso.load(studentImage.pathFile()).transform(new CircleTransform()).into(first);
            } else {
                picasso.load(url).transform(new CircleTransform()).into(first);//image
                new SaveImageAsync(studentImage).execute(url);
            }
            System.out.println("Kuch likh dia " + getResources().getString(R.string.resourceserverip) + profileList.get(1).getImageURL());
            firstRank.setText(Integer.toString(profileList.get(0).getBatchRank()));
            firstName.setText(profileList.get(0).getName().toLowerCase());
            firstXP.setText(Integer.toString(profileList.get(0).getPoints()) + " xp");
        }

        //setting second
        if(profileList!=null && profileList.get(1)!=null) {
            url = profileList.get(1).getImageURL();
            if (!url.startsWith("http")) {
                url += getResources().getString(R.string.resourceserverip) + url;
            }

            videoindex = url.lastIndexOf("/");
            studentImage = new ImageSaver(this).
                    setParentDirectoryName("leaderboard").
                    setFileName(new DisplayUtil().getFileNameReplaced(url.substring(videoindex + 1, url.length()))).
                    setExternal(ImageSaver.isExternalStorageReadable());

            if (studentImage.checkFile()) {
                picasso.load(studentImage.pathFile()).transform(new CircleTransform()).into(second);
            } else {
                picasso.load(url).transform(new CircleTransform()).into(second);//image
                new SaveImageAsync(studentImage).execute(url);
            }
            secondRank.setText(Integer.toString(profileList.get(1).getBatchRank()));
            secondName.setText(profileList.get(1).getName().toLowerCase());
            secondXP.setText(Integer.toString(profileList.get(1).getPoints()) + " xp");
        }

        //setting third
        if(profileList!=null && profileList.get(2)!=null){
            url = profileList.get(2).getImageURL();
            if (!url.startsWith("http")) {
                url += getResources().getString(R.string.resourceserverip) + url;
            }

            if (studentImage.checkFile()) {
                picasso.load(studentImage.pathFile()).transform(new CircleTransform()).into(third);
            } else {
                picasso.load(url).transform(new CircleTransform()).into(third);//image
                new SaveImageAsync(studentImage).execute(url);
            }
            thirdRank.setText(Integer.toString(profileList.get(2).getBatchRank()));
            thirdName.setText(profileList.get(2).getName().toLowerCase());
            thirdXP.setText(Integer.toString(profileList.get(2).getPoints()) + " xp");
        }
    }

    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;

        float yInches = displaymetrics.heightPixels / displaymetrics.ydpi;
        float xInches = displaymetrics.widthPixels / displaymetrics.xdpi;
        diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == first.getId()) {
            StudentRankPOJO  student = profileList.get(0);
            setDialog(student);
        } else if(v.getId() == second.getId()) {
            StudentRankPOJO  student = profileList.get(1);
            setDialog(student);
        } else if(v.getId() == third.getId()) {
            StudentRankPOJO  student = profileList.get(2);
            setDialog(student);
        }
    }

    public void setDialog(StudentRankPOJO  student){
        final Dialog dialog = new Dialog(getBaseContext());
        dialog.setContentView(R.layout.leaderboard_dialog);
        // set the custom dialog components - text, image and button
        TextView rank = (TextView) findViewById(R.id.tv_rank);
        TextView xp = (TextView) findViewById(R.id.tv_points_xp);
        TextView name = (TextView) findViewById(R.id.tv_name);
        ImageView image = (ImageView) findViewById(R.id.iv_image);
        //RecyclerView badgeRecycler = (RecyclerView) findViewById(R.id.rv_badges);//need to add adapter pass list of badges
        Button challenge = (Button) findViewById(R.id.btn_challenge);

        rank.setText(Integer.toString(student.getBatchRank()));
        xp.setText(Integer.toString(student.getPoints()));
        name.setText(student.getName());
        String url = student.getImageURL();
        int videoindex = url.lastIndexOf("/");
        ImageSaver studentImage = new ImageSaver(getBaseContext()).
                setParentDirectoryName("leaderboard").
                setFileName(new DisplayUtil().getFileNameReplaced(url.substring(videoindex + 1, url.length()))).
                setExternal(ImageSaver.isExternalStorageReadable());
        picasso = Picasso.with(getBaseContext());
        if (studentImage.checkFile()) {
            picasso.load(studentImage.pathFile()).transform(new CircleTransform()).into(image);
        } else {
            picasso.load(url).transform(new CircleTransform()).into(image);//image
            new SaveImageAsync(studentImage).execute(url);
        }

        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
            }
        });
    }
}
