package hu.bme.aut.onlabpropertyhome.admanager.model;

public class AdDTO {
    private String picture;
    private String price;
    private String location;
    private String details;

    public AdDTO() { }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public String getPicture() {
        return this.picture;
    }
    public String getPrice() {
        return this.price;
    }
    public String getLocation() {
        return this.location;
    }
    public String getDetails() {
        return this.details;
    }
}
