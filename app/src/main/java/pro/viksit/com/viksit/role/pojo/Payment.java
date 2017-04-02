package pro.viksit.com.viksit.role.pojo;

/**
 * Created by Akshay on 03/04/2017.
 */

public class Payment {
    private Boolean check;
    private String name;

    public Payment(Boolean check, String name) {
        this.check = check;
        this.name = name;
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
