package pro.viksit.com.viksit.role.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feroz on 20-04-2017.
 */

public class ModulePOJO implements Comparable<ModulePOJO>{

    private Integer id;
    private String name;
    private String description;
    private String status;
    private String imageURL;
    private Integer orderId;
    //private ArrayList<LessonPOJO> lessons = new ArrayList<LessonPOJO>();
    private List<CmsessionSkillObjectivePOJO> sessions = new ArrayList<CmsessionSkillObjectivePOJO>();
    private ArrayList<String> skillObjectives = new ArrayList<String>();

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

    /*	@XmlAttribute(name = "lessons", required = false)
        public ArrayList<LessonPOJO> getLessons() {
            return lessons;
        }
        public void setLessons(ArrayList<LessonPOJO> lessons) {
            this.lessons = lessons;
        }
        */
    public ArrayList<String> getSkillObjectives() {
        return skillObjectives;
    }
    public void setSkillObjectives(ArrayList<String> skillObjectives) {
        this.skillObjectives = skillObjectives;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<CmsessionSkillObjectivePOJO> getSessions() {
        return sessions;
    }
    public void setSessions(List<CmsessionSkillObjectivePOJO> sessions) {
        this.sessions = sessions;
    }

    @Override
    public int compareTo(ModulePOJO o) {
        return this.orderId -o.orderId;
    }
}
