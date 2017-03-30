package pro.viksit.com.viksit.dashboard.pojo;

import java.io.Serializable;

/**
 * Created by Feroz on 28-03-2017.
 */

public class DashboardCard implements Serializable{
    private String header;
    private String title;
    private String description;
    private int nosofQuestion;
    private int experience;
    private int coins;
    private String type;
    private String image_url;
    public DashboardCard() {
    }

    public DashboardCard(String header, String title, String description, String image_url, int nosofQuestion, int experience, int coins, String type) {
        this.header = header;
        this.title = title;
        this.description = description;
        this.nosofQuestion = nosofQuestion;
        this.experience = experience;
        this.coins = coins;
        this.type = type;
        this.image_url = image_url;
    }

    public DashboardCard(String header, String title, String description, String image_url,String type) {
        this.header = header;
        this.title = title;
        this.description = description;
        this.image_url = image_url;
        this.type = type;
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

    public int getNosofQuestion() {
        return nosofQuestion;
    }

    public void setNosofQuestion(int nosofQuestion) {
        this.nosofQuestion = nosofQuestion;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}
