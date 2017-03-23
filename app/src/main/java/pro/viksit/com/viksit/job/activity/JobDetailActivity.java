package pro.viksit.com.viksit.job.activity;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.home.activity.BatchCodeActivity;
import pro.viksit.com.viksit.role.adapter.RoleHorizontalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.pojo.RecommendedRole;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class JobDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Button backNavigation;
    private ImageView image;
    private Button attempt;
    private TextView subtitle;
    private TextView description;
    private LinearLayout pointContainer;
    private TextView expiryDate;
    private TextView salary;
    private TextView seemore;
    private RecyclerView hrecyclerView;
    private ArrayList<RecommendedRole> recommendedRoles;
    private RoleHorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        backNavigation = (Button) findViewById(R.id.btn_back_navigation);
        image = (ImageView) findViewById(R.id.iv_job_detail_image);
        attempt = (Button) findViewById(R.id.btn_attempt22);
        subtitle = (TextView) findViewById(R.id.tv_job_detail_subtitle);
        description = (TextView) findViewById(R.id.tv_job_detail_description);
        pointContainer = (LinearLayout) findViewById(R.id.ll_points_container);
        expiryDate = (TextView) findViewById(R.id.tv_job_detail_expiry_date);
        salary = (TextView) findViewById(R.id.tv_job_detail_salary);
        seemore = (TextView) findViewById(R.id.tv_job_detail_seemore);
        hrecyclerView = (RecyclerView) findViewById(R.id.rv_job_detail_horizontal);



        setSupportActionBar(toolbar);
        setRecommendedRoleData();
        setPoints();
        setHorizontalRecyclers(hrecyclerView);

    }

    private void setPoints(){
        TextView htext;
        for(int i = 0 ; i < 5 ; i++ ) {
            htext =new TextView(this);
            /* htext.setText(Html.fromHtml(getResources().getString(R.string.k)));*/
            htext.setText("\u2022 iusto odio dignissimos ducimus qui blanditiis praesentium");
            htext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            pointContainer.addView(htext);
        }
    }

    private void setHorizontalRecyclers(RecyclerView horizontalRecycle){
        //setting up horizontal recycler view
        horizontalRecyclerViewAdapter = new RoleHorizontalRecyclerViewAdapter(recommendedRoles);
        RecyclerView.LayoutManager hLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRecycle.setLayoutManager(hLayoutManager);
        horizontalRecycle.setItemAnimator(new DefaultItemAnimator());
        horizontalRecycle.setAdapter(horizontalRecyclerViewAdapter);
        horizontalRecycle.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), horizontalRecycle ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do something
                        System.out.println("Hposition: " + position);
                        Intent j = new Intent(JobDetailActivity.this,BatchCodeActivity.class);
                        startActivity(j);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );
    }

    private void setRecommendedRoleData(){
        recommendedRoles = new ArrayList<>();
        RecommendedRole recommendedRole;
        //recommendedRole constructor => (int resID, String title)
        recommendedRole = new RecommendedRole(R.mipmap.ic_adjust_black_24dp, "Stock Broker");
        recommendedRoles.add(recommendedRole);
        recommendedRole = new RecommendedRole(R.mipmap.ic_assignment_black_24dp, "Financial Analyst");
        recommendedRoles.add(recommendedRole);
        recommendedRole = new RecommendedRole(R.mipmap.ic_event_note_black_24dp, "Investment Banker");
        recommendedRoles.add(recommendedRole);
        recommendedRole = new RecommendedRole(R.mipmap.ic_monetization_on_black_24dp, "Financial Analyst");
        recommendedRoles.add(recommendedRole);
        recommendedRole = new RecommendedRole(R.mipmap.ic_notifications_active_black_24dp, "Stock Broker");
        recommendedRoles.add(recommendedRole);
        recommendedRole = new RecommendedRole(R.mipmap.ic_work_black_24dp, "Financial Banker");
        recommendedRoles.add(recommendedRole);
    }
}
