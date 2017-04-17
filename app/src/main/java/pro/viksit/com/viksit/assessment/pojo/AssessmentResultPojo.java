package pro.viksit.com.viksit.assessment.pojo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Feroz on 15-04-2017.
 */

public class AssessmentResultPojo implements Serializable{
    Integer user_id;
    Integer assessment_id;
    HashMap<Integer,QuestionResult> options;
    Long duration;
    public AssessmentResultPojo() {
    }

    public AssessmentResultPojo(Integer user_id, Integer assessment_id, HashMap<Integer, QuestionResult> options) {
        this.user_id = user_id;
        this.assessment_id = assessment_id;
        this.options = options;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getAssessment_id() {
        return assessment_id;
    }

    public void setAssessment_id(Integer assessment_id) {
        this.assessment_id = assessment_id;
    }

    public HashMap<Integer, QuestionResult> getOptions() {
        return options;
    }

    public void setOptions(HashMap<Integer, QuestionResult> options) {
        this.options = options;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
