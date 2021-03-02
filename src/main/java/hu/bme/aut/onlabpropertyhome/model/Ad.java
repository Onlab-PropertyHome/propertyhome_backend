package hu.bme.aut.onlabpropertyhome.model;

import javax.persistence.*;

@Entity
public class Ad {


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
@Column
private static String a = "nemertem";

    @ManyToOne()
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;


    @PrimaryKeyJoinColumn
    @OneToOne
    private Property property;







public Ad(){}

public void setUser(User u){this.user = u;}
public void setProperty(Property p){this.property = p;}


}
