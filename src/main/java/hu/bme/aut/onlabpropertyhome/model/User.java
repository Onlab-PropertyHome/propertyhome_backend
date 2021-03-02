package hu.bme.aut.onlabpropertyhome.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_entity")
public class User {
    public Integer getId() {
        return user_id;
    }

    /*public void setId(Integer id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public void addAd(Ad a){this.ads.add(a);}

    public User() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id")
    private Integer user_id;

    @PrimaryKeyJoinColumn
    @OneToMany(targetEntity=Ad.class, mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ad> ads = new ArrayList<>();


    private String name;

    private String email;

    private String password;
}
