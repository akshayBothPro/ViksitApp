package pro.viksit.com.viksit.dashboard.adapter.profile;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.ChildSkill;
import pro.viksit.com.viksit.dashboard.pojo.ParentSkill;

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

    public void bind(@NonNull ChildSkill child, int parentposition, int childPosition, List<ParentSkill> parent) {
        desc.setText(child.getName());
        pb.setProgress(child.getPercentage().intValue());
        pb.setProgressDrawable(itemView.getResources().getDrawable(R.drawable.role_depth_progress));
        if(child.getLastItem()){
            lastLine.setVisibility(View.GONE);
        } else {
            lastLine.setVisibility(View.VISIBLE);
        }

    }
}
