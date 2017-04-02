package pro.viksit.com.viksit.role.pojo;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

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
    private String type;
    private String imageURL;

    public Role(){}

    public Role(int imageResID, String title, String subtitle, int totalItems, int completedItems) {
        this.imageResID = imageResID;
        this.title = title;
        this.subtitle = subtitle;
        this.totalItems = totalItems;
        this.completedItems = completedItems;
    }

    public Role(String imageURL, String title, String subtitle, int totalItems, int completedItems) {
        this.imageURL = imageURL;
        this.title = title;
        this.subtitle = subtitle;
        this.totalItems = totalItems;
        this.completedItems = completedItems;
    }

    public Role(int imageResID, String title, String subtitle, int totalItems, int completedItems, String type) {
        this.imageResID = imageResID;
        this.title = title;
        this.subtitle = subtitle;
        this.totalItems = totalItems;
        this.completedItems = completedItems;
        this.type = type;
    }

    public Role(String imageURL, String title, String subtitle, int totalItems, int completedItems, String type) {
        this.imageURL = imageURL;
        this.title = title;
        this.subtitle = subtitle;
        this.totalItems = totalItems;
        this.completedItems = completedItems;
        this.type = type;
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
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Role> getParticularSectionItems(ArrayList<Role> list, String type){

        ArrayList<Role> sectionItems = new ArrayList<>();
        for(Role item: list){
            if(item.getType().equalsIgnoreCase(type)){
                sectionItems.add(item);
            }
        }
        return sectionItems;
    }

    public ArrayList<String> getSectionCount(ArrayList<Role> list){
        int count = 0;
        ArrayList<String> sectionTypes = new ArrayList<>();
        String type = "";
        sectionTypes.add(type);
        for(Role item : list){
            for(String section : sectionTypes){
                if(!item.getType().equalsIgnoreCase(section) && section.equalsIgnoreCase("")){
                    sectionTypes.set(0,item.getType());
                } else if(!item.getType().equalsIgnoreCase(section)){
                    sectionTypes.add(item.getType());
                }
            }
        }
        return sectionTypes;
    }
}
