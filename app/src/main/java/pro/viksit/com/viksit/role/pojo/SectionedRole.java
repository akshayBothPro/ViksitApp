package pro.viksit.com.viksit.role.pojo;

import java.util.ArrayList;

/**
 * Created by Akshay on 31/03/2017.
 */

public class SectionedRole {

    private String headerTitle;
    private ArrayList<Role> allItemsInSection;


    public SectionedRole() {
    }

    public SectionedRole(String headerTitle, ArrayList<Role> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Role> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<Role> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }


}
