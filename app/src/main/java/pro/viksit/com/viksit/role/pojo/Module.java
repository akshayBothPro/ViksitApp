package pro.viksit.com.viksit.role.pojo;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Akshay on 19/03/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Module implements Serializable{
    private String id;
    private int imageResID;
    private String moduleTitle;
    private ArrayList<String> labels;
    /*private URL imageURL;*/

    public Module(int imageResID, String moduleTitle, ArrayList<String> labels) {
        this.imageResID = imageResID;
        this.moduleTitle = moduleTitle;
        this.labels = labels;
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

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    /*public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }*/
}
