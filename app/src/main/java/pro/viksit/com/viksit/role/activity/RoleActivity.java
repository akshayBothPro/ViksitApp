package pro.viksit.com.viksit.role.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.activity.QuestionsActivity;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;
import pro.viksit.com.viksit.home.activity.OTPActivity;
import pro.viksit.com.viksit.role.adapter.RoleHorizontalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.adapter.RoleVerticalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.pojo.RecommendedRole;
import pro.viksit.com.viksit.role.pojo.Role;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class RoleActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = RoleActivity.class.getSimpleName();

    private Toolbar toolbar;
    private RecyclerView horizontalRecycler;
    private RecyclerView horizontalRecycler2;
    private RecyclerView horizontalRecycler3;
    private RoleHorizontalRecyclerViewAdapter roleHorizontalRecyclerViewAdapter;
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
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        verticalRecycler = (RecyclerView) findViewById(R.id.rv_role_vertical);
        horizontalRecycler = (RecyclerView) findViewById(R.id.rv_role_horizontal);
        horizontalRecycler2 = (RecyclerView) findViewById(R.id.rv_role_horizontal2);
        horizontalRecycler3 = (RecyclerView) findViewById(R.id.rv_role_horizontal3);

        setSupportActionBar(toolbar);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,RoleActivity.this,R.id.role);//setting bottom navigation bar
        setRoleData();
        setRecommendedRoleData();
        implementActions();
    }

    private void implementActions(){

        // setting up vertical recycler view
        roleVerticalRecyclerViewAdapter = new RoleVerticalRecyclerViewAdapter(roles,getBaseContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        verticalRecycler.setLayoutManager(linearLayoutManager);
        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(roleVerticalRecyclerViewAdapter);
        verticalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener (getBaseContext(), verticalRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Vposition: " + position);
                        Intent intent = new Intent(RoleActivity.this, QuestionsActivity.class);
                       /* Bundle bundle = new Bundle();
                        bundle.putSerializable("role", roles.get(position));
                        intent.putExtras(bundle);*/
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );


        //setting up horizontal recycler view
        roleHorizontalRecyclerViewAdapter = new RoleHorizontalRecyclerViewAdapter(recommendedRoles);
        RecyclerView.LayoutManager hLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRecycler.setLayoutManager(hLayoutManager);
        horizontalRecycler.setItemAnimator(new DefaultItemAnimator());
        horizontalRecycler.setAdapter(roleHorizontalRecyclerViewAdapter);
        horizontalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener (getBaseContext(), horizontalRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do something
                        System.out.println("Hposition: " + position);
                        Intent j = new Intent(RoleActivity.this,RoleDetailActivity.class);
                        startActivity(j);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );

        //
        setHorizontalRecyclers(horizontalRecycler2);
        setHorizontalRecyclers(horizontalRecycler3);
    }

    private void setHorizontalRecyclers(RecyclerView horizontalRecycle){
        //setting up horizontal recycler view
        roleHorizontalRecyclerViewAdapter = new RoleHorizontalRecyclerViewAdapter(recommendedRoles);
        RecyclerView.LayoutManager hLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRecycle.setLayoutManager(hLayoutManager);
        horizontalRecycle.setItemAnimator(new DefaultItemAnimator());
        horizontalRecycle.setAdapter(roleHorizontalRecyclerViewAdapter);
        horizontalRecycle.addOnItemTouchListener(
                new RecyclerItemClickListener (getBaseContext(), horizontalRecycle ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do something
                        System.out.println("Hposition: " + position);
                        Intent j = new Intent(RoleActivity.this,RoleDetailActivity.class);
                        startActivity(j);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );
    }

    private void setRoleData(){
        roles = new ArrayList<>();
        Role role;
        for(int i = 0 ; i < 8 ; i++) {
            //role constructor => (int imageResID, String title, String subtitle, int totalItems, int completedItems)
            role = new Role(R.drawable.ic_7, "Game Designer", "User Interface Developer", 247, 131);
            roles.add(role);
            role = new Role(R.drawable.ic_8, "Game Designer", "User Interface Developer", 247, 231);
            roles.add(role);
            role = new Role(R.drawable.ic_9, "Business Analyst", "Mutual Fund Planner", 247, 91);
            roles.add(role);
            role = new Role(R.drawable.ic_10, "Game Designer", "User Interface Developer", 247, 31);
            roles.add(role);
            role = new Role(R.drawable.ic_11, "Game Designer", "User Interface Developer", 247, 39);
            roles.add(role);
            role = new Role(R.drawable.ic_12, "Business Analyst", "Mutual Fund Planner", 247, 51);
            roles.add(role);
        }
    }

    private void setRecommendedRoleData(){
        recommendedRoles = new ArrayList<>();
        RecommendedRole recommendedRole;
        //recommendedRole constructor => (int resID)
        for(int i = 0 ; i < 8 ; i++) {
            recommendedRole = new RecommendedRole(R.drawable.ic_1, "Stock Broker");
            recommendedRoles.add(recommendedRole);
            recommendedRole = new RecommendedRole(R.drawable.ic_2, "Financial Analyst");
            recommendedRoles.add(recommendedRole);
            recommendedRole = new RecommendedRole(R.drawable.ic_3, "Investment Banker");
            recommendedRoles.add(recommendedRole);
            recommendedRole = new RecommendedRole(R.drawable.ic_4, "Financial Analyst");
            recommendedRoles.add(recommendedRole);
            recommendedRole = new RecommendedRole(R.drawable.ic_5, "Stock Broker");
            recommendedRoles.add(recommendedRole);
            recommendedRole = new RecommendedRole(R.drawable.ic_6, "Financial Banker");
            recommendedRoles.add(recommendedRole);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
