package hu.bme.aut.onlabpropertyhome.propertymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.bme.aut.onlabpropertyhome.admanager.model.Ad;

import javax.persistence.*;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ad_id")
    @JsonIgnore
    private Ad ad;

    private Integer roomNumber;
    private String type;
    private String state;
    private Integer size;

    public Property() { }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setAd(Ad ad) {
        this.ad = ad;
    }
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

    public Integer getId() {
        return this.id;
    }
    public Ad getAd() {
        return this.ad;
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
