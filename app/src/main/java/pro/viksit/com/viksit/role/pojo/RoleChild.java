package pro.viksit.com.viksit.role.pojo;

import java.io.Serializable;

/**
 * Created by Feroz on 20-03-2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class RoleChild implements Serializable{
    String text;
    int progress;

    public RoleChild() {
    }

    public RoleChild(String text, int progress) {
        this.text = text;
        this.progress = progress;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
