package hu.bme.aut.onlabpropertyhome.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private User user;

    @PrimaryKeyJoinColumn
    @OneToOne
    private Property property;

    private String picture;
    private String price;
    private String location;
    private String details;

    public Ad() { }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getId() {
        return this.id;
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
