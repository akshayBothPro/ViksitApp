package pro.viksit.com.viksit.dashboard.pojo;

/**
 * Created by Feroz on 13-04-2017.
 */

public class IstarUserPOJO {

    private Integer istarUserId;
    private String name;
    private String email;
    private String authenticationToken;
    private Boolean isVerified;
    private String role;

    public IstarUserPOJO(){

    }

    public Integer getIstarUserId() {
        return istarUserId;
    }
    public void setIstarUserId(Integer istarUserId) {
        this.istarUserId = istarUserId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }
    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}

