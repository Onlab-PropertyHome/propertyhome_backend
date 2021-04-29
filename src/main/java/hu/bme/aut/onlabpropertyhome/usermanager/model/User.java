package hu.bme.aut.onlabpropertyhome.usermanager.model;

import hu.bme.aut.onlabpropertyhome.admanager.model.Ad;
import hu.bme.aut.onlabpropertyhome.admanager.model.AdSearch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;

    @PrimaryKeyJoinColumn
    @OneToMany(targetEntity= Ad.class, mappedBy="user", fetch = FetchType.LAZY)
    private List<Ad> ads = new ArrayList<>();

    @ElementCollection
    private List<Integer> favAds = new ArrayList<>();

    private String name;

    private String email;
    private String password;
    private String tel;
    private String picture;

    public List<AdSearch> getSearches() {
        return searches;
    }

    public void setSearches(List<AdSearch> searches) {
        this.searches = searches;
    }

    @OneToMany
    @PrimaryKeyJoinColumn
    private List<AdSearch> searches = new ArrayList<AdSearch>();

    public User() { }
    public User(String name, String email, String password, String tel, String picture) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.picture = picture;
    }


    public void setId(Integer id) {
        this.user_id = id;
    }
    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
    public void setFavAds(List<Integer> fav_ads) {
        this.favAds = fav_ads;
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
    public void setPicture(String picture) {
        this.picture = picture;
    }


    public Integer getId() {
        return user_id;
    }
    public List<Ad> getAds() {
        return this.ads;
    }
    public List<Integer> getFavAds() {
        return this.favAds;
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
    public String getPicture() {
        return picture;
    }

    public void addAd(Ad ad) {
        this.ads.add(ad);
    }
    public void clearAds() {
        this.ads.clear();
    }
    public void addAdToFav(Integer id) {
        this.favAds.add(id);
    }
    public void removeAdFromFav(Integer id) {
        this.favAds.remove(id);
    }
    public void clearFavAds() {
        this.favAds.clear();
    }
    public void addAdSearch(AdSearch adsearch){this.searches.add(adsearch);}
    public void removeAdSearch(AdSearch adSearch){this.searches.remove(adSearch);}
}
