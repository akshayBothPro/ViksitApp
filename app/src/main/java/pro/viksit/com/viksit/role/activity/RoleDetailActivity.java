package pro.viksit.com.viksit.role.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.adapter.RoleDetailAdapter;
import pro.viksit.com.viksit.role.pojo.Role;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class RoleDetailActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    RoleDetailAdapter roleVerticalRecyclerViewAdapter;
    RecyclerView verticalRecycler;
    private List<Role> roles;

    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWidthAndHeight();


        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("Mutual Fund Planner");
        ImageView info = (ImageView) findViewById(R.id.info);
        ImageView back = (ImageView) findViewById(R.id.back);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Info is clicked");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("back is clicked");
                Intent j = new Intent(RoleDetailActivity.this,RoleActivity.class);
                startActivity(j);
            }
        });

        /*final Drawable upArrow = getResources().getDrawable(R.mipmap.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationIcon(upArrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked Toolbar icon");
            }
        });*/
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.theme_color));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.black));
        toolbarTextAppernce();
        verticalRecycler = (RecyclerView) findViewById(R.id.rv_role_vertical);
        roles = new ArrayList<>();

        //role constructor => (int imageResID, String title, String subtitle, int totalItems, int completedItems)
        for (int i = 0; i < 30; i++) {

            Role role = new Role(R.drawable.ic_accessible_black_36dp, "Game Designer" + i, "User Interface Developer", 247, 31);
            roles.add(role);

        }
        roleVerticalRecyclerViewAdapter = new RoleDetailAdapter(roles,RoleDetailActivity.this,screenWidth,screenHeight);

        verticalRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        verticalRecycler.setLayoutManager(linearLayoutManager);

        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(roleVerticalRecyclerViewAdapter);
        verticalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), verticalRecycler, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        System.out.println("Vposition: " + position);
                        Intent k = new Intent(RoleDetailActivity.this,RoleDepthActivity.class);
                        startActivity(k);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


    }

    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(RoleDetailActivity.this,RoleActivity .class);
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
    }

}
