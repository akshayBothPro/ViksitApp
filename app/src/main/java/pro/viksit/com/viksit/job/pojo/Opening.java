package pro.viksit.com.viksit.job.pojo;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshay on 19/03/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Opening implements Serializable{
    private String id;
    private String type;
    private int imageResID;
    private String jobProfile;
    private String companyName;
    private String expiryDate;
    private ArrayList<String> sectionTypeList;
    /*private URL imageURL;*/

    public Opening(){}

    public Opening(String type, int imageResID, String jobProfile, String companyName, String expiryDate) {
        this.type = type;
        this.imageResID = imageResID;
        this.jobProfile = jobProfile;
        this.companyName = companyName;
        this.expiryDate = expiryDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImageResID() {
        return imageResID;
    }

    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
    }

    public String getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(String jobProfile) {
        this.jobProfile = jobProfile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public ArrayList<Opening> getParticularSectionItems(ArrayList<Opening> list, String type){
        int count = 0;
        ArrayList<Opening> sectionItems = new ArrayList<>();
        for(Opening item: list){
            if(item.getType().equalsIgnoreCase(type)){
                sectionItems.add(item);
            }
        }
        return sectionItems;
    }

    public ArrayList<String> getSectionCount(ArrayList<Opening> list){
        int count = 0;
        ArrayList<String> sectionTypeList = new ArrayList<>();
        String type = "";
        sectionTypeList.add(type);
        for(Opening item : list){
            for(String section : sectionTypeList){
                if(!item.getType().equalsIgnoreCase(section) && section.equalsIgnoreCase("")){
                    sectionTypeList.set(0,item.getType());
                } else if(!item.getType().equalsIgnoreCase(section)){
                    sectionTypeList.add(item.getType());
                }
            }
        }
        return sectionTypeList;
    }

    /*public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }*/
}
