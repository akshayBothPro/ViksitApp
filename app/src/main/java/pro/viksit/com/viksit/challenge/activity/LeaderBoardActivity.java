package pro.viksit.com.viksit.challenge.activity;

import android.content.Context;
import android.content.Intent;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.challenge.adapter.LeaderBoardRecyclerAdapter;
import pro.viksit.com.viksit.challenge.pojo.LeaderBoardCourse;
import pro.viksit.com.viksit.challenge.pojo.StudentRankPOJO;
import pro.viksit.com.viksit.role.activity.CheckoutActivity;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;
import pro.viksit.com.viksit.util.CircleTransform;

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
    private ArrayList<StudentRankPOJO> profileList;
    private ImageView first, second, third;
    private Button firstRank, secondRank, thirdRank;
    private TextView firstName, secondName, thirdName;
    private TextView firstXP, secondXP, thirdXP;
    private RecyclerView verticalRecycler;
    private LeaderBoardRecyclerAdapter adapter;

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
        //

        new LeaderBoardAsync(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    private void implementActions() {
        //setting dropdown
        spinnerList = new ArrayList<String>();
        spinnerList.add("All Roles");
        for (LeaderBoardCourse course : leaderBoardCourses) {
            spinnerList.add(course.getName());
        }
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

        //String text = spinner.getSelectedItem().toString();
        for(LeaderBoardCourse course : leaderBoardCourses){
            if(dropdown.getSelectedItem().toString().equalsIgnoreCase(course.getName())){
                profileList = course.getAllStudentRanks();
            }
        }

        //setting first
        Picasso.with(this).load(profileList.get(0).getImageURL()).transform(new CircleTransform()).into(first);//image
        firstRank.setText(profileList.get(0).getBatchRank());
        firstName.setText(profileList.get(0).getName());
        firstXP.setText(profileList.get(0).getPoints());

        //setting second
        Picasso.with(this).load(profileList.get(1).getImageURL()).transform(new CircleTransform()).into(second);//image
        secondRank.setText(profileList.get(1).getBatchRank());
        secondName.setText(profileList.get(1).getName());
        secondXP.setText(profileList.get(1).getPoints());

        //setting third
        Picasso.with(this).load(profileList.get(2).getImageURL()).transform(new CircleTransform()).into(third);//image
        thirdRank.setText(profileList.get(2).getBatchRank());
        thirdName.setText(profileList.get(2).getName());
        thirdXP.setText(profileList.get(2).getPoints());

        // setting vertical recycler view
        //profileList = setProfileData(profileList);
        verticalRecycler.setHasFixedSize(true);
        adapter = new LeaderBoardRecyclerAdapter(profileList, getBaseContext(), screenWidth, screenHeight, diagonalInches);
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
                        Intent intent = new Intent(LeaderBoardActivity.this, CheckoutActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );


        if (diagonalInches >= 6.5) {
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
        }
    }

    public ArrayList<LeaderBoardCourse> setProfileData(ArrayList<LeaderBoardCourse> list) {
        list = new ArrayList<>();

        for (LeaderBoardCourse profile : leaderBoardCourses) {
            list.add(profile);
        }
        return list;
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

    public class LeaderBoardAsync extends AsyncTask {
        private Context context;
        private Gson gson = new Gson();


        public LeaderBoardAsync(Context context) {
            this.context = context;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            HttpClient httpclient = new DefaultHttpClient();
            System.out.println(context.getResources().getString(R.string.serverip) + "/courses/user/456/leaderboard");
            HttpGet httppost = new HttpGet(context.getResources().getString(R.string.serverip) + "/courses/user/456/leaderboard");
            int timeoutConnection = 120000;
            final HttpParams httpParameters = httpclient.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            HttpConnectionParams.setSoTimeout(httpParameters, 120000);

            String jsonresponse = "";
            try {

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity httpEntity = response.getEntity();
                jsonresponse = EntityUtils.toString(httpEntity);


            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "null";
            } catch (IOException e) {
                // TODO Auto-generated catch block

                e.printStackTrace();
                return "null";
            } catch (Exception e) {
                e.printStackTrace();
                return "null";
            }
            return jsonresponse;
        }

        protected void onPostExecute(String result) {
            if (result != null && !result.equalsIgnoreCase("")) {
                try {
                    Type listType = new TypeToken<List<LeaderBoardCourse>>() {
                    }.getType();
                    leaderBoardCourses = (ArrayList<LeaderBoardCourse>) gson.fromJson(result, listType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (leaderBoardCourses != null && leaderBoardCourses.size() > 0) {
                    implementActions();
                }
            }


        }
    }
}
