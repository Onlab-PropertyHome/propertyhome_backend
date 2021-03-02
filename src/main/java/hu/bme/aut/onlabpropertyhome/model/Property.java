package hu.bme.aut.onlabpropertyhome.model;

import javax.persistence.*;

@Entity
public class Property {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name = "nev";

    @OneToOne
    @MapsId
    @JoinColumn(name = "ad_id")
    private Ad ad;


    public Property() {

    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
