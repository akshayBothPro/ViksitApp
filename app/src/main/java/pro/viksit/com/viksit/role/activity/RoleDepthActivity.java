package pro.viksit.com.viksit.role.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.adapter.RoleDepthAdapter;
import pro.viksit.com.viksit.role.pojo.RoleChild;
import pro.viksit.com.viksit.role.pojo.RoleParent;

public class RoleDepthActivity extends AppCompatActivity {
    private ArrayList<RoleParent> roleParents;
    private RoleDepthAdapter roleDepthAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_depth);
        roleParents = new ArrayList<>();
        for(int i=0;i<2;i++){
            RoleParent roleParent = new RoleParent();
            roleParent.setProgress(40+i);
            roleParent.setText("Risk Management "+i);
            roleParent.setTitle("Risk "+i);
            ArrayList<RoleChild> roleChildren = new ArrayList<>();
            for(int j=0;j<2;j++){
                RoleChild roleChild = new RoleChild();
                roleChild.setText("identification of risk "+j);
                roleChild.setProgress(30+j);
                roleChildren.add(roleChild);
            }
            roleParent.setRoleChildren(roleChildren);
            roleParents.add(roleParent);
        }
        roleDepthAdapter = new RoleDepthAdapter(this,roleParents);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_role_vertical);
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

        recyclerView.setAdapter(roleDepthAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
