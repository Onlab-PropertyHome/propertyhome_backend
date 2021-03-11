package hu.bme.aut.onlabpropertyhome.propertymanager.model;

public class PropertyDTO {
    private Integer roomNumber;
    private String type;
    private String state;
    private Integer size;

    public PropertyDTO() { }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getRoomNumber() {
        return this.roomNumber;
    }
    public String getType() {
        return this.type;
    }
    public String getState() {
        return this.state;
    }
    public Integer getSize() {
        return this.size;
    }
}
