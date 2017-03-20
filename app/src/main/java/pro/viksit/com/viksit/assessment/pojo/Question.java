package pro.viksit.com.viksit.assessment.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Feroz on 20-03-2017.
 */

public class Question implements Serializable {

    private String text;
    private ArrayList<Option> optionArrayList;

    public Question() {
    }

    public Question(String text, ArrayList<Option> optionArrayList) {
        this.text = text;
        this.optionArrayList = optionArrayList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Option> getOptionArrayList() {
        return optionArrayList;
    }

    public void setOptionArrayList(ArrayList<Option> optionArrayList) {
        this.optionArrayList = optionArrayList;
    }
}
