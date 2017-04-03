package pro.viksit.com.viksit.role.pojo;

import java.io.Serializable;

/**
 * Created by Akshay on 03/04/2017.
 */
@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Payment implements Serializable {
    private String id;
    private Boolean check;
    private String name;

    public Payment(Boolean check, String name) {
        this.check = check;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
