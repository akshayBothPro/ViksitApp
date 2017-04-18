package pro.viksit.com.viksit.challenge.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Akshay on 13/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class LeaderBoardCourse implements Serializable {
    private int id;
    private String name;
    private String description;
    private String imageURL;
    private ArrayList <StudentRankPOJO> allStudentRanks;

    public LeaderBoardCourse(int id, String name, String imageURL, ArrayList<StudentRankPOJO> allStudentRanks) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.allStudentRanks = allStudentRanks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ArrayList<StudentRankPOJO> getAllStudentRanks() {
        return allStudentRanks;
    }

    public void setAllStudentRanks(ArrayList<StudentRankPOJO> allStudentRanks) {
        this.allStudentRanks = allStudentRanks;
    }
}
