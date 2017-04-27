package pro.viksit.com.viksit.dummy.pojo;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by Akshay on 26/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class DailyTaskPOJO implements Serializable {

    private Integer id;
    private String name;
    private String status;
    private String startDate;
    private String endDate;
    private String completedAt;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }
}
