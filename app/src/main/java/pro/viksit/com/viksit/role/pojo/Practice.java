package pro.viksit.com.viksit.role.pojo;

/**
 * Created by Akshay on 17/04/2017.
 */

public class Practice {

    private int id;
    private String text;
    private int max;
    private int progress;

    public Practice(String text, int max, int progress) {
        this.text = text;
        this.max = max;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
