package pro.viksit.com.viksit.dashboard.adapter.profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pro.viksit.com.viksit.R;

/**
 * Created by Akshay on 02/05/2017.
 */

public class PerformanceVerExpandableAdapter extends ExpandableRecyclerAdapter<RoleParent,RoleChild,PerformanceParentHolder,PerformanceChildHolder> {

    List<RoleParent> roleParents;
    private LayoutInflater mInflater;
    private Context context;
    private int screenWidth,screenHeight;
    private double diagonalInches;


    public PerformanceVerExpandableAdapter(Context context, @NonNull List<RoleParent> roleParents, int screenWidth,int screenHeight, double diagonalInches) {
        super(roleParents);
        this.roleParents = roleParents;
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.diagonalInches = diagonalInches;
    }

    @NonNull
    @Override
    public PerformanceParentHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView;

        recipeView = mInflater.inflate(R.layout.role_parent_view, parentViewGroup, false);

        return new PerformanceParentHolder(recipeView, screenWidth, screenHeight, diagonalInches);
    }

    @NonNull
    @Override
    public PerformanceChildHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView;
        ingredientView = mInflater.inflate(R.layout.role_child_view, childViewGroup, false);

        return new PerformanceChildHolder(ingredientView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull PerformanceParentHolder parentViewHolder, int parentPosition, @NonNull RoleParent parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull PerformanceChildHolder childViewHolder, int parentPosition, int childPosition, @NonNull RoleChild child) {
        childViewHolder.bind(child,parentPosition, childPosition, roleParents);
    }



}
