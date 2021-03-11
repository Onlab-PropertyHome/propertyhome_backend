package hu.bme.aut.onlabpropertyhome.admanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import hu.bme.aut.onlabpropertyhome.propertymanager.model.Property;

import javax.persistence.*;

@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ad_id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "ad", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Property property;

    private String picture;
    private String price;
    private String location;
    private String details;

    public Ad() { }

    public void setAd_id(Integer id) {
        this.ad_id = id;
    }
    public void setUser(User u) {
        this.user = u;
    }
    public void setProperty(Property p) {
        this.property = p;
    }
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

    public Integer getAd_id() {
        return this.ad_id;
    }
    public User getUser() {
        return this.user;
    }
    public Property getProperty() {
        return property;
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
