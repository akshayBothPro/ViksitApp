package pro.viksit.com.viksit.assessment.pojo;

import java.util.List;

/**
 * Created by Feroz on 17-04-2017.
 */

public class QuestionResult {
    Integer questionId;
    List<Integer> options;
    Long duration;

    public QuestionResult() {
    }

    public QuestionResult(Integer question_id, List<Integer> option_id, Long duration) {
        this.questionId = question_id;
        this.options = option_id;
        this.duration = duration;
    }

    public Integer getQuestion_id() {
        return questionId;
    }

    public void setQuestion_id(Integer question_id) {
        this.questionId = question_id;
    }

    public List<Integer> getOption_id() {
        return options;
    }

    public void setOption_id(List<Integer> option_id) {
        this.options = option_id;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
