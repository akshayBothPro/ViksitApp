package pro.viksit.com.viksit.assessment.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feroz on 14-04-2017.
 */

public class QuestionPOJO implements Serializable{

    private Integer id;
    private Integer orderId;
    private String text;
    private String type;
    private Integer difficultyLevel;
    private Integer durationInSec;
    private String explanation;
    private String comprehensivePassageText;
    private Integer points;
    private List<OptionPOJO> options;
    private List<Integer> answers = new ArrayList<Integer>();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getDurationInSec() {
        return durationInSec;
    }
    public void setDurationInSec(Integer durationInSec) {
        this.durationInSec = durationInSec;
    }

    public String getExplanation() {
        return explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getComprehensivePassageText() {
        return comprehensivePassageText;
    }
    public void setComprehensivePassageText(String comprehensivePassageText) {
        this.comprehensivePassageText = comprehensivePassageText;
    }

    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<OptionPOJO> getOptions() {
        return options;
    }
    public void setOptions(List<OptionPOJO> options) {
        this.options = options;
    }

    public List<Integer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}