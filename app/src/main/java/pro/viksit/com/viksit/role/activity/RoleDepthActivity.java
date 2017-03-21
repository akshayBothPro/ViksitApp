package pro.viksit.com.viksit.role.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.adapter.RoleDepthAdapter;
import pro.viksit.com.viksit.role.adapter.RoleDepthSecondAdapter;
import pro.viksit.com.viksit.role.pojo.RoleChild;
import pro.viksit.com.viksit.role.pojo.RoleParent;

public class RoleDepthActivity extends AppCompatActivity {
    private ArrayList<RoleParent> roleParents;
    private RoleDepthAdapter roleDepthAdapter;

    private int lastExpandedPosition;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_depth);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_role_vertical);


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
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
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


}
