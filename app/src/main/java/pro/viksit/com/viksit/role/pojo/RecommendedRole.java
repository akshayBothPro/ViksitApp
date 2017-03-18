package pro.viksit.com.viksit.role.pojo;

import java.net.URL;

/**
 * Created by Akshay on 18/03/2017.
 */

public class RecommendedRole {
    private String id;
    private int resID;
    /*private URL imageURL;*/

    public RecommendedRole(int resID) {
        this.resID = resID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    /*public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }*/

}
