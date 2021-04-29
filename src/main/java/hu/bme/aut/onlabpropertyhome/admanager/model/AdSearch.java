package hu.bme.aut.onlabpropertyhome.admanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.bme.aut.onlabpropertyhome.usermanager.model.User;

import javax.persistence.*;

@Entity
public class AdSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private User user;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String location;
    private String price;
    private Integer roomNumber;
    private Integer size;
    private String type;

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }
}
