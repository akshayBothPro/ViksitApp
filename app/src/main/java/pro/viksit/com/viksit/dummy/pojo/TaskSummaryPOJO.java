package pro.viksit.com.viksit.dummy.pojo;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by Akshay on 26/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class TaskSummaryPOJO implements Serializable {

    private Integer id;
    private String header;
    private String title;
    private String description;
    private Integer numberOfQuestions;
    private Integer itemPoints;
    private Integer itemCoins;
    private String itemType;
    private Integer itemId;
    private Integer duration;
    private String imageURL;
    private String status;
    private Timestamp date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Integer getItemPoints() {
        return itemPoints;
    }

    public void setItemPoints(Integer itemPoints) {
        this.itemPoints = itemPoints;
    }

    public Integer getItemCoins() {
        return itemCoins;
    }

    public void setItemCoins(Integer itemCoins) {
        this.itemCoins = itemCoins;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
