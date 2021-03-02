package hu.bme.aut.onlabpropertyhome.controller;

import hu.bme.aut.onlabpropertyhome.model.*;
import hu.bme.aut.onlabpropertyhome.repository.AdRepository;
import hu.bme.aut.onlabpropertyhome.repository.PropertyRepository;
import hu.bme.aut.onlabpropertyhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private PropertyRepository propertyRepository;


    @GetMapping
    public String helloWorld() {
        User nu = new User();
        nu.setEmail("asd");
        nu.setName("asdd");
        Ad a = new Ad();
        a.setUser(nu);
        nu.addAd(a);
        userRepository.save(nu);
        adRepository.save(a);

        Property p = new Property();
        a.setProperty(p);
        p.setAd(a);

        propertyRepository.save(p);
        Ad b = new Ad();
        b.setUser(nu);
        userRepository.save(nu);
        adRepository.save(b);

        return "Successful Test";
    }
}
