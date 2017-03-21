package pro.viksit.com.viksit.role.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.RoleChild;
import pro.viksit.com.viksit.role.pojo.RoleParent;

/**
 * Created by Feroz on 20-03-2017.
 */

public class RoleNameViewHolder extends ChildViewHolder {
    private TextView desc;
    private ProgressBar pb;
    private View lastLine;


    public RoleNameViewHolder(@NonNull View itemView) {
        super(itemView);
        desc = (TextView) itemView.findViewById(R.id.title1);
        pb= (ProgressBar) itemView.findViewById(R.id.progress1);
        lastLine = itemView.findViewById(R.id.line_for_last);
    }

    public void bind(@NonNull RoleChild roleChild, int parentposition, int childPosition, List<RoleParent> roleParents) {
        desc.setText(roleChild.getText());
        pb.setProgress(roleChild.getProgress());
        /*if(roleParents.get(parentposition)!= null){
            if(childPosition == roleParents.get(parentposition).getChildList().size()-1){
                System.out.println("child: " + childPosition);
                lastLine.setVisibility(View.GONE);
            }
        }*/

    }
}
