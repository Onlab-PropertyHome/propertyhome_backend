package hu.bme.aut.onlabpropertyhome.usermanager.model;

public class UserDetails {
    private String name;
    private String picture;
    private String tel;

    public UserDetails() { }
    public UserDetails(String name, String picture, String tel) {
        this.name = name;
        this.picture = picture;
        this.tel = tel;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return this.name;
    }
    public String getPicture() {
        return this.picture;
    }
    public String getTel() {
        return this.tel;
    }
}
