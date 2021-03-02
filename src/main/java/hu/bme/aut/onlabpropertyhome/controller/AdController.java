package hu.bme.aut.onlabpropertyhome.controller;

import hu.bme.aut.onlabpropertyhome.model.*;
import hu.bme.aut.onlabpropertyhome.repository.AdRepository;
import hu.bme.aut.onlabpropertyhome.repository.PropertyRepository;
import hu.bme.aut.onlabpropertyhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping
public class AdController {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @PostMapping(path="/addAd")
    public @ResponseBody String addAd (@RequestParam Integer id,
                                       @RequestBody AdDetails adDetails,
                                       @RequestBody PropertyDetails propertyDetails) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            Ad ad = new Ad();
            ad.setDetails(adDetails.getDetails());
            ad.setLocation(adDetails.getLocation());
            ad.setPicture(adDetails.getPicture());
            ad.setPrice(adDetails.getPrice());
            ad.setUser(user);

            Property property = new Property();
            property.setRoomNumber(propertyDetails.getRoomNumber());
            property.setSize(propertyDetails.getSize());
            property.setState(propertyDetails.getState());
            property.setType(propertyDetails.getType());
            property.setAd(ad);

            ad.setProperty(property);
            user.addAd(ad);

            userRepository.save(user);
            adRepository.save(ad);
            propertyRepository.save(property);
        }

        return "err";
    }

    @GetMapping(path="/allAd")
    public @ResponseBody Iterable<Ad> getAllAd() {
        return adRepository.findAll();
    }

    @GetMapping(path="/getAd/{id}")
    public @ResponseBody
    Optional<Ad> getAd(@PathVariable(value = "id") Integer id) {
        return adRepository.findById(id);
    }

    @PutMapping(path="/editAd/{id}")
    public @ResponseBody String editAd(@PathVariable(value = "id") Integer id, AdDetails adDetails) {
        if (adRepository.findById(id).isPresent()) {
            Ad old_ad = adRepository.findById(id).get();
            old_ad.setPicture(adDetails.getPicture());
            old_ad.setPrice(adDetails.getPrice());
            old_ad.setDetails(adDetails.getDetails());
            old_ad.setLocation(adDetails.getLocation());

            adRepository.save(old_ad);

            return "done";
        }

        return "err";
    }
}
