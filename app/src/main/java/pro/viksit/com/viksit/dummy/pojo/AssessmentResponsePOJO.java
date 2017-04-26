package pro.viksit.com.viksit.dummy.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshay on 26/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class AssessmentResponsePOJO implements Serializable {

    private Integer id;
    private List<QuestionResponsePOJO> response = new ArrayList<QuestionResponsePOJO>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<QuestionResponsePOJO> getResponse() {
        return response;
    }

    public void setResponse(List<QuestionResponsePOJO> response) {
        this.response = response;
    }
}
