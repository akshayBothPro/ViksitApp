package pro.viksit.com.viksit.dashboard.pojo;

import java.util.ArrayList;

/**
 * Created by Akshay on 01/05/2017.
 */

public class ProfileItem {
    private String header;
    private ArrayList<ProfileData> allItemsInSection;

    public ProfileItem(String header, ArrayList<ProfileData> allItemsInSection) {
        this.header = header;
        this.allItemsInSection = allItemsInSection;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ArrayList<ProfileData> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<ProfileData> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }
}
