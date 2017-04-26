package pro.viksit.com.viksit.dummy.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshay on 26/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class CoursePOJO implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private String category;
    private String imageURL;
    private String status;
    private Integer rank;
    private Double userPoints = 0.0;
    private Double totalPoints = 0.0;
    private Double progress = 0.0;
    private List<ModulePOJO> modules = new ArrayList<ModulePOJO>();
    private List<SkillReportPOJO> skillObjectives = new ArrayList<SkillReportPOJO>();

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(Double userPoints) {
        this.userPoints = userPoints;
    }

    public Double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public List<ModulePOJO> getModules() {
        return modules;
    }

    public void setModules(List<ModulePOJO> modules) {
        this.modules = modules;
    }

    public List<SkillReportPOJO> getSkillObjectives() {
        return skillObjectives;
    }

    public void setSkillObjectives(List<SkillReportPOJO> skillObjectives) {
        this.skillObjectives = skillObjectives;
    }
}
