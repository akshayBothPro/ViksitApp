package pro.viksit.com.viksit.role.viewholder;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.RoleParent;

/**
 * Created by Feroz on 20-03-2017.
 */

public class RoleSkillViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    @NonNull
    private final ImageButton mArrowExpandImageView;
    private TextView title;
    private TextView desc;
    private ProgressBar pb;
    private View vLine;
    private View lastLine;


    public RoleSkillViewHolder(@NonNull View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        desc = (TextView) itemView.findViewById(R.id.description);
        pb = (ProgressBar) itemView.findViewById(R.id.progress);
        mArrowExpandImageView = (ImageButton) itemView.findViewById(R.id.expanded);
        vLine =  itemView.findViewById(R.id.tv_role_parent_line);
        lastLine = itemView.findViewById(R.id.bottom_line);
    }

    public void bind(@NonNull RoleParent roleParent) {
    title.setText(roleParent.getTitle());
        desc.setText(roleParent.getText());
        pb.setProgress(roleParent.getProgress());
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
