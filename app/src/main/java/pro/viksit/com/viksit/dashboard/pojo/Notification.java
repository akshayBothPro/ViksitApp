package pro.viksit.com.viksit.dashboard.pojo;

/**
 * Created by Akshay on 06/04/2017.
 */

public class Notification {
    private String imageUrl;
    private String info;
    private String time;
    private int imageResID;

    public Notification(String imageUrl, String info, String time) {
        this.imageUrl = imageUrl;
        this.info = info;
        this.time = time;
    }

    public Notification(int imageResID, String info, String time) {
        this.imageResID = imageResID;
        this.info = info;
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImageResID() {
        return imageResID;
    }

    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
    }
}
