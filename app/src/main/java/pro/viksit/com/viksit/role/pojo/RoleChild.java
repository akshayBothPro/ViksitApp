package pro.viksit.com.viksit.role.pojo;

import java.io.Serializable;

/**
 * Created by Feroz on 20-03-2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class RoleChild implements Serializable{

    private Integer id;
    private String name;
    private Double totalPoints;
    private Double userPoints;
    private Double percentage;
    private Boolean lastItem = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Double getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(Double userPoints) {
        this.userPoints = userPoints;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Boolean getLastItem() {
        return lastItem;
    }

    public void setLastItem(Boolean lastItem) {
        this.lastItem = lastItem;
    }
}
