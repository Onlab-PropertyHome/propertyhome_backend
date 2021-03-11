package hu.bme.aut.onlabpropertyhome.controller;

import hu.bme.aut.onlabpropertyhome.propertymanager.model.Property;
import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import hu.bme.aut.onlabpropertyhome.admanager.model.Ad;
import hu.bme.aut.onlabpropertyhome.admanager.repository.AdRepository;
import hu.bme.aut.onlabpropertyhome.propertymanager.repository.PropertyRepository;
import hu.bme.aut.onlabpropertyhome.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private PropertyRepository propertyRepository;


    @GetMapping(path="/test")
    public String helloWorld() {
        /*
        User nu = new User();
        nu.setName("Teszt");
        Ad a = new Ad();
        a.setUser(nu);
        a.setDetails("a");
        nu.addAd(a);
        userRepository.save(nu);
        adRepository.save(a);

        Property p = new Property();
        a.setProperty(p);
        p.setAd(a);

        propertyRepository.save(p);
        Ad b = new Ad();
        b.setUser(nu);
        b.setDetails("b");
        userRepository.save(nu);
        adRepository.save(b);*/

        User u = new User();
        Ad ad = new Ad();
        adRepository.save(ad);
        Property property = new Property();
        property.setAd(ad);
        propertyRepository.save(property);
        u.addAd(ad);
        userRepository.save(u);
        ad.setUser(u);
        ad.setProperty(property);
        adRepository.save(ad);
        property.setAd(ad);
        propertyRepository.save(property);
        userRepository.save(u);

        return "Successful Test";
    }
}
