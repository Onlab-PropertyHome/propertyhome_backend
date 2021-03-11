package hu.bme.aut.onlabpropertyhome.propertymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.bme.aut.onlabpropertyhome.admanager.model.Ad;

import javax.persistence.*;

@Entity
public class Property {
    @Id
    @Column(name = "ad_id")
    private Integer property_id;

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

    public void setProperty_id(Integer id) {
        this.property_id = id;
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

    public Integer getProperty_id() {
        return this.property_id;
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
