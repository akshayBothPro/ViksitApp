package pro.viksit.com.viksit.role.activity;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.activity.QuestionsActivity;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;
import pro.viksit.com.viksit.role.adapter.ModuleHorizontalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.adapter.ModuleVerticalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.adapter.RoleHorizontalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.adapter.RoleVerticalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.pojo.RecommendedRole;
import pro.viksit.com.viksit.role.pojo.Role;
import pro.viksit.com.viksit.role.pojo.SectionedRole;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class ModuleActivity extends AppCompatActivity {
    private static final String TAG = ModuleActivity.class.getSimpleName();

    private Toolbar toolbar;
    private RecyclerView horizontalRecycler;
    private RecyclerView verticalRecycler;
    private ModuleVerticalRecyclerViewAdapter moduleVerticalRecyclerViewAdapter;
    private RoleHorizontalRecyclerViewAdapter roleHorizontalRecyclerViewAdapter;
    private BottomNavigationView bottomNavigationView;

    private ArrayList<Role> roles;
    private ArrayList<RecommendedRole> recommendedRoles;
    private ArrayList <SectionedRole> allSampleData;

    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        getWidthAndHeight();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        verticalRecycler = (RecyclerView) findViewById(R.id.rv_module_vertical);
        horizontalRecycler = (RecyclerView) findViewById(R.id.rv_module_horizontal);

        setSupportActionBar(toolbar);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,ModuleActivity.this,R.id.role);//setting bottom navigation bar
        setRoleData();
        createDummyData();

        implementActions();
    }

    private void implementActions(){

        // setting up vertical recycler view
        moduleVerticalRecyclerViewAdapter = new ModuleVerticalRecyclerViewAdapter(roles,getBaseContext(), screenWidth, screenHeight);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        verticalRecycler.setLayoutManager(linearLayoutManager);
        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(moduleVerticalRecyclerViewAdapter);
        verticalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), verticalRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Vposition: " + position);
                        Intent intent = new Intent(ModuleActivity.this, QuestionsActivity.class);
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

        horizontalRecycler.setHasFixedSize(true);
        ModuleHorizontalRecyclerViewAdapter adapter = new ModuleHorizontalRecyclerViewAdapter(this, allSampleData);
        horizontalRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        horizontalRecycler.setAdapter(adapter);

    }

    private void setRoleData(){
        roles = new ArrayList<>();
        Role role;
        for(int i = 0 ; i < 8 ; i++) {
            //role constructor => (int imageResID, String title, String subtitle, int totalItems, int completedItems)
            role = new Role(R.drawable.ic_7, "Game Designer", "User Interface Developer", 247, 131,"Recommended");
            roles.add(role);
            role = new Role(R.drawable.ic_8, "Game Designer", "User Interface Developer", 247, 231,"Finance");
            roles.add(role);
            role = new Role(R.drawable.ic_9, "Business Analyst", "Mutual Fund Planner", 247, 91,"Sales and Marketing");
            roles.add(role);
            role = new Role(R.drawable.ic_10, "Game Designer", "User Interface Developer", 247, 31,"Recommended");
            roles.add(role);
            role = new Role(R.drawable.ic_11, "Game Designer", "User Interface Developer", 247, 39,"Finance");
            roles.add(role);
            role = new Role(R.drawable.ic_12, "Business Analyst", "Mutual Fund Planner", 247, 51,"Sales and Marketing");
            roles.add(role);
        }
    }

    public void createDummyData() {
        ArrayList<Role> recommendedList = new Role().getParticularSectionItems(roles,"Recommended");
        ArrayList<Role> financeList = new Role().getParticularSectionItems(roles,"Finance");
        ArrayList<Role> salesList = new Role().getParticularSectionItems(roles,"Sales and Marketing");
        allSampleData = new ArrayList<>();

        SectionedRole dm = new SectionedRole("Recommended",recommendedList);
        allSampleData.add(dm);

        dm = new SectionedRole("Finance",financeList);
        allSampleData.add(dm);

        dm = new SectionedRole("Sales and Marketing",salesList);
        allSampleData.add(dm);

    }

    /*@Override
    public void onClick(View v) {

    }*/

    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }
}
