package pro.viksit.com.viksit.assessment.pojo;

/**
 * Created by Feroz on 17-04-2017.
 */

public class QuestionResult {
    Integer question_id;
    Integer option_id;
    Long duration;

    public QuestionResult() {
    }

    public QuestionResult(Integer question_id, Integer option_id, Long duration) {
        this.question_id = question_id;
        this.option_id = option_id;
        this.duration = duration;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getOption_id() {
        return option_id;
    }

    public void setOption_id(Integer option_id) {
        this.option_id = option_id;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
