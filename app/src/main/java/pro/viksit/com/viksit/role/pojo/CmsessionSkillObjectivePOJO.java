package pro.viksit.com.viksit.role.pojo;

/**
 * Created by Feroz on 20-04-2017.
 */

public class CmsessionSkillObjectivePOJO {

    private Integer id;
    private Integer cmessionId;
    private String name;
    private String description;
    private String itemType;
    private Integer itemId;
    private Double accuracy;
    private String status;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCmessionId() {
        return cmessionId;
    }
    public void setCmessionId(Integer cmessionId) {
        this.cmessionId = cmessionId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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

    public Double getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }




}
