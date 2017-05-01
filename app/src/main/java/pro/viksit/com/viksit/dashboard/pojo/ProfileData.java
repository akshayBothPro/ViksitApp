package pro.viksit.com.viksit.dashboard.pojo;

/**
 * Created by Akshay on 01/05/2017.
 */

public class ProfileData {
    private String title;
    private String value;
    private String type;

    public ProfileData(String title, String value, String type) {
        this.title = title;
        this.value = value;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
