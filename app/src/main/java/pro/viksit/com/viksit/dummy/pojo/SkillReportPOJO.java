package pro.viksit.com.viksit.dummy.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Akshay on 26/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class SkillReportPOJO implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private String itemType;
    private Integer itemId;
    private Double totalPoints = 0.0;
    private Double userPoints = 0.0;
    private Double percentage = 0.0;
    private List<SkillReportPOJO> skills;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public List<SkillReportPOJO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillReportPOJO> skills) {
        this.skills = skills;
    }
}
