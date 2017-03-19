package pro.viksit.com.viksit.role.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;
import pro.viksit.com.viksit.role.adapter.RoleHorizontalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.adapter.RoleVerticalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.pojo.RecommendedRole;
import pro.viksit.com.viksit.role.pojo.Role;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class RoleActivity extends AppCompatActivity {
    private static final String TAG = RoleActivity.class.getSimpleName();

    private Toolbar toolbar;
    private RecyclerView horizontalRecycler;
    private RoleHorizontalRecyclerViewAdapter roleHorizontalRecyclerViewAdapter;
    private TextView seeMore;
    private RecyclerView verticalRecycler;
    private RoleVerticalRecyclerViewAdapter roleVerticalRecyclerViewAdapter;
    private BottomNavigationView bottomNavigationView;

    private List<Role> roles;
    private List<RecommendedRole> recommendedRoles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        verticalRecycler = (RecyclerView) findViewById(R.id.rv_role_vertical);
        seeMore = (TextView) findViewById(R.id.tv_see_more);
        horizontalRecycler = (RecyclerView) findViewById(R.id.rv_role_horizontal);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        setSupportActionBar(toolbar);
        setRoleData();
        setRecommendedRoleData();
        implementActions();
        new BottomBarUtil().setupBottomBar(bottomNavigationView,RoleActivity.this,R.id.role);//setting bottom navigation bar
    }

    private void implementActions(){

        // setting up vertical recycler view
        roleVerticalRecyclerViewAdapter = new RoleVerticalRecyclerViewAdapter(roles);
        RecyclerView.LayoutManager vLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, true);
        verticalRecycler.setLayoutManager(vLayoutManager);
        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(roleVerticalRecyclerViewAdapter);
        verticalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener (getBaseContext(), verticalRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Vposition: " + position);
                        Intent intent = new Intent(RoleActivity.this, ModuleViewActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("role", roles.get(position));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do soemthing
            }
        });

        //setting up horizontal recycler view
        roleHorizontalRecyclerViewAdapter = new RoleHorizontalRecyclerViewAdapter(recommendedRoles);
        RecyclerView.LayoutManager hLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, true);
        horizontalRecycler.setLayoutManager(hLayoutManager);
        horizontalRecycler.setItemAnimator(new DefaultItemAnimator());
        horizontalRecycler.setAdapter(roleHorizontalRecyclerViewAdapter);
        horizontalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener (getBaseContext(), horizontalRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do something
                        System.out.println("Hposition: " + position);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );
    }
    private void setRoleData(){
        roles = new ArrayList<>();

        //role constructor => (int imageResID, String title, String subtitle, int totalItems, int completedItems)
        Role role = new Role(R.drawable.ic_ab,"Game Designer","User Interface Developer",247,131);
        roles.add(role);
        role = new Role(R.drawable.ic_ac,"Game Designer","User Interface Developer",247,231);
        roles.add(role);
        role = new Role(R.drawable.ic_af,"Business Analyst","Mutual Fund Planner",247,91);
        roles.add(role);
        role = new Role(R.drawable.ic_ad,"Game Designer","User Interface Developer",247,31);
        roles.add(role);
        role = new Role(R.drawable.ic_ae,"Game Designer","User Interface Developer",247,39);
        roles.add(role);
        role = new Role(R.drawable.ic_ag,"Business Analyst","Mutual Fund Planner",247,51);
        roles.add(role);

    }

    private void setRecommendedRoleData(){
        recommendedRoles = new ArrayList<>();

        //recommendedRole constructor => (int resID)
        RecommendedRole recommendedRole = new RecommendedRole(R.drawable.ic_ag);
        recommendedRoles.add(recommendedRole);
        recommendedRole = new RecommendedRole(R.drawable.ic_ai);
        recommendedRoles.add(recommendedRole);
        recommendedRole = new RecommendedRole(R.drawable.ic_aj);
        recommendedRoles.add(recommendedRole);
        recommendedRole = new RecommendedRole(R.drawable.ic_ak);
        recommendedRoles.add(recommendedRole);
        recommendedRole = new RecommendedRole(R.drawable.ic_an);
        recommendedRoles.add(recommendedRole);
        recommendedRole = new RecommendedRole(R.drawable.ic_ap);
        recommendedRoles.add(recommendedRole);
    }
}
