package pro.viksit.com.viksit.role.pojo;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feroz on 20-03-2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class RoleParent implements Parent<RoleChild>,Serializable {

    private Integer id;
    private String name;
    private Double totalPoints;
    private Double userPoints;
    private Double percentage;
    private ArrayList<RoleChild> skills;

    public RoleParent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Double getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(Double userPoints) {
        this.userPoints = userPoints;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public ArrayList<RoleChild> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<RoleChild> roleChildren) {
        this.skills = roleChildren;
    }

    @Override
    public List<RoleChild> getChildList() {
        return skills;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
