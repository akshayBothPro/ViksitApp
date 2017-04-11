package pro.viksit.com.viksit.role.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.adapter.RoleDepthAdapter;
import pro.viksit.com.viksit.role.pojo.RoleChild;
import pro.viksit.com.viksit.role.pojo.RoleParent;

public class RoleDepthActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView attained;
    private TextView outOf;
    private TextView accuracyPercent;
    private TextView avgPercent;
    private TextView noOfStudentsAttempted;
    private ArrayList<RoleParent> roleParents;
    private RoleDepthAdapter roleDepthAdapter;
    private Button repeatAssessment;
    private Button reviewQuestions;
    private int lastExpandedPosition;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private LinearLayout reportContainer;

    private int screenWidth;
    private int screenHeight;
    private double diagonalInches;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_depth);
        getWidthAndHeight();
        Double d, d1;
        if (diagonalInches>=6.5){
            // 6.5inch device or bigger
            d = new Double(screenWidth / 1.2);
            d1= new Double(screenHeight/1.3);
        }else{
            // smaller device
            d = new Double(screenWidth / 1.2);
            d1= new Double(screenHeight/1.6);
        }
        int screenwidth = d.intValue();;
        int screenheitght = d1.intValue();

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_role_vertical);
        attained = (TextView) findViewById(R.id.tv_attained_score);
        outOf = (TextView) findViewById(R.id.tv_total_score);
        accuracyPercent = (TextView) findViewById(R.id.tv_accuracy_percent);
        avgPercent = (TextView) findViewById(R.id.tv_avg_percent);
        noOfStudentsAttempted = (TextView) findViewById(R.id.tv_no_of_students_attempted);
        repeatAssessment = (Button) findViewById(R.id.btn_repaeat_assessment);
        reviewQuestions = (Button) findViewById(R.id.btn_review_questions);
        reportContainer = (LinearLayout)findViewById(R.id.ll_report_container);




        toolbar.setTitle("Mid-Assesment Report");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white_color));
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(toolbar);
        lastExpandedPosition = -1;
        roleParents = new ArrayList<>();
        for(int i=0;i<15;i++){
            RoleParent roleParent = new RoleParent();
            roleParent.setProgress(40+i);
            roleParent.setText("Risk Management "+i);
            roleParent.setTitle("Risk "+i);
            ArrayList<RoleChild> roleChildren = new ArrayList<>();
            for(int j=0;j<3;j++){
                RoleChild roleChild = new RoleChild();
                roleChild.setText("identification of risk "+j);
                roleChild.setProgress(30+j);
                roleChildren.add(roleChild);

            }
            roleParent.setRoleChildren(roleChildren);
            roleParents.add(roleParent);
        }

        roleDepthAdapter = new RoleDepthAdapter(this,roleParents);
        recyclerView.setAdapter(roleDepthAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        roleDepthAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @UiThread
            @Override
            public void onParentExpanded(int parentPosition) {
                RoleParent expandedRecipe = roleParents.get(parentPosition);

                String toastMsg = "ee "+expandedRecipe.getTitle();
                Toast.makeText(RoleDepthActivity.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
                if (lastExpandedPosition != -1
                        && parentPosition != lastExpandedPosition) {
                    roleDepthAdapter.collapseParent(lastExpandedPosition);
                }
                lastExpandedPosition = parentPosition;
            }

            @UiThread
            @Override
            public void onParentCollapsed(int parentPosition) {
                RoleParent collapsedRecipe = roleParents.get(parentPosition);

                String toastMsg = "cc "+collapsedRecipe.getTitle();
                Toast.makeText(RoleDepthActivity.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();

            }
        });

        //
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        if (diagonalInches>=6.5){
            ViewGroup.LayoutParams params = appBarLayout.getLayoutParams();
            params.height = screenHeight/3;
            appBarLayout.setLayoutParams(params);
        }

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                reportContainer.setAlpha(1.0f - Math.abs(verticalOffset / (float)appBarLayout.getTotalScrollRange()));//for scroll fade

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
        //



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        roleDepthAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roleDepthAdapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(RoleDepthActivity.this,RoleDetailActivity.class);
        startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
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
