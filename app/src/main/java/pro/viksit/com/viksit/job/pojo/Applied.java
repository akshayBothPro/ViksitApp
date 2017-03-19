package pro.viksit.com.viksit.job.pojo;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by Akshay on 19/03/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Applied implements Serializable {
    private String id;
    private int imageResID;
    private String title;
    private String subtitle;
    private String status;
    /*private URL imageURL;*/

    public Applied(int imageResID, String title, String subtitle, String status) {
        this.imageResID = imageResID;
        this.title = title;
        this.subtitle = subtitle;
        this.status = status;
    }

    public int getImageResID() {
        return imageResID;
    }

    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }*/
}
