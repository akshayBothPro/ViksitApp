package pro.viksit.com.viksit.dummy.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Akshay on 26/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class QuestionResponsePOJO implements Serializable {

    private Integer questionId;
    private List<Integer> options;
    private Integer duration;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<Integer> getOptions() {
        return options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
