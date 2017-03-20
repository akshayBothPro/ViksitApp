package pro.viksit.com.viksit.role.pojo;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feroz on 20-03-2017.
 */

public class RoleParent implements Parent<RoleChild> {

    String title;
    String text;
    int progress;
    ArrayList<RoleChild> roleChildren;

    public RoleParent() {
    }

    public RoleParent(String title, String text, int progress, ArrayList<RoleChild> roleChildren) {
        this.title = title;
        this.text = text;
        this.progress = progress;
        this.roleChildren = roleChildren;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public ArrayList<RoleChild> getRoleChildren() {
        return roleChildren;
    }

    public void setRoleChildren(ArrayList<RoleChild> roleChildren) {
        this.roleChildren = roleChildren;
    }

    @Override
    public List<RoleChild> getChildList() {
        return roleChildren;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
