package pro.viksit.com.viksit.role.pojo;

/**
 * Created by Feroz on 20-03-2017.
 */

public class RoleChild {
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
