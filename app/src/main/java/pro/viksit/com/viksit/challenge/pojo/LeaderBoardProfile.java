package pro.viksit.com.viksit.challenge.pojo;

/**
 * Created by Akshay on 13/04/2017.
 */

public class LeaderBoardProfile {
    private int id;
    private String imageURL;
    private int imageResId;
    private String name;
    private int rank;
    private int xp;

    public LeaderBoardProfile(String imageURL, String name, int rank, int xp) {
        this.imageURL = imageURL;
        this.name = name;
        this.rank = rank;
        this.xp = xp;
    }

    public LeaderBoardProfile(int imageResId, String name, int rank, int xp) {
        this.imageResId = imageResId;
        this.name = name;
        this.rank = rank;
        this.xp = xp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
