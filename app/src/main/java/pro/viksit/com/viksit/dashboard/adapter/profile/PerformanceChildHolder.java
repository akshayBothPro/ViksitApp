package pro.viksit.com.viksit.dashboard.adapter.profile;

import android.view.View;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

/**
 * Created by Akshay on 02/05/2017.
 */

public class PerformanceChildHolder extends ChildViewHolder {
    private TextView desc;
    private ProgressBar pb;
    private View lastLine;


    public PerformanceChildHolder(@NonNull View itemView) {
        super(itemView);
        desc = (TextView) itemView.findViewById(R.id.title1);
        pb= (ProgressBar) itemView.findViewById(R.id.progress1);
        lastLine = itemView.findViewById(R.id.rl_ver_line2);
    }

    public void bind(@NonNull RoleChild roleChild, int parentposition, int childPosition, List<RoleParent> roleParents) {
        desc.setText(roleChild.getName());
        pb.setProgress(roleChild.getPercentage().intValue());
        pb.setProgressDrawable(itemView.getResources().getDrawable(R.drawable.role_depth_progress));
        if(roleChild.getLastItem()){
            lastLine.setVisibility(View.GONE);
        } else {
            lastLine.setVisibility(View.VISIBLE);
        }

    }
}
