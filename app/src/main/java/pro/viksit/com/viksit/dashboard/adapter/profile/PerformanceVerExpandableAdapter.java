package pro.viksit.com.viksit.dashboard.adapter.profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.ChildSkill;
import pro.viksit.com.viksit.dashboard.pojo.ParentSkill;

/**
 * Created by Akshay on 02/05/2017.
 */


public class PerformanceVerExpandableAdapter extends ExpandableRecyclerAdapter<ParentSkill,ChildSkill,PerformanceParentHolder,PerformanceChildHolder> {

    List<ParentSkill> parents;
    private LayoutInflater mInflater;
    private Context context;


    public PerformanceVerExpandableAdapter(Context context, @NonNull List<ParentSkill> parents) {
        super(parents);
        this.parents = parents;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public PerformanceParentHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView;
        recipeView = mInflater.inflate(R.layout.performance_parent_item, parentViewGroup, false);

        return new PerformanceParentHolder(recipeView);
    }

    @NonNull
    @Override
    public PerformanceChildHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView;
        ingredientView = mInflater.inflate(R.layout.role_child_view, childViewGroup, false);

        return new PerformanceChildHolder(ingredientView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull PerformanceParentHolder parentViewHolder, int parentPosition, @NonNull ParentSkill parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull PerformanceChildHolder childViewHolder, int parentPosition, int childPosition, @NonNull ChildSkill child) {
        childViewHolder.bind(child,parentPosition, childPosition, parents);
    }

}
