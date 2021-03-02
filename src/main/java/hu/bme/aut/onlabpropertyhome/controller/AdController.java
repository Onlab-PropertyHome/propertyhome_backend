package hu.bme.aut.onlabpropertyhome.controller;

import hu.bme.aut.onlabpropertyhome.model.*;
import hu.bme.aut.onlabpropertyhome.repository.AdRepository;
import hu.bme.aut.onlabpropertyhome.repository.PropertyRepository;
import hu.bme.aut.onlabpropertyhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
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
                                       @RequestBody PropertyAd propertyAd) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            Ad ad = new Ad();
            ad.setDetails(propertyAd.getAdDetails().getDetails());
            ad.setLocation(propertyAd.getAdDetails().getLocation());
            ad.setPicture(propertyAd.getAdDetails().getPicture());
            ad.setPrice(propertyAd.getAdDetails().getPrice());
            ad.setUser(user);

            Property property = new Property();
            property.setRoomNumber(propertyAd.getPropertyDetails().getRoomNumber());
            property.setSize(propertyAd.getPropertyDetails().getSize());
            property.setState(propertyAd.getPropertyDetails().getState());
            property.setType(propertyAd.getPropertyDetails().getType());
            property.setAd(ad);

            ad.setProperty(property);
            user.addAd(ad);

            propertyRepository.save(property);
            adRepository.save(ad);
            userRepository.save(user);

            return "done";
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
    public @ResponseBody String editAd(@PathVariable(value = "id") Integer id, AdDTO adDTO) {
        if (adRepository.findById(id).isPresent()) {
            Ad old_ad = adRepository.findById(id).get();
            old_ad.setPicture(adDTO.getPicture());
            old_ad.setPrice(adDTO.getPrice());
            old_ad.setDetails(adDTO.getDetails());
            old_ad.setLocation(adDTO.getLocation());

            adRepository.save(old_ad);

            return "done";
        }

        return "err";
    }
}
