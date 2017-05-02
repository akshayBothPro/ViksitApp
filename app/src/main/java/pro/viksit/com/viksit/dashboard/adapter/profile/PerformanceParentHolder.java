package pro.viksit.com.viksit.dashboard.adapter.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.ParentSkill;

/**
 * Created by Akshay on 02/05/2017.
 */

public class PerformanceParentHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    @NonNull
    private final ImageButton mArrowExpandImageView;
    private TextView title,desc,score;
    private ProgressBar pb;
    private View vLine, lastLine;
    private Context context;

    public PerformanceParentHolder(@NonNull View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        desc = (TextView) itemView.findViewById(R.id.description);
        score = (TextView) itemView.findViewById(R.id.score);
        pb = (ProgressBar) itemView.findViewById(R.id.progress);
        mArrowExpandImageView = (ImageButton) itemView.findViewById(R.id.expanded);
        vLine =  itemView.findViewById(R.id.tv_role_parent_line);
        lastLine = itemView.findViewById(R.id.bottom_line);
    }

    public void bind(@NonNull ParentSkill parent) {
        title.setText(parent.getName());
        desc.setText( context.getResources().getString(R.string.bullet) + " " + Integer.toString(parent.getChildList().size()) + " subskills");
        score.setText(Double.toString(parent.getUserPoints()) + "/" + Double.toString(parent.getTotalPoints()));
        pb.setProgress(parent.getPercentage().intValue());
        pb.setProgressDrawable(itemView.getResources().getDrawable(R.drawable.role_depth_progress));
        mArrowExpandImageView.setImageResource(R.mipmap.ic_add_circle_outline_black_24dp);

    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
                mArrowExpandImageView.setImageResource(R.mipmap.ic_remove_circle_outline_black_24dp);
                vLine.setVisibility(View.VISIBLE);
                lastLine.setVisibility(View.GONE);

            } else {
                mArrowExpandImageView.setImageResource(R.mipmap.ic_add_circle_outline_black_24dp);
                vLine.setVisibility(View.GONE);
                lastLine.setVisibility(View.VISIBLE);
            }
        }
    }


}
