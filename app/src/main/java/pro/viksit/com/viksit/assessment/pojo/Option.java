package pro.viksit.com.viksit.assessment.pojo;

import java.io.Serializable;

/**
 * Created by Feroz on 20-03-2017.
 */

public class Option implements Serializable {
    private String text;

    public Option() {
    }

    public Option(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
