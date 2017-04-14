package pro.viksit.com.viksit.assessment.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feroz on 14-04-2017.
 */

public class AssessmentPOJO {

    private Integer id;
    private String type;
    private String name;
    private String category;
    private Integer durationInMinutes;
    private List<QuestionPOJO> questions = new ArrayList<QuestionPOJO>();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }
    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public List<QuestionPOJO> getQuestions() {
        return questions;
    }
    public void setQuestions(List<QuestionPOJO> questions) {
        this.questions = questions;
    }
}
