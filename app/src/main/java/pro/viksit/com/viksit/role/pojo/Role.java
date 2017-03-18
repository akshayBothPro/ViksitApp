package pro.viksit.com.viksit.role.pojo;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by Akshay on 18/03/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Role implements Serializable {
    private String id;
    private int imageResID;
    private String title;
    private String subtitle;
    private int totalItems;
    private int completedItems;
    /*private URL imageURL;*/

    public Role(int imageResID, String title, String subtitle, int totalItems, int completedItems) {
        this.imageResID = imageResID;
        this.title = title;
        this.subtitle = subtitle;
        this.totalItems = totalItems;
        this.completedItems = completedItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getCompletedItems() {
        return completedItems;
    }

    public void setCompletedItems(int completedItems) {
        this.completedItems = completedItems;
    }

    // in case we have image URL ,we use this
    /*public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }*/
}
