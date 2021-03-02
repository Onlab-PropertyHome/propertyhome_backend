package hu.bme.aut.onlabpropertyhome.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;

    @PrimaryKeyJoinColumn
    @OneToMany(targetEntity=Ad.class, mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ad> ads = new ArrayList<>();

    private String name;
    private String email;
    private String password;
    private String tel;

    public User() { }

    public void setId(Integer id) {
        this.user_id = id;
    }
    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) { this.password = password; }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getId() {
        return user_id;
    }
    public List<Ad> getAds() {
        return this.ads;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() { return password; }
    public String getTel() {
        return this.tel;
    }

    public void addAd(Ad ad) {
        this.ads.add(ad);
    }
    public void clearAds() {
        this.ads.clear();
    }
}
