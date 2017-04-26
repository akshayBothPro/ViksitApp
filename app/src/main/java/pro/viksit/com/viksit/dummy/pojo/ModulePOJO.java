package pro.viksit.com.viksit.dummy.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshay on 26/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class ModulePOJO implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private String status;
    private String imageURL;
    private Integer orderId;
    private List<CmsessionPOJO> lessons = new ArrayList<CmsessionPOJO>();
    private List<String> skillObjectives = new ArrayList<String>();

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<CmsessionPOJO> getLessons() {
        return lessons;
    }

    public void setLessons(List<CmsessionPOJO> lessons) {
        this.lessons = lessons;
    }

    public List<String> getSkillObjectives() {
        return skillObjectives;
    }

    public void setSkillObjectives(List<String> skillObjectives) {
        this.skillObjectives = skillObjectives;
    }
}
