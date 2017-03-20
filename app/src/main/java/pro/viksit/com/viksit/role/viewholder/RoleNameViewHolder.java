package pro.viksit.com.viksit.role.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.RoleChild;

/**
 * Created by Feroz on 20-03-2017.
 */

public class RoleNameViewHolder extends ChildViewHolder {
    private TextView desc;
    private ProgressBar pb;


    public RoleNameViewHolder(@NonNull View itemView) {
        super(itemView);
        desc = (TextView) itemView.findViewById(R.id.title1);
        pb= (ProgressBar) itemView.findViewById(R.id.progress1);

    }

    public void bind(@NonNull RoleChild roleChild) {
        desc.setText(roleChild.getText());
        pb.setProgress(roleChild.getProgress());
    }
}
