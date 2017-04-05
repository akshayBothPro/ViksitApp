package pro.viksit.com.viksit.dashboard.pojo;

import java.io.Serializable;

/**
 * Created by Feroz on 28-03-2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class DashboardCard implements Serializable{
    private int id;
    private String header;
    private String title;
    private String description;
    private int nosofQuestion;
    private int experience;
    private int coins;
    private String type;
    private int duration;
    private String image_url;
    private int personalRank;
    private int personalExperience;
    private int challengerExperience;
    private String challengerImage_url;
    private String challengerName;
    private int challengerRank;


    public DashboardCard() {
    }

    //game type / video type / presentation type
    public DashboardCard(int id, String header, String title, String description, String image_url,String type) {
        this.id = id;
        this.header = header;
        this.title = title;
        this.description = description;
        this.image_url = image_url;
        this.type = type;
    }


    // challenge type
    public DashboardCard(int id, String header, String title, String description, String image_url, int nosofQuestion, int experience, int coins,
                         int personalRank, int personalExperience, int challengerExperience, int challengerRank){
        this.id = id;
        this.header = header;
        this.title = title;
        this.description = description;
        this.image_url = image_url;
        this.nosofQuestion = nosofQuestion;
        this.experience = experience;
        this.coins = coins;
        this.personalRank = personalRank;
        this.personalExperience = personalExperience;
        this.challengerExperience = challengerExperience;
        this.challengerRank = challengerRank;

    }

    //assessment type
    public DashboardCard(int id, String header, String title, String description, String image_url, int nosofQuestion, int experience, int coins, String type) {
        this.id = id;
        this.header = header;
        this.title = title;
        this.description = description;
        this.nosofQuestion = nosofQuestion;
        this.experience = experience;
        this.coins = coins;
        this.type = type;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonalExperience() {
        return personalExperience;
    }

    public void setPersonalExperience(int personalExperience) {
        this.personalExperience = personalExperience;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPersonalRank() {
        return personalRank;
    }

    public void setPersonalRank(int rank) {
        this.personalRank = rank;
    }



    public int getChallengerExperience() {
        return challengerExperience;
    }

    public void setChallengerExperience(int challengerExperience) {
        this.challengerExperience = challengerExperience;
    }

    public String getChallengerName() {
        return challengerName;
    }

    public void setChallengerName(String challengerName) {
        this.challengerName = challengerName;
    }

    public int getChallengerRank() {
        return challengerRank;
    }

    public void setChallengerRank(int challengerRank) {
        this.challengerRank = challengerRank;
    }

    public String getChallengerImage_url() {
        return challengerImage_url;
    }

    public void setChallengerImage_url(String challengerImage_url) {
        this.challengerImage_url = challengerImage_url;
    }
}
