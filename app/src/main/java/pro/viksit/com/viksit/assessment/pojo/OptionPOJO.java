package pro.viksit.com.viksit.assessment.pojo;

import java.io.Serializable;

/**
 * Created by Feroz on 14-04-2017.
 */

public class OptionPOJO implements Serializable {

    private Integer id;
    private String text;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}

