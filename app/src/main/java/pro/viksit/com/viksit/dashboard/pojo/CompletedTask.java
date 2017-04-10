package pro.viksit.com.viksit.dashboard.pojo;

/**
 * Created by Akshay on 10/04/2017.
 */

public class CompletedTask {
    private String id;
    private String type;
    private String description;
    private String time;
    private Boolean completed;

    public CompletedTask(String type, String description, String time, Boolean completed) {
        this.type = type;
        this.description = description;
        this.time = time;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
