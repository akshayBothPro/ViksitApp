package pro.viksit.com.viksit.dummy.pojo;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by Akshay on 26/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class NotificationPOJO implements Serializable {

    private Integer id;
    private String message;
    private String status;
    private String imageURL;
    private Timestamp time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
