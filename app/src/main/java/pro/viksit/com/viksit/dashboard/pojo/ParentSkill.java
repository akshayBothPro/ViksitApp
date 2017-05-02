package pro.viksit.com.viksit.dashboard.pojo;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.role.pojo.RoleChild;

/**
 * Created by Akshay on 02/05/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class ParentSkill implements Parent<ChildSkill>,Serializable {

    private Integer id;
    private String name;
    private Double totalPoints = 0.0;
    private Double userPoints = 0.0;
    private Double percentage = 0.0;
    private ArrayList<ChildSkill> childSkills;

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

    public ArrayList<ChildSkill> getChildSkills() {
        return childSkills;
    }

    public void setChildSkills(ArrayList<ChildSkill> childSkills) {
        this.childSkills = childSkills;
    }

    @Override
    public List<ChildSkill> getChildList() {
        return null;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

}
